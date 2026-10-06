package rt4.aionly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** AIO-08 — the token create flow, driven with fake replies. */
class TokenCreateFlowTest {

	@AfterEach
	void reset() {
		TokenCreateFlow.finish();
	}

	@Test
	void theHappyPathStaysBusyUntilDone() {
		TokenCreateFlow.begin(12345L);

		TokenCreateFlow.Decision nameCheck = TokenCreateFlow.onReply(TokenCreateFlow.OK);
		Assertions.assertEquals(TokenCreateFlow.Action.CHECK_INFO, nameCheck.action);
		Assertions.assertEquals(TokenCreateFlow.BUSY, nameCheck.reply, "the CS2 must not reach the DOB screen");

		TokenCreateFlow.Decision info = TokenCreateFlow.onReply(TokenCreateFlow.OK);
		Assertions.assertEquals(TokenCreateFlow.Action.CREATE_ACCOUNT, info.action);
		Assertions.assertEquals(TokenCreateFlow.BUSY, info.reply);

		TokenCreateFlow.Decision create = TokenCreateFlow.onReply(TokenCreateFlow.OK);
		Assertions.assertEquals(TokenCreateFlow.Action.DONE, create.action);
		Assertions.assertEquals(TokenCreateFlow.OK, create.reply, "the CS2 finally sees success");
		Assertions.assertTrue(AgentToken.isValid(create.token));
		Assertions.assertEquals(12345L, create.encodedName);
		Assertions.assertNull(TokenCreateFlow.token(), "the token leaves the flow at DONE");
		Assertions.assertFalse(TokenCreateFlow.active());
	}

	@Test
	void aTakenNameStopsTheFlowAndIsSurfaced() {
		TokenCreateFlow.begin(1L);

		TokenCreateFlow.Decision decision = TokenCreateFlow.onReply(20);

		Assertions.assertEquals(TokenCreateFlow.Action.FAILED, decision.action);
		Assertions.assertEquals(20, decision.reply);
		Assertions.assertNull(TokenCreateFlow.token());
		Assertions.assertFalse(TokenCreateFlow.active());
	}

	@Test
	void aRefusedInfoStepIsSurfaced() {
		TokenCreateFlow.begin(1L);
		TokenCreateFlow.onReply(TokenCreateFlow.OK);

		TokenCreateFlow.Decision decision = TokenCreateFlow.onReply(9);

		Assertions.assertEquals(TokenCreateFlow.Action.FAILED, decision.action);
		Assertions.assertEquals(9, decision.reply);
		Assertions.assertNull(TokenCreateFlow.token());
	}

	@Test
	void aRefusedCreateIsSurfaced() {
		TokenCreateFlow.begin(1L);
		TokenCreateFlow.onReply(TokenCreateFlow.OK);
		TokenCreateFlow.onReply(TokenCreateFlow.OK);

		TokenCreateFlow.Decision decision = TokenCreateFlow.onReply(7);

		Assertions.assertEquals(TokenCreateFlow.Action.FAILED, decision.action);
		Assertions.assertEquals(7, decision.reply);
		Assertions.assertNull(TokenCreateFlow.token());
	}

	@Test
	void aConnectionErrorFailsTheFlow() {
		TokenCreateFlow.begin(1L);
		TokenCreateFlow.onReply(TokenCreateFlow.OK);

		TokenCreateFlow.Decision decision = TokenCreateFlow.onReply(-4);

		Assertions.assertEquals(TokenCreateFlow.Action.FAILED, decision.action);
		Assertions.assertEquals(-4, decision.reply);
	}

	@Test
	void repliesOutsideTheFlowChangeNothing() {
		TokenCreateFlow.Decision decision = TokenCreateFlow.onReply(TokenCreateFlow.OK);

		Assertions.assertEquals(TokenCreateFlow.Action.NONE, decision.action);
		Assertions.assertEquals(TokenCreateFlow.OK, decision.reply);
	}
}
