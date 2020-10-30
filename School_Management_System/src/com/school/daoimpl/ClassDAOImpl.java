package com.school.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.school.connection.DBConnection;
import com.school.dao.ClassDAO;

public class ClassDAOImpl implements ClassDAO{

	Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select c.id, c.class,c.students,s.name as Teacher from class c inner join staff s on c.staff_id= s.id inner join post p on s.Post_id = p.id where p.post ='teacher'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	@Override
	public int getStudents(String standard) {
		String sql = "Select * from class where class=?";
		Integer numOfStudents =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, standard);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				numOfStudents = rs.getInt("Students");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOfStudents;	
	}


	@Override
	public int getClassIdByName(String standard) {
		String sql = "Select * from class where class=?";
		Integer id =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, standard);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;	
	}

	@Override
	public int updateStudentsAfterAddnew(String newStudentquantity,String standard) {
		String sql = "update class set students=? where class=?";
		Integer id =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newStudentquantity);
			ps.setString(2, standard);
			
			id = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int getIdByName(String CLASS) {
		String sql = "Select * from class where class=?";
		Integer id =0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, CLASS);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;	
	}

	public List<String> getAllClasses() {
		List<String> teacherList = new ArrayList<>(); 
		try {
		
			String sql="Select class from class" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				teacherList.add(rs.getString("class"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return teacherList;

	}
	
	
	public List<String> getAllTeachers() {
		List<String> teacherList = new ArrayList<>(); 
		try {
		
			String sql="Select s.name from class c inner join staff s on c.staff_id= s.id inner join post p on s.Post_id = p.id where p.post= 'teacher'" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				teacherList.add(rs.getString("s.name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return teacherList;

	}
	public ResultSet getSpecificResultSet(String teacher) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select c.id, c.class,c.students,s.name from class c inner join staff s on c.staff_id= s.id inner join post p on s.Post_id = p.id where s.name=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacher);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
