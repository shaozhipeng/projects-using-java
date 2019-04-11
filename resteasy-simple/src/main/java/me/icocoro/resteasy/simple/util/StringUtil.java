package me.icocoro.resteasy.simple.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * StringUtil
 * 
 * @author shaozhipeng
 * 
 */
public class StringUtil {
	/**
	 * 去掉字符串中的特殊字符
	 * 
	 * @param str
	 * @return String
	 * @throws PatternSyntaxException
	 */
	public static String specCharFilter(String str)
			throws PatternSyntaxException {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\s*|\t|\r|\n]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
