package com.zhipengs.resteasy.simple.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.zhipengs.resteasy.simple.service.SimpleService;

/**
 * Application for Service Regist
 * 
 * @author shaozhipeng
 * 
 */
public class SimpleApplication extends Application {
	HashSet<Object> singletons = new HashSet<Object>();

	/**
	 * Regist Service
	 */
	public SimpleApplication() {
		singletons.add(new SimpleService());
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
