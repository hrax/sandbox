package info.elepha.crypto.encryptor;

import java.security.Key;

public interface TextAsymmetricEncryptor {

	public String encrypt(String data, Key key);
	
	public String decrypt(String data, Key key);
	
}
