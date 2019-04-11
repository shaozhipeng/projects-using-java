package me.icocoro.resteasy.demo.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * SignaturGenUtil
 * 
 * @author shaozhipeng
 * 
 */
public class SignaturGenUtil {

	/**
	 * 生成签名算法
	 * 
	 * @param pStringToSign
	 * @param pKey
	 * @return String
	 */
	public static String generator(String pStringToSign, String pKey) {
		String lSignature = "None";
		try {
			Mac lMac = Mac.getInstance("HmacSHA1");
			SecretKeySpec lSecret = new SecretKeySpec(pKey.getBytes(),
					"HmacSHA1");
			lMac.init(lSecret);
			byte[] lDigest = lMac.doFinal(pStringToSign.getBytes());
			lSignature = new String(Base64Util.encode(lDigest));
		} catch (NoSuchAlgorithmException lEx) {
			throw new RuntimeException("Problems calculating HMAC", lEx);
		} catch (InvalidKeyException lEx) {
			throw new RuntimeException("Problems calculating HMAC", lEx);
		}
		return lSignature;
	}

}
