package info.elepha.crypto.encryptor;

import java.security.Key;

public interface BytesAsymmetricEncryptor {

	public byte[] encrypt(byte[] data, Key key);
	
	public byte[] decrypt(byte[] data, Key key);
	
}
