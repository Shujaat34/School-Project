package com.school.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;

public interface StaffDAO {
	
	public ResultSet getDataResultSet();
	public int getEmployees(String post);
	public int getAllEmployees();
	public ResultSet getSpecificResultSet(String post);
	public Integer deleteStaff(Integer id);
	public Integer addNewStaff(String name, String rollnum,String gender , String post_id);
	public Integer updateStudent(String name, String rollnum,String gender , String post_id,String id);
	
	public Integer getTeacherIdByName(String name);

	
}
