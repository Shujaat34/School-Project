package com.school.dao;

import java.sql.ResultSet;

public interface AssignmentDAO {
	public ResultSet getDataResultSet();
	public Integer addAssignment(String AssignmentName, String student_id,String startDate,String dueDate ,String staffid);
	public Integer deleteAssignment(Integer id);
	public Integer updateAssignment(String AssignmentName, String student_id,String startDate,String dueDate ,String staffid,Integer id);
}
