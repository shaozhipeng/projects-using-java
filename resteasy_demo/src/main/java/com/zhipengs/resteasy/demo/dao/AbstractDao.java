package com.zhipengs.resteasy.demo.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.zhipengs.resteasy.demo.common.Constant;

/**
 * MyBatis-AbstractDao
 * 
 * @author shaozhipeng
 * 
 */
public abstract class AbstractDao {
	protected static final Log logger = LogFactory.getLog(AbstractDao.class);

	// 是否已经初始化
	private boolean loaded = false;
	protected SqlSessionFactory sqlSessionFactory;
	// 配置文件默认SqlMapConfig.xml
	protected String configFile = Constant.DEFAULT_CONFIGURATION_FILE;
	protected static String databaseName = "";
	protected String statementPrefix = "";
	// statement的分割符，默认为英文下划线"_"
	protected String statementDivision = ".";

	public AbstractDao() {
		this(Constant.DEFAULT_CONFIGURATION_FILE);
	}

	public AbstractDao(String configFile) {
		this.configFile = configFile;
		init();
	}

	public abstract void init();

	/**
	 * 加载配置文件，加载错误则抛出IllegalArgumentException异常
	 * 
	 * @throws IOException
	 */
	private synchronized void load() throws IOException {
		if (!loaded) {
			this.sqlSessionFactory = MyBatisMapSessionFactory
					.getSqlMapInstance(configFile);
			loaded = true;
		}
	}

	/**
	 * 获取statement的全名称，总共由三部分组成：Prefix(前缀)+Division(分隔符)+Postfix(后缀 )
	 * 
	 * @param statementPostfix
	 * @return String
	 */
	protected String getStatement(String statementPostfix) {
		return getStatementPrefix() + getStatementDivision() + statementPostfix;
	}

	/**
	 * 返回SqlSessionFactory对象
	 * 
	 * @return SqlSessionFactory
	 * @throws IOException
	 */
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		load();
		return sqlSessionFactory;
	}

	/**
	 * selectList template method
	 * 
	 * @param sqlMapName
	 * @param t2
	 * @return List<T>
	 */
	protected <T, T2> List<T> selectList(String sqlMapName, T2 t2) {
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			return session.selectList(sqlMapName, t2);
		} catch (Exception e) {
			logger.error("sqlMapName:" + sqlMapName);
			logger.error("AbstractDao->selectList:" + e);
		} finally {
			if (session != null)
				session.close();
		}
		return new ArrayList<T>();
	}

	/**
	 * selectOne template method
	 * 
	 * @param sqlMapName
	 * @param t2
	 * @return T
	 */
	protected <T, T2> T selectOne(String sqlMapName, T2 t2) {
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			return session.selectOne(sqlMapName, t2);
		} catch (Exception e) {
			logger.error("sqlMapName:" + sqlMapName);
			logger.error("AbstractDao->selectOne:" + e);
		} finally {
			if (session != null)
				session.close();
		}
		return null;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public String getStatementDivision() {
		return statementDivision;
	}

	public void setStatementDivision(String statementDivision) {
		this.statementDivision = statementDivision;
	}

	public String getStatementPrefix() {
		return statementPrefix;
	}

	public void setStatementPrefix(String statementPrefix) {
		this.statementPrefix = statementPrefix;
	}
}
