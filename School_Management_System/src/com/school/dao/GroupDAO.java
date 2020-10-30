package com.school.dao;

import java.sql.ResultSet;
import java.util.List;

public interface GroupDAO {
	public ResultSet getDataResultSet();
	public List<String> getAllGroupMembers();
	public List<String> getAllProjects();
	public Integer getGroupLeaderIdByName(String name);
	public Integer getGroupMemberIdByName(String name);
	public Integer addGroupFirstMember(String groupLeaderId, String firstMember, String project_id );
	public Integer addGroupSecondMember(String groupLeaderId, String firstMember, String project_id );
	public Integer addGroupthirdMember(String groupLeaderId, String firstMember, String project_id );
	public Integer deleteGroup(Integer groupleader_id);
	public boolean checkGroupLeaderIdExist(String id);
	public boolean checkGroupMemberIdExist(String id);
	
	public Integer addGroupLeader(String groupLeader_id,String Project_id);
	public Integer getOriginalGroupLeaderId(String groupLeader_id);
	public Integer deleteGroupLeader(Integer id) ;
	
	
	
	
	
}
