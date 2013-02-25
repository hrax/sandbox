package info.elepha.crypto.encryptor;

import info.elepha.crypto.util.CipherUtils;
import info.elepha.crypto.util.CryptoUtils;

import java.security.Key;

import javax.crypto.Cipher;

public class StandardAsymmetricEncryptor implements BytesAsymmetricEncryptor, TextAsymmetricEncryptor {

	private String algorithm = "RSA";
	
	@Override
	public byte[] encrypt(byte[] data, Key key) {
		Cipher cipher = CipherUtils.createCipher(algorithm);
		CipherUtils.initCipher(cipher, Cipher.ENCRYPT_MODE, key, null);
		return CipherUtils.doFinal(cipher, data);
	}

	@Override
	public byte[] decrypt(byte[] data, Key key) {
		Cipher cipher = CipherUtils.createCipher(algorithm);
		CipherUtils.initCipher(cipher, Cipher.DECRYPT_MODE, key, null);
		return CipherUtils.doFinal(cipher, data);
	}

	@Override
	public String encrypt(String data, Key key) {
		return CryptoUtils.toString(encrypt(data.getBytes(), key));
	}

	@Override
	public String decrypt(String data, Key key) {
		return new String(decrypt(CryptoUtils.fromString(data), key));
	}

}
