package me.icocoro.resteasy.simple.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
 * MapUtil
 * 
 * @author shaozhipeng
 * 
 */
public class MapUtil {

	/**
	 * 把Map中的参数拼成key=value&key=value的URL参数形式
	 * 
	 * @param params
	 * @return String
	 */
	public static String getUrlFromMap(Map<String, Object> params) {
		// 将key转换为list
		List<String> keys = new ArrayList<String>(params.keySet());
		// 将key进行排序
		Collections.sort(keys);
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			sb.append(key).append("=").append(params.get(key)).append("&");
		}
		// 删除最后一个&
		String url = sb.deleteCharAt(sb.lastIndexOf("&")).toString();
		return url;
	}
}
