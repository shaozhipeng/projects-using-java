package me.icocoro.resteasy.demo.util;

/**
 * Base64Util
 * 
 * @author shaozhipeng
 * 
 */
public class Base64Util {

	protected static char getChar(int sixbit) {
		if (sixbit >= 0 && sixbit <= 25) {
			return (char) (65 + sixbit);
		}

		if (sixbit >= 26 && sixbit <= 51) {
			return (char) (97 + (sixbit - 26));
		}

		if (sixbit >= 52 && sixbit <= 61) {
			return (char) (48 + (sixbit - 52));
		}

		if (sixbit == 62) {
			return '+';
		}

		return sixbit != 63 ? '?' : '/';
	}

	public static String encode(byte raw[]) {
		StringBuffer encoded = new StringBuffer();

		for (int i = 0; i < raw.length; i += 3) {
			encoded.append(encodeBlock(raw, i));
		}

		return encoded.toString();
	}

	protected static char[] encodeBlock(byte raw[], int offset) {
		int block = 0;
		int slack = raw.length - offset - 1;
		int end = slack < 2 ? slack : 2;

		for (int i = 0; i <= end; i++) {
			byte b = raw[offset + i];

			int neuter = b >= 0 ? ((int) (b)) : b + 256;
			block += neuter << 8 * (2 - i);
		}

		char base64[] = new char[4];

		for (int i = 0; i < 4; i++) {
			int sixbit = block >>> 6 * (3 - i) & 0x3f;
			base64[i] = getChar(sixbit);
		}

		if (slack < 1) {
			base64[2] = '=';
		}

		if (slack < 2) {
			base64[3] = '=';
		}

		return base64;
	}

}