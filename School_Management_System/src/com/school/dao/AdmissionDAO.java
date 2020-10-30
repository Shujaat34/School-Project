package com.school.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import com.school.bean.AdmissionBean;

public interface AdmissionDAO {
	public ResultSet getDataResultSet();
	public ResultSet getSpecificResultSet(String CLASS);
	public Integer addNewStudent(String Sname, String Fname, Timestamp dob , String Pcontact , String class_id );
	public Integer deleteStudent(Integer id);
	public AdmissionBean getDataById(Integer id);
	public Integer updateStudent(String name, String fname, Timestamp dob, String contact,String class_id,String id);
	public Integer getStudentIdbyName(String name);
	public List<String> getAllStudents();
	
	public int getAllStudentsCount() ;

}
