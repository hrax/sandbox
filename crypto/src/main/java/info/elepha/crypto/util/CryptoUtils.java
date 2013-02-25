package info.elepha.crypto.util;

import info.elepha.crypto.codec.Base64;


public abstract class CryptoUtils {

	public static String toString(byte[] bytes) {
		return new String(Base64.encode(bytes));
	}
	
	public static byte[] fromString(String string) {
		return Base64.decode(string.getBytes());
	}
	
}
