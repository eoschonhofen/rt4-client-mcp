import java.io.IOException;
import java.net.Socket;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static216 {

	@OriginalMember(owner = "client!ri", name = "d", descriptor = "[I")
	public static int[] anIntArray188;

	@OriginalMember(owner = "client!ri", name = "b", descriptor = "[I")
	public static final int[] anIntArray187 = new int[14];

	@OriginalMember(owner = "client!ri", name = "a", descriptor = "(B)V")
	public static void method1639() {
		if (Static184.anInt4348 == 0 || Static184.anInt4348 == 5) {
			return;
		}
		try {
			if (++Static92.anInt2430 > 2000) {
				if (Client.stream != null) {
					Client.stream.close();
					Client.stream = null;
				}
				if (Static276.anInt5816 >= 1) {
					Static266.anInt5336 = -5;
					Static184.anInt4348 = 0;
					return;
				}
				Static92.anInt2430 = 0;
				if (Client.anInt4794 == Client.anInt5800) {
					Client.anInt4794 = Client.anInt1738;
				} else {
					Client.anInt4794 = Client.anInt5800;
				}
				Static184.anInt4348 = 1;
				Static276.anInt5816++;
			}
			if (Static184.anInt4348 == 1) {
				Static72.aClass212_3 = GameShell.signlink.socketreq(Client.aString1, Client.anInt4794);
				Static184.anInt4348 = 2;
			}
			if (Static184.anInt4348 == 2) {
				if (Static72.aClass212_3.status == 2) {
					throw new IOException();
				}
				if (Static72.aClass212_3.status != 1) {
					return;
				}
				Client.stream = new ClientStream((Socket) Static72.aClass212_3.result, GameShell.signlink);
				Static72.aClass212_3 = null;
				@Pc(106) long local106 = Static101.aLong98 = Static186.aClass100_829.method3158();
				Static6.aClass3_Sub15_Sub1_1.pos = 0;
				Static6.aClass3_Sub15_Sub1_1.p1(14);
				@Pc(120) int local120 = (int) (local106 >> 16 & 0x1FL);
				Static6.aClass3_Sub15_Sub1_1.p1(local120);
				Client.stream.write(Static6.aClass3_Sub15_Sub1_1.data, 2);
				if (Client.midiPcmPlayer != null) {
					Client.midiPcmPlayer.method3571();
				}
				if (Client.soundPcmPlayer != null) {
					Client.soundPcmPlayer.method3571();
				}
				@Pc(150) int local150 = Client.stream.method2828();
				if (Client.midiPcmPlayer != null) {
					Client.midiPcmPlayer.method3571();
				}
				if (Client.soundPcmPlayer != null) {
					Client.soundPcmPlayer.method3571();
				}
				if (local150 != 0) {
					Static266.anInt5336 = local150;
					Static184.anInt4348 = 0;
					Client.stream.close();
					Client.stream = null;
					return;
				}
				Static184.anInt4348 = 3;
			}
			if (Static184.anInt4348 == 3) {
				if (Client.stream.available() < 8) {
					return;
				}
				Client.stream.read(0, 8, Client.in.data);
				Client.in.pos = 0;
				Static193.aLong147 = Client.in.g8();
				@Pc(210) int[] local210 = new int[4];
				Static6.aClass3_Sub15_Sub1_1.pos = 0;
				local210[2] = (int) (Static193.aLong147 >> 32);
				local210[3] = (int) Static193.aLong147;
				local210[1] = (int) (Math.random() * 9.9999999E7D);
				local210[0] = (int) (Math.random() * 9.9999999E7D);
				Static6.aClass3_Sub15_Sub1_1.p1(10);
				Static6.aClass3_Sub15_Sub1_1.p4(local210[0]);
				Static6.aClass3_Sub15_Sub1_1.p4(local210[1]);
				Static6.aClass3_Sub15_Sub1_1.p4(local210[2]);
				Static6.aClass3_Sub15_Sub1_1.p4(local210[3]);
				Static6.aClass3_Sub15_Sub1_1.p8(Static186.aClass100_829.method3158());
				Static6.aClass3_Sub15_Sub1_1.method2171(Static186.aClass100_828);
				if (GlobalConfig.LOGIN_EXTRA_INFO) {
					Static6.aClass3_Sub15_Sub1_1.method2171(JagString.wrap(""));
					Static6.aClass3_Sub15_Sub1_1.method2171(JagString.wrap(""));
					Static6.aClass3_Sub15_Sub1_1.method2171(JagString.wrap(""));
				}
				Static6.aClass3_Sub15_Sub1_1.method2226(Static86.aBigInteger1, Static256.aBigInteger2);
				Static17.aClass3_Sub15_Sub1_2.pos = 0;
				if (Client.state == 40) {
					Static17.aClass3_Sub15_Sub1_2.p1(18);
				} else {
					Static17.aClass3_Sub15_Sub1_2.p1(16);
				}
				int offset = 0;
				if (GlobalConfig.LOGIN_FAKE_IDX28) {
					// pretend that we're loading the archive so we don't throw the packet size off
					offset = 4;
				}
				Static17.aClass3_Sub15_Sub1_2.p2(Static6.aClass3_Sub15_Sub1_1.pos + Static229.method3937(Static47.aClass100_991) + (159 + offset));
				Static17.aClass3_Sub15_Sub1_2.p4(530);
				Static17.aClass3_Sub15_Sub1_2.p1(Static5.anInt39);
				Static17.aClass3_Sub15_Sub1_2.p1(Client.advertsuppressed ? 1 : 0);
				Static17.aClass3_Sub15_Sub1_2.p1(1);
				Static17.aClass3_Sub15_Sub1_2.p1(Static144.method2736());
				Static17.aClass3_Sub15_Sub1_2.p2(GameShell.anInt1448);
				Static17.aClass3_Sub15_Sub1_2.p2(GameShell.anInt5554);
				Static17.aClass3_Sub15_Sub1_2.p1(Static186.anInt4392);
				Static140.method2705(Static17.aClass3_Sub15_Sub1_2);
				Static17.aClass3_Sub15_Sub1_2.method2171(Static47.aClass100_991);
				Static17.aClass3_Sub15_Sub1_2.p4(Client.affid);
				Static17.aClass3_Sub15_Sub1_2.p4(Static145.method2746());
				Static18.aBoolean39 = true;
				Static17.aClass3_Sub15_Sub1_2.p2(Static189.anInt4443);
				Static17.aClass3_Sub15_Sub1_2.p4(Client.anims.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.bases.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.config.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.interfaces.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.jagFX.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.maps.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.songs.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.models.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.sprites.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.textures.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.binary.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.jingles.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.scripts.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.fontMetrics.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.vorbis.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.patches.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.locConfig.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.enumConfig.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.npcConfig.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.objConfig.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.seqConfig.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.spotConfig.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.varbitConfig.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.worldmap.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.quickchat.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.quickchatGlobal.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.materials.method4480());
				Static17.aClass3_Sub15_Sub1_2.p4(Client.particleConfig.method4480());
				if (GlobalConfig.LOGIN_FAKE_IDX28) {
					Static17.aClass3_Sub15_Sub1_2.p4(0);
				}
				Static17.aClass3_Sub15_Sub1_2.method2179(Static6.aClass3_Sub15_Sub1_1.data, Static6.aClass3_Sub15_Sub1_1.pos);
				Client.stream.write(Static17.aClass3_Sub15_Sub1_2.data, Static17.aClass3_Sub15_Sub1_2.pos);
				Static6.aClass3_Sub15_Sub1_1.method2240(local210);
				for (@Pc(583) int local583 = 0; local583 < 4; local583++) {
					local210[local583] += 50;
				}
				Client.in.method2240(local210);
				Static184.anInt4348 = 4;
			}
			if (Static184.anInt4348 == 4) {
				if (Client.stream.available() < 1) {
					return;
				}
				@Pc(623) int local623 = Client.stream.method2828();
				if (local623 == 21) {
					Static184.anInt4348 = 7;
				} else if (local623 == 29) {
					Static184.anInt4348 = 10;
				} else if (local623 == 1) {
					Static184.anInt4348 = 5;
					Static266.anInt5336 = local623;
					return;
				} else if (local623 == 2) {
					Static184.anInt4348 = 8;
				} else if (local623 == 15) {
					Static184.anInt4348 = 0;
					Static266.anInt5336 = local623;
					return;
				} else if (local623 == 23 && Static276.anInt5816 < 1) {
					Static184.anInt4348 = 1;
					Static276.anInt5816++;
					Static92.anInt2430 = 0;
					Client.stream.close();
					Client.stream = null;
					return;
				} else {
					Static266.anInt5336 = local623;
					Static184.anInt4348 = 0;
					Client.stream.close();
					Client.stream = null;
					return;
				}
			}
			if (Static184.anInt4348 == 6) {
				Static6.aClass3_Sub15_Sub1_1.pos = 0;
				Static6.aClass3_Sub15_Sub1_1.p1Enc(17);
				Client.stream.write(Static6.aClass3_Sub15_Sub1_1.data, Static6.aClass3_Sub15_Sub1_1.pos);
				Static184.anInt4348 = 4;
				return;
			}
			if (Static184.anInt4348 == 7) {
				if (Client.stream.available() >= 1) {
					Static231.anInt5202 = (Client.stream.method2828() + 3) * 60;
					Static184.anInt4348 = 0;
					Static266.anInt5336 = 21;
					Client.stream.close();
					Client.stream = null;
					return;
				}
				return;
			}
			if (Static184.anInt4348 == 10) {
				if (Client.stream.available() >= 1) {
					Static204.anInt4765 = Client.stream.method2828();
					Static184.anInt4348 = 0;
					Static266.anInt5336 = 29;
					Client.stream.close();
					Client.stream = null;
					return;
				}
				return;
			}
			if (Static184.anInt4348 == 8) {
				if (Client.stream.available() < 14) {
					return;
				}
				Client.stream.read(0, 14, Client.in.data);
				Client.in.pos = 0;
				Static191.anInt4502 = Client.in.g1();
				Static249.anInt5431 = Client.in.g1();
				Static124.aBoolean157 = Client.in.g1() == 1;
				Static207.aBoolean236 = Client.in.g1() == 1;
				Static25.aBoolean57 = Client.in.g1() == 1;
				Static86.aBoolean129 = Client.in.g1() == 1;
				Static245.aBoolean281 = Client.in.g1() == 1;
				Static16.anInt549 = Client.in.g2();
				Static202.aBoolean233 = Client.in.g1() == 1;
				Static2.memServer = Client.in.g1() == 1;
				Static189.method3438(Static2.memServer);
				Static9.method186(Static2.memServer);
				if (!Client.advertsuppressed) {
					if (Static124.aBoolean157 && !Static25.aBoolean57 || Static202.aBoolean233) {
						try {
							Static167.aClass100_781.method3157(GameShell.signlink.applet);
						} catch (@Pc(910) Throwable local910) {
						}
					} else {
						try {
							Static56.aClass100_380.method3157(GameShell.signlink.applet);
						} catch (@Pc(920) Throwable local920) {
						}
					}
				}
				Client.ptype = Client.in.g1Enc();
				Client.psize = Client.in.g2();
				Static184.anInt4348 = 9;
			}
			if (Static184.anInt4348 == 9) {
				if (Client.stream.available() < Client.psize) {
					return;
				}
				Client.in.pos = 0;
				Client.stream.read(0, Client.psize, Client.in.data);
				Static266.anInt5336 = 2;
				Static184.anInt4348 = 0;
				Static243.method4221();
				Static80.anInt4701 = -1;
				Static75.method1629(false);
				Client.ptype = -1;
				return;
			}
		} catch (@Pc(977) IOException local977) {
			if (Client.stream != null) {
				Client.stream.close();
				Client.stream = null;
			}
			if (Static276.anInt5816 >= 1) {
				Static184.anInt4348 = 0;
				Static266.anInt5336 = -4;
			} else {
				Static184.anInt4348 = 1;
				Static92.anInt2430 = 0;
				Static276.anInt5816++;
				if (Client.anInt5800 == Client.anInt4794) {
					Client.anInt4794 = Client.anInt1738;
				} else {
					Client.anInt4794 = Client.anInt5800;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ri", name = "a", descriptor = "(II)I")
	public static int method1640(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		arg1 = arg1 * (arg0 & 0x7F) >> 7;
		if (arg1 < 2) {
			arg1 = 2;
		} else if (arg1 > 126) {
			arg1 = 126;
		}
		return (arg0 & 0xFF80) + arg1;
	}
}
