package com.school.bean;

public class ClassBean {

	private String id;
	private String standard;
	private String students;
	private StaffBean staffbean;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getStudents() {
		return students;
	}
	public void setStudents(String students) {
		this.students = students;
	}
	public StaffBean getStaffbean() {
		return staffbean;
	}
	public void setStaffbean(StaffBean staffbean) {
		this.staffbean = staffbean;
	}
	
	
}
