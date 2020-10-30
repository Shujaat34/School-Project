package com.school.bean;

public class GroupBean {
	private String id;
	private String groupLeader_id;
	private String groupMember_id;
	private ProjectBean projectbean;
	private GroupMemberBean groupmemberBean;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupLeader_id() {
		return groupLeader_id;
	}
	public void setGroupLeader_id(String groupLeader_id) {
		this.groupLeader_id = groupLeader_id;
	}
	public String getGroupMember_id() {
		return groupMember_id;
	}
	public void setGroupMember_id(String groupMember_id) {
		this.groupMember_id = groupMember_id;
	}	
	public ProjectBean getProjectbean() {
		return projectbean;
	}
	public void setProjectbean(ProjectBean projectbean) {
		this.projectbean = projectbean;
	}
	public GroupMemberBean getGroupmemberBean() {
		return groupmemberBean;
	}
	public void setGroupmemberBean(GroupMemberBean groupmemberBean) {
		this.groupmemberBean = groupmemberBean;
	}
	

	
}
