package com.school.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.school.bean.AdmissionBean;
import com.school.bean.ClassBean;
import com.school.connection.DBConnection;
import com.school.dao.AdmissionDAO;

public class AdmissionDAOImpl implements AdmissionDAO{
	
	Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select a.id ,a.student_name,a.father_name , a.Parent_contact,a.dob,c.class from Admission a inner join class c on c.id=a.class_id";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Integer addNewStudent(String Sname, String Fname, Timestamp dob , String Pcontact , String class_id ) {
		String sql = "insert into admission(student_name,father_name,dob,Parent_contact,class_id) values(?,?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, Sname);
			ps.setString(2, Fname);
			ps.setTimestamp(3, dob);
			ps.setString(4, Pcontact);
			ps.setString(5, class_id);
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public ResultSet getSpecificResultSet(String CLASS) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select a.id ,a.student_name,a.father_name , a.Parent_contact,a.dob,c.class from Admission a inner join class c on a.class_id=c.id where c.class=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, CLASS);
			
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public Integer deleteStudent(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from admission where id=?";
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
	public AdmissionBean getDataById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from admission where id=?";
		AdmissionBean ab = new AdmissionBean();
		ClassBean cb = new ClassBean();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs =ps.executeQuery();
			while(rs.next()) {
				ab.setId(rs.getString("id"));
				ab.setName(rs.getString("student_name"));
				ab.setFname(rs.getString("father_name"));
				ab.setDob(rs.getTimestamp("dob"));
				ab.setParentContact(rs.getString("Parent_contact"));
				cb.setId("class_id");
				ab.setClassbean(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ab;
	}
	
	public Integer updateStudent(String name, String fname, Timestamp dob, String contact,String class_id,String id) {
		PreparedStatement ps = null;
		String sql = "update admission set student_name=?,father_name=?,dob=?,parent_contact=?,class_id=? where id=?";
		
		int row=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, fname);
			ps.setTimestamp(3, dob);
			ps.setString(4, contact);
			
			
			ps.setString(5,class_id);
			ps.setString(6, id);
			
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
	public Integer getStudentIdbyName(String name) {
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
	
	public List<String> getAllStudents() {
		List<String> studentNames = new ArrayList<>();  
		try {
			String sql="Select student_name from admission;" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				studentNames.add(rs.getString("student_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentNames;

	}
	
	public int getAllStudentsCount() {
		int count =0;
		try {
			String sql="Select count(admission.id) as Scount from admission;" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("Scount");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;

	}
	
}


