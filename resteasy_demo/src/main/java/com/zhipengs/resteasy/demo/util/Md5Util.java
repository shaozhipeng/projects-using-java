package com.zhipengs.resteasy.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5Util 加密
 * 
 * @author shaozhipeng
 * 
 */
public class Md5Util {
	public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * MD5对字符串加密
	 * 
	 * @param sourceStr
	 * @return String
	 */
	public static String process(String sourceStr) {
		String lDigest = "None";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(sourceStr.getBytes());
			byte[] byteArray = md.digest();

			StringBuffer md5StrBuff = new StringBuffer();

			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			lDigest = md5StrBuff.toString();
		} catch (NoSuchAlgorithmException lEx) {
			throw new RuntimeException("Problems calculating MD5", lEx);
		}
		return lDigest;
	}
}
