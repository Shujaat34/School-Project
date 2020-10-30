package com.school.bean;

public class TimeTableBean {
	private String id;
	private SubjectBean subjectbean;
	private String day;
	
	private TTimeBean ttimebean;
	private ClassBean classbean;
	private PeriodBean periodbean;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SubjectBean getSubjectbean() {
		return subjectbean;
	}
	public void setSubjectbean(SubjectBean subjectbean) {
		this.subjectbean = subjectbean;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public TTimeBean getTtimebean() {
		return ttimebean;
	}
	public void setTtimebean(TTimeBean ttimebean) {
		this.ttimebean = ttimebean;
	}
	public ClassBean getClassbean() {
		return classbean;
	}
	public void setClassbean(ClassBean classbean) {
		this.classbean = classbean;
	}
	public PeriodBean getPeriodbean() {
		return periodbean;
	}
	public void setPeriodbean(PeriodBean periodbean) {
		this.periodbean = periodbean;
	}
	
	
	
}
