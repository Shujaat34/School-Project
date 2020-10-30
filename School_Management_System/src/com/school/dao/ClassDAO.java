package com.school.dao;

import java.sql.ResultSet;
import java.util.List;

public interface ClassDAO {

	public ResultSet getDataResultSet();
	public int getClassIdByName(String standard);
	public int getStudents(String standard);
	public int updateStudentsAfterAddnew(String newStudentquantity,String standard);
	public int getIdByName(String CLASS);
	public List<String> getAllTeachers();
	public ResultSet getSpecificResultSet(String teacher) ;
	public List<String> getAllClasses();
}
