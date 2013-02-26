package info.elepha.crypto.encryptor;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public abstract class KeyGenerators {

	public static final int DEFAULT_KEYSIZE = 2048;
	
	public static KeyPair generateKeyPair(String algorithm, int keysize) throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
		kpg.initialize(keysize);
		return kpg.generateKeyPair();
	}
	
	public static Key generate(String algorithm, int keysize) {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(algorithm);
			generator.init(keysize);
			return generator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static Key generatePublic(String algorithm, KeySpec spec) {
		try {
			KeyFactory factory = KeyFactory.getInstance(algorithm);
			return factory.generatePublic(spec);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		} catch (InvalidKeySpecException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static Key generatePrivate(String algorithm, KeySpec spec) {
		try {
			KeyFactory factory = KeyFactory.getInstance(algorithm);
			return factory.generatePrivate(spec);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		} catch (InvalidKeySpecException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static KeySpec getSecretKeySpec(String algorithm, byte[] key) {
		return new SecretKeySpec(key, algorithm);
	}

	public static KeySpec getX509EncodedKeySpec(byte[] key) {
		return new X509EncodedKeySpec(key);
	}

	public static KeySpec getPkcs8EncodedKeySpec(byte[] key) {
		return new PKCS8EncodedKeySpec(key);
	}
	
}
