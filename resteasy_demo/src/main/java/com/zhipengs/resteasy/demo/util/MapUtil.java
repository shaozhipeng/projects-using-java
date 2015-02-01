package com.zhipengs.resteasy.demo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

	/**
	 * 把URL参数key=value&key=value分解后存入Map
	 * 
	 * @param param
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getMapFromUrl(String param) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtil.isEmpty(param)) {
			return result;
		}
		// 分解&和=
		String[] params = param.split("&");
		for (String s : params) {
			String[] p = s.split("=");
			if (p.length == 2) {
				result.put(p[0], p[1]);
				continue;
			}
		}
		return result;
	}

	/**
	 * 删除Map中的filterKey
	 * 
	 * @param map
	 * @param filterKey
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> filter(Map<String, Object> map,
			String filterKey) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (isNull(map)) {
			return result;
		}
		Object value = null;
		for (String key : map.keySet()) {
			value = map.get(key);
			if (value == null || value.equals("")
					|| key.equalsIgnoreCase(filterKey)) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 判断Map是否为空（null或""）
	 * 
	 * @param map
	 * @return boolean
	 */
	public static boolean isNull(Map<?, ?> map) {
		return map == null || map.size() <= 0;
	}
}
