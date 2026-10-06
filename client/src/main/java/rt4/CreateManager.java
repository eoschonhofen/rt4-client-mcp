package rt4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import rt4.aionly.AccountStore;
import rt4.aionly.Accounts;
import rt4.aionly.Lockdown;
import rt4.aionly.TokenCreateFlow;
import rt4.aionly.TokenPanel;
import rt4.mcp.InputInjector;
import rt4.mcp.ToolException;

import java.io.IOException;
import java.net.Socket;

public class CreateManager {

	@OriginalMember(owner = "client!oe", name = "l", descriptor = "I")
	public static int step = 0;

	@OriginalMember(owner = "client!sf", name = "a", descriptor = "I")
	public static int loops = 0;

	@OriginalMember(owner = "client!eg", name = "v", descriptor = "I")
	public static int errors = 0;

	@OriginalMember(owner = "client!sc", name = "y", descriptor = "I")
	public static int reply = -2;

	@OriginalMember(owner = "client!si", name = "S", descriptor = "[Lclient!na;")
	public static JagString[] suggestedNames;

	@OriginalMember(owner = "client!mh", name = "f", descriptor = "(B)V")
	public static void loop() {
		if (step == 0) {
			return;
		}
		try {
			if (++loops > 2000) {
				if (Protocol.socket != null) {
					Protocol.socket.close();
					Protocol.socket = null;
				}
				if (errors >= 1) {
					reply = -5;
					step = 0;
					driveTokenFlow(-5);
					return;
				}
				step = 1;
				loops = 0;
				errors++;
				if (client.port == client.defaultPort) {
					client.port = client.alternatePort;
				} else {
					client.port = client.defaultPort;
				}
			}
			if (step == 1) {
				Protocol.socketRequest = GameShell.signLink.openSocket(client.hostname, client.port);
				step = 2;
			}
			@Pc(120) int response;
			if (step == 2) {
				if (Protocol.socketRequest.status == 2) {
					throw new IOException();
				}
				if (Protocol.socketRequest.status != 1) {
					return;
				}
				Protocol.socket = new BufferedSocket((Socket) Protocol.socketRequest.result, GameShell.signLink);
				Protocol.socketRequest = null;
				Protocol.socket.write(Protocol.outboundBuffer.data, Protocol.outboundBuffer.offset);
				if (client.musicChannel != null) {
					client.musicChannel.pauseConsumptionCheck();
				}
				if (client.soundChannel != null) {
					client.soundChannel.pauseConsumptionCheck();
				}
				response = Protocol.socket.read();
				if (client.musicChannel != null) {
					client.musicChannel.pauseConsumptionCheck();
				}
				if (client.soundChannel != null) {
					client.soundChannel.pauseConsumptionCheck();
				}
				if (response != 21) {
					reply = response;
					step = 0;
					Protocol.socket.close();
					Protocol.socket = null;
					driveTokenFlow(response);
					return;
				}
				step = 3;
			}
			if (step == 3) {
				if (Protocol.socket.available() < 1) {
					return;
				}
				suggestedNames = new JagString[Protocol.socket.read()];
				step = 4;
			}
			if (step == 4) {
				if (Protocol.socket.available() < suggestedNames.length * 8) {
					return;
				}
				Protocol.inboundBuffer.offset = 0;
				Protocol.socket.read(0, suggestedNames.length * 8, Protocol.inboundBuffer.data);
				for (response = 0; response < suggestedNames.length; response++) {
					suggestedNames[response] = Base37.decode37(Protocol.inboundBuffer.g8());
				}
				reply = 21;
				step = 0;
				Protocol.socket.close();
				Protocol.socket = null;
				driveTokenFlow(21);
			}
		} catch (@Pc(238) IOException ignored) {
			if (Protocol.socket != null) {
				Protocol.socket.close();
				Protocol.socket = null;
			}
			if (errors < 1) {
				errors++;
				if (client.defaultPort == client.port) {
					client.port = client.alternatePort;
				} else {
					client.port = client.defaultPort;
				}
				loops = 0;
				step = 1;
			} else {
				reply = -4;
				step = 0;
				driveTokenFlow(-4);
			}
		}
	}

