package rt4.aionly;

/**
 * AIO-08 — the token-driven account creation state machine.
 *
 * <p>Pure: it holds no game references and sends no packets. {@code CreateManager} feeds it
 * each finished server reply and performs the {@link Action} it returns; the tests drive it
 * with fake replies.</p>
 *
 * <p>The native CS2 only ever sees the busy reply until the very end, so its DOB, country and
 * password screens are never reached. From its point of view a single create succeeded.</p>
 */
public final class TokenCreateFlow {
	public enum State { IDLE, NAME_CHECK, INFO, CREATE, DONE, FAILED }

	public enum Action { NONE, CHECK_INFO, CREATE_ACCOUNT, DONE, FAILED }

	/** The client's "busy" reply, which keeps the native CS2 from advancing a screen. */
	public static final int BUSY = -3;
	/** The server's "ok" reply. */
	public static final int OK = 2;

	/** The fixed details; the server accepts any DOB and country (AIO-06). */
	public static final int DAY = 1;
	public static final int MONTH = 1;
	public static final int YEAR = 1990;
	public static final int COUNTRY = 147;

	private static volatile State state = State.IDLE;
	private static volatile long encodedName;
	private static volatile String token;

	private TokenCreateFlow() {
	}

	/** Starts a flow for the name the CS2 just asked about. */
	public static void begin(long name) {
		encodedName = name;
		token = null;
		state = State.NAME_CHECK;
	}

	public static boolean active() {
		return state == State.NAME_CHECK || state == State.INFO || state == State.CREATE;
	}

	public static State state() {
		return state;
	}

	/** The token while the flow runs; null outside it. */
	public static String token() {
		return token;
	}

	/** The encoded name the flow is working on, valid from {@link #begin} until the next one. */
	public static long encodedName() {
		return encodedName;
	}

	/** Forgets everything. Called by tests, and safe to call at any time. */
	public static void finish() {
		state = State.IDLE;
		encodedName = 0L;
		token = null;
	}

	/** What the caller should do, and the reply the CS2 must see. */
	public static final class Decision {
		public final Action action;
		public final int reply;
		public final String token;
		public final long encodedName;

		Decision(Action action, int reply, String token, long encodedName) {
			this.action = action;
			this.reply = reply;
			this.token = token;
			this.encodedName = encodedName;
		}
	}

	/**
	 * A request finished with {@code reply}. Returns what to do next: keep the CS2 busy and
	 * issue the next request, or surface this reply and stop.
	 */
	public static Decision onReply(int reply) {
		switch (state) {
			case NAME_CHECK:
				if (reply == OK) {
					token = AgentToken.generate();
					state = State.INFO;
					return new Decision(Action.CHECK_INFO, BUSY, null, 0L);
				}
				return failure(reply);
			case INFO:
				if (reply == OK) {
					state = State.CREATE;
					return new Decision(Action.CREATE_ACCOUNT, BUSY, null, 0L);
				}
				return failure(reply);
			case CREATE:
				if (reply == OK) {
					String issued = token;
					long name = encodedName;
					// The token leaves the flow here; accounts.json is the durable copy.
					state = State.IDLE;
					token = null;
					return new Decision(Action.DONE, OK, issued, name);
				}
				return failure(reply);
			default:
				return new Decision(Action.NONE, reply, null, 0L);
		}
	}

	private static Decision failure(int reply) {
		state = State.FAILED;
		token = null;
		return new Decision(Action.FAILED, reply, null, 0L);
	}
}
