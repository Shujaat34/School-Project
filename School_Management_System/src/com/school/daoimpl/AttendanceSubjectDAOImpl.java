package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.connection.DBConnection;
import com.school.dao.AttendanceSubjectDAO;

public class AttendanceSubjectDAOImpl implements AttendanceSubjectDAO {
	
	Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet(String student) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select ad.student_name as Student , sub.name as Subject, atsub.percentage from attendance_subject atsub inner join admission ad on ad.id = atsub.admission_id \r\n" + 
				"inner join ssubject sub on sub.id =atsub.subject_id where ad.Student_Name=? order by ad.student_name ;";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, student);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public List<String> getAllStudents() {
		List<String> studentList = new ArrayList<>();  
		try {
			String sql="Select student_name from admission" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				studentList.add(rs.getString("student_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;
	}
	
	
	public List<Integer> getAllAttendance(String student) {
		List<Integer> studentList = new ArrayList<>();  
		try {
			String sql="select atsub.percentage from attendance_subject atsub inner join admission ad on ad.id = atsub.admission_id \r\n" + 
					"inner join ssubject sub on sub.id =atsub.subject_id where ad.Student_Name=? order by ad.student_name ;" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				studentList.add(rs.getInt("atsub.percentage"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;
	}

}
