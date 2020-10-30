package com.school.bean;

public class StaffBean {

	private String id;
	private String name;
	private String Rollnum;
	private String gender;
	private PostBean post;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollnum() {
		return Rollnum;
	}
	public void setRollnum(String rollnum) {
		Rollnum = rollnum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public PostBean getPost() {
		return post;
	}
	public void setPost(PostBean post) {
		this.post = post;
	}
	
	
	
}
