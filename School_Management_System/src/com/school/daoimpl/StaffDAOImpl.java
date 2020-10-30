package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.connection.DBConnection;
import com.school.dao.StaffDAO;

public class StaffDAOImpl implements StaffDAO{

	Connection con = DBConnection.getConnection();
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select s.id , s.name,s.Rollnum,s.Gender , p.post,p.salary from staff s inner join post p on p.id=s.Post_id;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getSpecificResultSet(String post) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select s.id , s.name,s.Rollnum,s.Gender , p.post,p.salary from staff s inner join post p on p.id=s.Post_id where p.post=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, post);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int getEmployees(String post) {
		String sql = "Select count(s.name) from staff s inner join post p on p.id=s.Post_id where  p.post=?";
		Integer numOfEmp =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, post);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				numOfEmp = rs.getInt("count(s.name)");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOfEmp;	 
	}

	public int getAllEmployees() {
		String sql = "Select count(s.name) from staff s inner join post p on p.id=s.Post_id";
		Integer numOfEmp =0; 
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				numOfEmp += rs.getInt("count(s.name)");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOfEmp;
	}
	@Override
	public Integer addNewStaff(String name, String rollnum, String gender, String post_id) {
		String sql = "insert into staff(name,rollnum,gender,post_id) values(?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, rollnum);
			ps.setString(3, gender);
			ps.setString(4, post_id);
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public Integer deleteStaff(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from staff where id=?";
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
	@Override
	public Integer updateStudent(String name, String rollnum, String gender, String post_id,String id) {
		PreparedStatement ps = null;
		String sql = "update staff set name=?,rollnum=?,gender=?,post_id=? where id=?";
		
		int row=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, rollnum);
			ps.setString(3, gender);
			ps.setString(4, post_id);
			ps.setString(5, id);
			
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	@Override
	public Integer getTeacherIdByName(String name) {
		String sql = "Select * from staff where name=?";
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
