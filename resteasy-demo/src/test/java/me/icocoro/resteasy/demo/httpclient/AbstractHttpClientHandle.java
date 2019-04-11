package me.icocoro.resteasy.demo.httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * AbstractHttpClientHandle
 * 
 * @author shaozhipeng
 * 
 */
public class AbstractHttpClientHandle {
	protected static List<NameValuePair> transformMap(Map<String, String> params) {
		// 如果参数为空则返回null
		if (params == null || params.size() < 0) {
			return new ArrayList<NameValuePair>();
		}

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> map : params.entrySet()) {
			paramList.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}
		return paramList;
	}
}
