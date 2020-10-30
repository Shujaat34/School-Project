package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.school.connection.DBConnection;
import com.school.dao.GroupDAO;

public class GroupDAOImpl implements GroupDAO{

	Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
//		String sql = "SELECT g.id, ad.student_name as 'Group Leader' , gr.student_name as 'Group Member' , p.project FROM admission ad \r\n" + 
//				"INNER JOIN groups g on g.groupLeader_id = ad.id \r\n" + 
//				"INNER JOIN groupmember gr on gr.id = g.groupmember_id \r\n" + 
//				"INNER JOIN projects p on p.id= g.project_id ;";
		
		String sql =  "Select ad.id ,ad.student_name,gm.student_name , p.project from admission ad inner join groups g on g.groupLeader_id = ad.id inner join groupmember gm on \r\n" + 
				"g.id = gm.groupleader_id inner join Projects p on p.id = g.project_id;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean checkGroupMemberIdExist(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * from groups where groupmember_id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkGroupLeaderIdExist(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * from groups where groupleader_id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<String> getAllGroupMembers() {
		List<String> groupmemberList = new ArrayList<>();   
		try {
//			String sql="SELECT student_name from admission WHERE id NOT IN (SELECT gm.admission_id FROM groupmember gm \r\n" + 
//					"INNER JOIN groups g on gm.id= g.GroupMember_id ) AND id NOT IN\r\n" + 
//					"(SELECT g.GroupLeader_id  FROM admission ad INNER JOIN groups g on g.GroupLeader_id= ad.id GROUP BY ad.id)" ;
			String sql = "SELECT student_name from admission ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				groupmemberList.add(rs.getString("student_name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return groupmemberList;

	}
	public List<String> getAllProjects() { 
		List<String> projectList = new ArrayList<>();   
		try {
			String sql="SELECT project from projects;" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectList.add(rs.getString("project"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return projectList;

	}
	public Integer getGroupLeaderIdByName(String name) {
		String sql = "SELECT id from admission where student_name = ? limit 1";
		Integer id =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;	
	}
	public Integer getGroupMemberIdByName(String name) {
		String sql = "SELECT id from admission where student_name = ? limit 1";
		Integer id =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;	
	}
	public Integer addGroupLeader(String groupLeader_id,String Project_id) {
		String sql = "INSERT INTO groups (groupLeader_id,Project_id) values(?,?)";
		int row = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, groupLeader_id);
			ps.setString(2, Project_id);
			
			row = ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;	
	}
	public Integer getOriginalGroupLeaderId(String groupLeader_id) {
		String sql = "SELECT id from groups where groupLeader_id = ?";
		Integer id =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, groupLeader_id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;	
	}
	
	//INSERT INTO groups (groupLoeader_id,Project_id) values(?,?);
	public Integer addGroupFirstMember(String firstMember, String admission_id, String groupleader_id ) {
		String sql = "INSERT INTO groupmember (student_name , admission_id , groupleader_id) values (?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, firstMember);
			ps.setString(2, admission_id);
			ps.setString(3, groupleader_id);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public Integer addGroupSecondMember(String secondMember, String admission_id, String groupleader_id ) {
		String sql = "INSERT INTO groupmember (student_name , admission_id , groupleader_id) values (?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, secondMember);
			ps.setString(2, admission_id);
			ps.setString(3, groupleader_id);
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public Integer addGroupthirdMember(String thirdMember, String admission_id, String groupleader_id ) {
		String sql = "INSERT INTO groupmember (student_name , admission_id , groupleader_id) values (?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, thirdMember);
			ps.setString(2, admission_id);
			ps.setString(3, groupleader_id);
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
//DELETE FROM groups WHERE id = ?
	
	public Integer deleteGroupLeader(Integer id) {
		PreparedStatement ps = null;
		String sql ="DELETE FROM groups WHERE id = ?";
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public Integer deleteGroup(Integer groupleader_id) {
		PreparedStatement ps = null;
		String sql ="DELETE FROM groupmember WHERE groupleader_id = ?";
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, groupleader_id);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}


	

	
}
