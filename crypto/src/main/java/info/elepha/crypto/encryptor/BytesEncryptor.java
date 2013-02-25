package info.elepha.crypto.encryptor;


public interface BytesEncryptor {

	public byte[] encrypt(byte[] data);
	
	public byte[] decrypt(byte[] data);
	
}
