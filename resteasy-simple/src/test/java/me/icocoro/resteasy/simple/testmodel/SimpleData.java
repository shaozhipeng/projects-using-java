package me.icocoro.resteasy.simple.testmodel;

import java.util.List;

import me.icocoro.resteasy.simple.model.Simple;

/**
 * SimpleData for test
 * 
 * @author shaozhipeng
 *
 */
public class SimpleData {
	private double price;
	private String dataName;
	private String introduction;
	private List<Simple> simpleList;

	public SimpleData(double price, String dataName, String introduction) {
		super();
		this.price = price;
		this.dataName = dataName;
		this.introduction = introduction;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public List<Simple> getSimpleList() {
		return simpleList;
	}

	public void setSimpleList(List<Simple> simpleList) {
		this.simpleList = simpleList;
	}

}
