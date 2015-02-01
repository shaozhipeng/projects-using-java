package com.zhipengs.resteasy.demo.model;

import java.util.Date;

/**
 * Pricerule
 * 
 * @author shaozhipeng
 * 
 */
public class Pricerule {
	private int id;
	private String name;
	private float discount;
	private float specialRate;
	private int isdelete;
	private int isdefault;
	private Date updatetime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getSpecialRate() {
		return specialRate;
	}

	public void setSpecialRate(float specialRate) {
		this.specialRate = specialRate;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public int getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(int isdefault) {
		this.isdefault = isdefault;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
