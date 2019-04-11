package me.icocoro.resteasy.demo.dao;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0DataSourceFactory
 * 
 * @author shaozhipeng
 * 
 */
public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {

	/**
	 * 初始化dataSource
	 */
	public C3P0DataSourceFactory() {
		this.dataSource = new ComboPooledDataSource();
	}
}