	/**
	 * AIO-08 — once a request finishes, the token flow decides what happens next. It only
	 * runs in a locked build, so the native multi-step flow is untouched in development.
	 */
	private static void driveTokenFlow(int reply) {
		if (!Lockdown.ENABLED || !TokenCreateFlow.active()) {
			return;
		}
		TokenCreateFlow.Decision decision = TokenCreateFlow.onReply(reply);
		CreateManager.reply = decision.reply;
		switch (decision.action) {
			case CHECK_INFO:
				checkInfo(TokenCreateFlow.YEAR, TokenCreateFlow.COUNTRY, TokenCreateFlow.DAY, TokenCreateFlow.MONTH);
				break;
			case CREATE_ACCOUNT:
				createAccount(TokenCreateFlow.DAY, TokenCreateFlow.COUNTRY, TokenCreateFlow.MONTH,
					JagString.parse(TokenCreateFlow.token()), TokenCreateFlow.encodedName(), TokenCreateFlow.YEAR);
				break;
			case DONE:
				publishCreatedAccount(decision.token, decision.encodedName);
				abandonNativeCreateFlow();
				break;
			default:
				// The server's own reply is left in `reply`, so the native screen shows its message.
				break;
		}
	}

	/** AIO-08 — keep the credentials in accounts.json and show them once on screen. */
	private static void publishCreatedAccount(String token, long encodedName) {
		if (token == null) {
			return;
		}
		String username = Base37.decode37(encodedName).toString().replace(" ", "_").toLowerCase();
		Accounts.store().add(AccountStore.Account.create(username, token, Accounts.host()));
		TokenPanel.show(username, token);
	}

	/**
	 * AIO-08 — the account already exists once the token flow finishes, so the native terms
	 * and password screens the CS2 queues after a successful name check are not part of it.
	 * Escape is what the title screen uses to drop the create form and return to the main
	 * menu; the token panel stays up over it.
	 */
	private static void abandonNativeCreateFlow() {
		try {
			InputInjector.pressKey("escape", 0L);
		} catch (ToolException ignored) {
			// No canvas yet: the human can still press Escape, or read the panel.
		}
	}

	@OriginalMember(owner = "client!gd", name = "a", descriptor = "(JI)V")
	public static void checkName(@OriginalArg(0) long name) {
		// AIO-08 — in a locked build the name the human typed starts the token flow.
		if (Lockdown.ENABLED) {
			TokenCreateFlow.begin(name);
		}
		Protocol.outboundBuffer.offset = 0;
		Protocol.outboundBuffer.p1(186);
		if (GlobalConfig.LOGIN_USE_STRINGS) {
			Protocol.outboundBuffer.pjstr(Base37.decode37(name));
		} else {
			Protocol.outboundBuffer.p8(name);
		}
		step = 1;
		loops = 0;
		errors = 0;
		reply = -3;
	}

	@OriginalMember(owner = "client!jl", name = "a", descriptor = "(IIIII)V")
	public static void checkInfo(@OriginalArg(0) int year, @OriginalArg(1) int country, @OriginalArg(2) int day, @OriginalArg(3) int month) {
		Protocol.outboundBuffer.offset = 0;
		Protocol.outboundBuffer.p1(147);
		Protocol.outboundBuffer.p1(day);
		Protocol.outboundBuffer.p1(month);
		Protocol.outboundBuffer.p2(year);
		Protocol.outboundBuffer.p2(country);
		loops = 0;
		errors = 0;
		step = 1;
		reply = -3;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(IIIILclient!na;JI)V")
	public static void createAccount(@OriginalArg(0) int day, @OriginalArg(2) int country, @OriginalArg(3) int month, @OriginalArg(4) JagString password, @OriginalArg(5) long name, @OriginalArg(6) int year) {
		@Pc(8) Buffer buffer = new Buffer(GlobalConfig.LOGIN_USE_STRINGS ? 129 : 128);
		buffer.p1(10);
		buffer.p2((int) (Math.random() * 99999.0D));
		buffer.p2(530);
		if (GlobalConfig.LOGIN_USE_STRINGS) {
			buffer.pjstr(Base37.decode37(name));
		} else {
			buffer.p8(name);
		}
		buffer.p4((int) (Math.random() * 9.9999999E7D));
		buffer.pjstr(password);
		buffer.p4((int) (Math.random() * 9.9999999E7D));
		buffer.p2(client.affiliate);
		buffer.p1(day);
		buffer.p1(month);
		buffer.p4((int) (Math.random() * 9.9999999E7D));
		buffer.p2(year);
		buffer.p2(country);
		buffer.p4((int) (Math.random() * 9.9999999E7D));
		buffer.rsaenc(GlobalConfig.RSA_EXPONENT, GlobalConfig.RSA_MODULUS);
		Protocol.outboundBuffer.offset = 0;
		Protocol.outboundBuffer.p1(36);
		Protocol.outboundBuffer.p1(buffer.offset);
		Protocol.outboundBuffer.pdata(buffer.data, buffer.offset);
		reply = -3;
		step = 1;
		loops = 0;
		errors = 0;
	}
}
