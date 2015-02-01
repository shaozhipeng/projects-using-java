package com.zhipengs.resteasy.demo.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.zhipengs.resteasy.demo.service.BookService;

/**
 * Application for Service Regist
 * 
 * @author shaozhipeng
 * 
 */
public class DemoApplication extends Application {
	HashSet<Object> singletons = new HashSet<Object>();

	/**
	 * Regist Service
	 */
	public DemoApplication() {
		singletons.add(new BookService());
	}

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
