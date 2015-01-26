package com.zhipengs.resteasy.simple.model;

import java.util.Date;

/**
 * Model Simple POJO
 * 
 * @author shaozhipeng
 * 
 */
public class Simple {
	private Integer id;
	private String simpleName;
	private String simpleColor;
	private Date simpleDate;

	public Simple(Integer id, String simpleName, String simpleColor,
			Date simpleDate) {
		super();
		this.id = id;
		this.simpleName = simpleName;
		this.simpleColor = simpleColor;
		this.simpleDate = simpleDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getSimpleColor() {
		return simpleColor;
	}

	public void setSimpleColor(String simpleColor) {
		this.simpleColor = simpleColor;
	}

	public Date getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(Date simpleDate) {
		this.simpleDate = simpleDate;
	}

}
