package me.icocoro.resteasy.demo.model;

import java.util.Date;

/**
 * Author
 * 
 * @author shaozhipeng
 * 
 */
public class Author {
	private int id;
	private String name;
	private String gender;
	private String introduction;
	private int parentId;
	private Date createtime;
	private Date updatetime;
	private String email;
	private String penname;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPenname() {
		return penname;
	}

	public void setPenname(String penname) {
		this.penname = penname;
	}

	public Author() {

	}

	public Author(String name, String gender, String introduction,
			int parentId, Date createtime, Date updatetime, String email,
			String penname) {
		super();
		this.name = name;
		this.gender = gender;
		this.introduction = introduction;
		this.parentId = parentId;
		this.createtime = createtime;
		this.updatetime = updatetime;
		this.email = email;
		this.penname = penname;
	}

	public Author(int id, String name, String gender, String introduction,
			int parentId, Date createtime, Date updatetime, String email,
			String penname) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.introduction = introduction;
		this.parentId = parentId;
		this.createtime = createtime;
		this.updatetime = updatetime;
		this.email = email;
		this.penname = penname;
	}

}
