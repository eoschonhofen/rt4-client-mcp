package rt4.mcp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/** AIO-13 — the auto-port search and the name the client advertises. */
class McpAutoPortTest {
	private static final String TOKEN = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";

	private final List<ServerSocket> blockers = new ArrayList<ServerSocket>();

	@AfterEach
	void cleanUp() throws Exception {
		McpServer.stop();
		for (ServerSocket blocker : blockers) {
			blocker.close();
		}
		blockers.clear();
	}

	@Test
	void theFirstPortKeepsThePlainServerName() {
		Assertions.assertEquals("rt4", McpServer.serverName(43600));
		Assertions.assertEquals("rt4-43601", McpServer.serverName(43601));
		Assertions.assertEquals("rt4-43609", McpServer.serverName(43609));
	}

	@Test
	void bindsTheNextPortWhenTheConfiguredOneIsBusy() throws Exception {
		int base = freePort();
		blockers.add(new ServerSocket(base, 1, InetAddress.getLoopbackAddress()));

		McpHttpServer server = McpServer.start(new McpConfig(true, base, TOKEN, null));

		Assertions.assertNotNull(server);
		Assertions.assertEquals(base + 1, server.port());
		Assertions.assertEquals(base + 1, McpServer.boundPort());
	}

	@Test
	void givesUpWhenEveryPortInTheSpanIsBusy() throws Exception {
		int base = freePort();
		for (int offset = 0; offset <= McpServer.PORT_SEARCH_SPAN; offset++) {
			blockers.add(new ServerSocket(base + offset, 1, InetAddress.getLoopbackAddress()));
		}

		McpHttpServer server = McpServer.start(new McpConfig(true, base, TOKEN, null));

		Assertions.assertNull(server);
		Assertions.assertEquals(-1, McpServer.boundPort());
	}

	@Test
	void aDisabledConfigBindsNothing() {
		Assertions.assertNull(McpServer.start(new McpConfig(false, 0, TOKEN, null)));
		Assertions.assertFalse(McpServer.enabled());
		Assertions.assertEquals(-1, McpServer.boundPort());
	}

	private static int freePort() throws Exception {
		try (ServerSocket probe = new ServerSocket(0, 1, InetAddress.getLoopbackAddress())) {
			return probe.getLocalPort();
		}
	}
}
