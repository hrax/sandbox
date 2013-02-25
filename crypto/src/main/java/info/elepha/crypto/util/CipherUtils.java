package info.elepha.crypto.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class CipherUtils {

	 /**
     * Constructs a new Cipher.
     */
    public static Cipher createCipher(String algorithm) {
        try {
            return Cipher.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Not a valid encryption algorithm", e);
        } catch (NoSuchPaddingException e) {
            throw new IllegalStateException("Should not happen", e);
        }
    }

    public static <T extends AlgorithmParameterSpec> T getParameterSpec(Cipher cipher, Class<T> parameterSpecClass) {
        try {
            return cipher.getParameters().getParameterSpec(parameterSpecClass);
        } catch (InvalidParameterSpecException e) {
            throw new IllegalArgumentException("Unable to access parameter", e);
        }
    }
	
	/**
     * Initializes the Cipher for use.
     */
    public static void initCipher(Cipher cipher, int mode, Key key, AlgorithmParameterSpec parameterSpec) {
        try {
            if (parameterSpec != null) {
                cipher.init(mode, key, parameterSpec);
            } else {
                cipher.init(mode, key);
            }
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException("Unable to initialize due to invalid secret key", e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalStateException("Unable to initialize due to invalid decryption parameter spec", e);
        }
    }
	
	/**
     * Invokes the Cipher to perform encryption or decryption (depending on the initialized mode).
     */
    public static byte[] doFinal(Cipher cipher, byte[] input) {
        try {
            return cipher.doFinal(input);
        } catch (IllegalBlockSizeException e) {
            throw new IllegalStateException("Unable to invoke Cipher due to illegal block size", e);
        } catch (BadPaddingException e) {
            throw new IllegalStateException("Unable to invoke Cipher due to bad padding", e);
        }
    }
	
}
