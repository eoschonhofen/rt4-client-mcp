package rt4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * AIO-08 / AIO-16 — `rsaenc` rewrites the buffer as a length byte plus the ciphertext, so the block is
 * one byte longer than the modulus, and two bytes longer when `BigInteger.toByteArray()` adds a sign
 * byte. `CreateManager.createAccount` allocated 129 bytes and `LoginManager.startLogin`'s world hop 128,
 * both sized from the plaintext instead: the create path crashed the client on roughly one registration
 * in fourteen, and the hop path on every one.
 *
 * <p>These tests pin the two allocations the client used to make. They fail before the fix in
 * `Buffer.rsaenc` (IndexOutOfBounds) and pass after it.
 */
public class RsaEncBlockTest {

	/** `CreateManager.createAccount` writes 63 bytes before `rsaenc`; the framing does not care. */
	private static final int PLAINTEXT_LENGTH = 63;

	/** The buffer `CreateManager.createAccount` used before the fix. */
	private static final int CREATE_ACCOUNT_BUFFER = 129;

	/** The buffer `LoginManager.startLogin`'s world hop used before the fix. */
	private static final int WORLD_HOP_BUFFER = 128;

	@Test
	public void keepsASignedCiphertextInTheCreateAccountBuffer() {
		assertFramesTheBlock(CREATE_ACCOUNT_BUFFER);
	}

	@Test
	public void keepsASignedCiphertextInTheWorldHopBuffer() {
		assertFramesTheBlock(WORLD_HOP_BUFFER);
	}

	@Test
	public void keepsAnUnsignedCiphertext() {
		// The other half of the distribution: no sign byte, so the block is 129 bytes and fitted the
		// create-account buffer before the fix even though the world hop still lost a byte.
		byte[] plaintext = plaintextWithCiphertextLength(modulusBytes());
		assertFramesTheBlock(CREATE_ACCOUNT_BUFFER, plaintext);
	}

	/**
	 * Encrypts {@code plaintext} in a buffer of {@code size} — the size a caller would have picked from
	 * the plaintext — and checks the rewritten block is a length byte plus the exact ciphertext, the way
	 * the server's {@code Login.decryptRSABuffer} reads it back.
	 */
	private static void assertFramesTheBlock(int size) {
		assertFramesTheBlock(size, plaintextWithCiphertextLength(modulusBytes() + 1));
	}

	private static void assertFramesTheBlock(int size, byte[] plaintext) {
		BigInteger ciphertext = new BigInteger(plaintext).modPow(GlobalConfig.RSA_EXPONENT, GlobalConfig.RSA_MODULUS);
		byte[] ciphertextBytes = ciphertext.toByteArray();

		Buffer buffer = new Buffer(size);
		buffer.pdata(plaintext, plaintext.length);
		Assertions.assertEquals(plaintext.length, buffer.offset);

		buffer.rsaenc(GlobalConfig.RSA_EXPONENT, GlobalConfig.RSA_MODULUS);

		Assertions.assertEquals(1 + ciphertextBytes.length, buffer.offset, "block is a length byte plus the ciphertext");
		Assertions.assertEquals(ciphertextBytes.length, buffer.data[0] & 0xFF, "the length byte is the ciphertext length");
		Assertions.assertArrayEquals(ciphertextBytes, Arrays.copyOfRange(buffer.data, 1, buffer.offset));
		// The server reads the block back signed: the sign byte has to survive.
		Assertions.assertEquals(ciphertext, new BigInteger(Arrays.copyOfRange(buffer.data, 1, buffer.offset)));
	}

	/** A 63-byte plaintext like the registry packet's, whose ciphertext is {@code length} bytes. */
	private static byte[] plaintextWithCiphertextLength(int length) {
		byte[] plaintext = new byte[PLAINTEXT_LENGTH];
		plaintext[0] = 10; // the header byte AccountRegister checks after decrypting
		for (int attempt = 0; attempt < 100000; attempt++) {
			plaintext[1] = (byte) (attempt >>> 8);
			plaintext[2] = (byte) attempt;
			if (new BigInteger(plaintext).modPow(GlobalConfig.RSA_EXPONENT, GlobalConfig.RSA_MODULUS).toByteArray().length == length) {
				return plaintext;
			}
		}
		throw new AssertionError("no plaintext of " + PLAINTEXT_LENGTH + " bytes has a " + length + "-byte ciphertext");
	}

	private static int modulusBytes() {
		return (GlobalConfig.RSA_MODULUS.bitLength() + 7) / 8;
	}
}
