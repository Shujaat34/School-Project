package com.school.dao;

import java.sql.ResultSet;
import java.util.List;

public interface SubjectDAO {
	
	public ResultSet getDataResultSet();
	public Integer addNewSubject(String name, String chapters, String staff_id) ;
	public Integer updateSubject(String name, String chapters, String staff_id,String id);
	public Integer deleteStaff(Integer id);
	
	public int getSubjectIdByName(String subject);
	public List<String> getAllSubjects();
	

}
