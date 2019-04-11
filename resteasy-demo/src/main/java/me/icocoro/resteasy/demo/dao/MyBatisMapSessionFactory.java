package me.icocoro.resteasy.demo.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;

import me.icocoro.resteasy.demo.common.Constant;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import me.icocoro.resteasy.demo.util.StringUtil;

/**
 * MyBatisMapSessionFactory--自定义SqlSessionFactory的Factory
 * 
 * @author shaozhipeng
 * 
 */
public class MyBatisMapSessionFactory {
	private static Hashtable<String, SqlSessionFactory> ht = new Hashtable<String, SqlSessionFactory>();

	/**
	 * 解析SqlMapConfig.xml文件获得sqlMap句柄
	 * 
	 * @param configFile
	 * @return SqlSessionFactory
	 * @throws IOException
	 */
	public synchronized static SqlSessionFactory getSqlMapInstance(
			String configFile) throws IOException {
		if (StringUtil.isEmpty(configFile)) {
			configFile = Constant.DEFAULT_CONFIGURATION_FILE;
		}
		if (!ht.containsKey(configFile)) {
			Reader reader = Resources.getResourceAsReader(configFile);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
					.build(reader);
			reader.close();
			ht.put(configFile, sessionFactory);
		}
		return ht.get(configFile);
	}
}
