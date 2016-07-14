package com.smluv82.file2compare.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smluv82.file2compare.constants.File2CompareConstants;

/**
 * @author CelestialMoon
 * @since 2010.12.06
 */
public class AES { // NOPMD by Administrator on 16. 6. 29 오전 9:48
	//public static String key = "E5T1KY82Z1MR42X5";
	private static Logger logger = LoggerFactory.getLogger(File2CompareConstants.LOG_FILE2COMPARE);

	/**
	 * hex to byte[] : 16진수 문자열을 바이트 배열로 변환한다.
	 *
	 * @param hex    hex string
	 * @return
	 */
	public static byte[] hexToByteArray(final String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];

		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}

		return ba;
	}

	/**
	 * byte[] to hex : unsigned byte(바이트) 배열을 16진수 문자열로 바꾼다.
	 *
	 * @param byte[]
	 * @return
	 */
	public static String byteArrayToHex(final byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}

		final StringBuilder sb = new StringBuilder(ba.length * 2);
		String hexNumber;

		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}

		return sb.toString();
	}

	/**
	 * AES 방식의 암호화
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(final String key, final String message) {
		try {
			// use key coss2
			final SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

			// Instantiate the cipher
			final Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			final byte[] encrypted = cipher.doFinal(message.getBytes());

			return byteArrayToHex(encrypted);
		}catch(Exception e) {
			logger.error(UsefulUtil.getPrintStacTraceString(e));
			return null;
		}
	}

	/**
	 * AES 방식의 복호화
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(final String key, final String encrypted) {
		try {
			// use key coss2
			final SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

			final Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			final byte[] original = cipher.doFinal(hexToByteArray(encrypted));
			final String originalString = new String(original);

			return originalString;
		}catch(Exception e) {
			logger.error(UsefulUtil.getPrintStacTraceString(e));
			return null;
		}
	}
}
