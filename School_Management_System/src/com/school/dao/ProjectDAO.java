package com.school.dao;

import java.sql.ResultSet;

public interface ProjectDAO {
	public ResultSet getDataResultSet();
	public Integer getProjectIdByName(String name);
	
	public Integer addProject(String name, String duration);
	public Integer deleteProject(Integer id);
	public Integer updateProject(String name, String duration,String id );
	
	public boolean checkIdExistInChild(String id) ;
	
}
