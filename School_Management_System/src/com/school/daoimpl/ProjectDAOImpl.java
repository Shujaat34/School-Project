package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.connection.DBConnection;
import com.school.dao.ProjectDAO;

public class ProjectDAOImpl implements ProjectDAO{
	Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from Projects;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Integer addProject(String name, String duration) {
		String sql = "INSERT INTO projects (project,duration) values(?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, duration);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public Integer deleteProject(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from projects where id=?";
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
	
	public Integer updateProject(String name, String duration,String id ) {
		PreparedStatement ps = null;
		String sql = "UPDATE Projects set Project = ? , duration=? where id = ?";
		
		int row=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, duration);
			ps.setString(3, id);
			
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
	public boolean checkIdExistInChild(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * from groups where project_id = ?";
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
	
	
	public Integer getProjectIdByName(String name) {
		String sql = "SELECT id from projects where project = ? limit 1";
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
}
