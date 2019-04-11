package me.icocoro.resteasy.simple.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.annotations.GZIP;

import me.icocoro.resteasy.simple.model.Simple;

/**
 * SimpleService
 * 
 * @author shaozhipeng
 * 
 */
@Path("/ws/simple/")
public class SimpleService {
	private static final Log logger = LogFactory
			.getLog(SimpleService.class);

	@GET
	@Path("detail")
	@Produces("application/json;charset=utf8")
	@GZIP
	public Simple getDetail(@QueryParam("id") int id) {
		logger.info("getDetail->id "+id);
		List<Simple> simpleList = initData();
		if (id > 0 && id <= simpleList.size()) {
			return simpleList.get(id - 1);
		}
		return new Simple(10000, "Hello", "Red", new Date());
	}
	
	@POST
	@Path("saveSimple")
	@Produces("application/json;charset=utf8")
	@GZIP
	public Map<String,Object> saveSimple(@QueryParam("keyValue") String keyValue,@FormParam("simpleData") String simpleData) {
		logger.info("saveSimple->apikey "+keyValue);
		logger.info("saveSimple->simpleData "+simpleData);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", "200");
		if(keyValue==null || keyValue.equals("")) {
			resultMap.put("code", "00001");
		}
		if(simpleData==null || simpleData.equals("")) {
			resultMap.put("code", "00002");
		}
		return resultMap;
	}
	
	/**
	 * 初始化测试数据
	 * 
	 * @return List<Simple>
	 */
	private List<Simple> initData() {
		List<Simple> simpleList = new ArrayList<Simple>();
		for (int i = 1; i <= 50; i++) {
			simpleList.add(new Simple(i, "simpleName " + i, "simpleColor " + i,
					new Date()));
		}
		return simpleList;
	}
}
