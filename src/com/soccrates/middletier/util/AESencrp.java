package com.soccrates.middletier.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// TODO: Auto-generated Javadoc
/**
 * The Class AESencrp.
 */
public class AESencrp {

	/** The Constant ALGO. */
	private static final String ALGO = "AES";
	
	/** The Constant keyValue. */
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B',
			'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	/**
	 * Encrypt.
	 *
	 * @param Data the data
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}

	/**
	 * Decrypt.
	 *
	 * @param encryptedData the encrypted data
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	/**
	 * Generate key.
	 *
	 * @return the key
	 * @throws Exception the exception
	 */
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

}