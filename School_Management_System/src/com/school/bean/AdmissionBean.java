package com.school.bean;

import java.sql.Timestamp;

public class AdmissionBean {
	
	private String id;
	private String name;
	private String fname;
	private Timestamp dob;
	private String parentContact;
	private ClassBean classbean;
	
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
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Timestamp getDob() {
		return dob;
	}
	public void setDob(Timestamp dob) {
		this.dob = dob;
	}
	public String getParentContact() {
		return parentContact;
	}
	public void setParentContact(String parentContact) {
		this.parentContact = parentContact;
	}
	public ClassBean getClassbean() {
		return classbean;
	}
	public void setClassbean(ClassBean classbean) {
		this.classbean = classbean;
	}
	

	
}
