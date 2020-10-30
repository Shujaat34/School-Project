package com.school.bean;

public class AttendanceSubjectBean {
	
	private String id;
	private String subject_id;
	private Integer percentage;
	private String admission_id;// For Student Data.
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	public String getAdmission_id() {
		return admission_id;
	}
	public void setAdmission_id(String admission_id) {
		this.admission_id = admission_id;
	}
	
	
	
	
	

}
