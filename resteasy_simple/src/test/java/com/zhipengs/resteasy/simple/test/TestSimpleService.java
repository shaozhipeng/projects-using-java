package com.zhipengs.resteasy.simple.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.zhipengs.resteasy.simple.common.Constant;
import com.zhipengs.resteasy.simple.httpclient.DefaultHttpClientHandle;
import com.zhipengs.resteasy.simple.model.Simple;
import com.zhipengs.resteasy.simple.testmodel.SimpleData;

/**
 * JUnit Test for SimpleService
 * 
 * @author shaozhipeng
 * 
 */
public class TestSimpleService {
	private static final Log logger = LogFactory
			.getLog(TestSimpleService.class);
	
	@Test
	public void testGetDetail() throws ClientProtocolException, IOException {
		String url = Constant.HOSTURL + "resteasy_simple/ws/simple/detail?id=5";
		Object object = DefaultHttpClientHandle.httpGet(url);
		logger.info("testGetDetail object->"+object);
	}
	
	@Test
	public void testSaveSimple() throws ClientProtocolException, IOException {
		String url = Constant.HOSTURL + "resteasy_simple/ws/simple/saveSimple";
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("keyValue", "shaozhipeng");
		Map<String, String> formParam = new HashMap<String, String>();
		formParam.put("simpleData",getTestJsonString());
		Object object = DefaultHttpClientHandle.httpPost(url, queryParam, formParam);
		logger.info("testSaveSimple object->"+object);
	}
	
	private static String getTestJsonString() throws JsonGenerationException, JsonMappingException, IOException {
		 ObjectMapper mapper = new ObjectMapper();
		 List<Simple> simpleList = new ArrayList<Simple>();
		 SimpleData data = new SimpleData(20.6, "simpleData 2015", "just test post request");
		 for(int i=88;i<=99;i++) {
			 simpleList.add(new Simple(i, "simple post "+i, "simple color "+i, new Date()));
		 }
		 data.setSimpleList(simpleList);
		 return mapper.writeValueAsString(data);
	}
	
}
