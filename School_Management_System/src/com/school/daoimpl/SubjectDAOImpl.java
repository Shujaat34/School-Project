package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.connection.DBConnection;
import com.school.dao.SubjectDAO;

public class SubjectDAOImpl implements SubjectDAO{
Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select s.id ,s.name , s.chapters , st.name , st.gender from ssubject s inner join staff st on st.id = s.staff_id"; 
			
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Integer addNewSubject(String name, String chapters, String staff_id) {
		String sql = "insert into ssubject(name,chapters,staff_id) values(?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, chapters);
			ps.setString(3, staff_id);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public Integer updateSubject(String name, String chapters, String staff_id,String id) {
		PreparedStatement ps = null;
		String sql = "update ssubject set name=?,chapters=?,staff_id=? where id=?";
		
		int row=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, chapters);
			ps.setString(3, staff_id);
			ps.setString(4, id);
			
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public Integer deleteStaff(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from ssubject where id=?";
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
	
	public int getSubjectIdByName(String subject) {
		String sql = "Select id from ssubject where name=?";
		Integer id =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, subject);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;	
	}
	
	public List<String> getAllSubjects() {
		List<String> teacherList = new ArrayList<>(); 
		try {
		
			String sql="Select name from ssubject" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				teacherList.add(rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacherList;

	}
}
