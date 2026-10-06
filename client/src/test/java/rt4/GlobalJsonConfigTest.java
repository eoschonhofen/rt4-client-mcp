package rt4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * AIO-03 — the server's public RSA modulus can be supplied by config.json.
 */
public class GlobalJsonConfigTest {
	private final BigInteger saved = GlobalConfig.RSA_MODULUS;

	@AfterEach
	public void restoreModulus() {
		GlobalConfig.RSA_MODULUS = saved;
	}

	@Test
	public void shouldOverrideTheDefaultModulusWhenConfigured() {
		BigInteger replacement = new BigInteger("96982303379631821170939875058071478695026608406924780574168393250855797534862289546229721580153879336741968220328805101128831071152160922518190059946555203865621183480223212969502122536662721687753974815205744569357388338433981424032996046420057284324856368815997832596174397728134370577184183004453899764052");

		GlobalJsonConfig.instance = GlobalJsonConfig.parse(
			"{\"ip_address\":\"play.example.org\",\"rsa_modulus\":\"" + replacement + "\"}");
		GlobalJsonConfig.applyRsaModulus();

		Assertions.assertEquals(replacement, GlobalConfig.RSA_MODULUS);
	}

	@Test
	public void shouldKeepTheDefaultModulusWhenTheKeyIsMissing() {
		GlobalJsonConfig.instance = GlobalJsonConfig.parse("{\"ip_address\":\"play.example.org\"}");
		GlobalJsonConfig.applyRsaModulus();

		Assertions.assertEquals(saved, GlobalConfig.RSA_MODULUS);
	}

	@Test
	public void shouldKeepTheDefaultModulusWhenTheKeyIsBlank() {
		GlobalJsonConfig.instance = GlobalJsonConfig.parse("{\"rsa_modulus\":\"   \"}");
		GlobalJsonConfig.applyRsaModulus();

		Assertions.assertEquals(saved, GlobalConfig.RSA_MODULUS);
	}
}
