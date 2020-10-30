package com.school.dao;

import java.sql.ResultSet;
import java.util.List;

public interface AttendanceSubjectDAO {
	
	public ResultSet getDataResultSet(String student);
	public List<String> getAllStudents();
	public List<Integer> getAllAttendance(String student);

}
