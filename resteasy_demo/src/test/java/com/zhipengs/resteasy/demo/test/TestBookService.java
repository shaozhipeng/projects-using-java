package com.zhipengs.resteasy.demo.test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.zhipengs.resteasy.demo.common.Constant;
import com.zhipengs.resteasy.demo.httpclient.DefaultHttpClientHandle;
import com.zhipengs.resteasy.demo.model.Author;

/**
 * JUnit Test for BookService
 * 
 * @author shaozhipeng
 * 
 */
public class TestBookService {
	private static final Log logger = LogFactory
			.getLog(TestBookService.class);
	
	@Test
	public void testGetRestful() throws ClientProtocolException, IOException {
		String url = Constant.HOSTURL + "resteasy_demo/ws/demo/book/2";
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("keyCode", "guest");
		Object object = DefaultHttpClientHandle.httpGet(url, paramsMap);
		logger.info("testGetRestful object->"+object);
	}
	
	@Test
	public void testGetDetail() throws ClientProtocolException, IOException {
		String url = Constant.HOSTURL + "resteasy_demo/ws/demo/book/detail";
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("keyCode", "guest");
		paramsMap.put("id", 1);
		Object object = DefaultHttpClientHandle.httpGet(url, paramsMap);
		logger.info("testGetDetail object->"+object);
	}
	
	@Test
	public void testGetBookList() throws ClientProtocolException, IOException {
		String url = Constant.HOSTURL + "resteasy_demo/ws/demo/book/list";
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("keyCode", "guest");
		paramsMap.put("start", 0);
		paramsMap.put("limit", 10);
		Object object = DefaultHttpClientHandle.httpGet(url, paramsMap);
		logger.info("testGetDetail object->"+object);
	}
	
	@Test
	public void testSaveAuthor() throws ClientProtocolException, IOException {
		String url = Constant.HOSTURL + "resteasy_demo/ws/demo/book/saveAuthor";
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("keyCode", "guest");
		Map<String, String> formParam = new HashMap<String, String>();
		formParam.put("author",getTestJsonString());
		Object object = DefaultHttpClientHandle.httpPost(url, paramsMap, formParam);
		logger.info("testSaveAuthor object->"+object);
	}

	private String getTestJsonString() throws JsonGenerationException, JsonMappingException, IOException {
		Author author = new Author("shaozhipeng", "男", "程序员", 0, new Date(), new Date(), "zhipenglees@gmail.com", "zhipengs");
		return new ObjectMapper().writeValueAsString(author);
	}
	
}
