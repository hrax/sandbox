package info.elepha.crypto.encryptor;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.spec.KeySpec;

import org.junit.Test;

public class StandardAsymmetricEncryptorTest {

	private static final String TEXT = "Hello world!";
	
	private static final String LONG_TEXT = "movie has caused considerable surprise. Chapter 30 - they spent a boneful of things. - and one - Oh good. He edged up sharply, shooting people of them they cold on they were the it. They waited for his mouth. Kettle, plug, fridge, milk, coffee. Yawn. The man standing with a slight degrees and civilization, leapt off off in a machine only know as to like it was a beam that, was disturbed only thought was sheer industrial archaeology of the floor where Arthur read this, and was more keys. - But";
	
	@Test
	public void testOfEncryptingAndDecryptingText() throws Exception {
		KeyPair pair = KeyGenerators.generateKeyPair("RSA", KeyGenerators.DEFAULT_KEYSIZE);
		StandardAsymmetricEncryptor enc = new StandardAsymmetricEncryptor();
		
		String encrypted = enc.encrypt(TEXT, pair.getPublic());
		String decrypted = enc.decrypt(encrypted, pair.getPrivate());
		assertEquals(TEXT, decrypted);
	}

	@Test
	public void testOfEncryptingAndDecryptingLongText() throws Exception {
		KeyPair pair = KeyGenerators.generateKeyPair("RSA", KeyGenerators.DEFAULT_KEYSIZE);
		StandardAsymmetricEncryptor enc = new StandardAsymmetricEncryptor();
		
		String encrypted = enc.encrypt(LONG_TEXT, pair.getPublic());
		String decrypted = enc.decrypt(encrypted, pair.getPrivate());
		assertEquals(LONG_TEXT, decrypted);
	}
	
	@Test
	public void testOfEncryptingAndDecryptingTextUsingStoredKey() throws Exception {
		KeyPair pair = KeyGenerators.generateKeyPair("RSA", KeyGenerators.DEFAULT_KEYSIZE);
		StandardAsymmetricEncryptor enc = new StandardAsymmetricEncryptor();
		
		byte[] encoded = pair.getPrivate().getEncoded();
		KeySpec spec = KeyGenerators.getPkcs8EncodedKeySpec(encoded);
		
		String encrypted = enc.encrypt(TEXT, pair.getPublic());
		String decrypted = enc.decrypt(encrypted, KeyGenerators.generatePrivate("RSA", spec));
		assertEquals(TEXT, decrypted);
	}
	
	@Test
	public void testOfEncryptingAndDecryptingLongTextUsingStoredKey() throws Exception {
		KeyPair pair = KeyGenerators.generateKeyPair("RSA", KeyGenerators.DEFAULT_KEYSIZE);
		StandardAsymmetricEncryptor enc = new StandardAsymmetricEncryptor();
		
		byte[] encoded = pair.getPrivate().getEncoded();
		KeySpec spec = KeyGenerators.getPkcs8EncodedKeySpec(encoded);
		
		String encrypted = enc.encrypt(LONG_TEXT, pair.getPublic());
		String decrypted = enc.decrypt(encrypted, KeyGenerators.generatePrivate("RSA", spec));
		assertEquals(LONG_TEXT, decrypted);
	}
	
}
