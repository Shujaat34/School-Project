package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.connection.DBConnection;
import com.school.dao.AssignmentDAO;

public class AssignmentDAOImpl implements AssignmentDAO{

	Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT a.id , a.AssignmentName , ad.student_name , a.startDate , a.DueDate , s.name FROM assignment a INNER JOIN admission ad on ad.id = a.student_id\r\n" + 
				"INNER JOIN staff s on s.id=a.staff_id;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public Integer addAssignment(String AssignmentName, String student_id,String startDate,String dueDate ,String staffid) {
		String sql = "INSERT INTO assignment (AssignmentName,student_id,startDate,DueDate,staff_id) values(?,?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, AssignmentName);
			ps.setString(2, student_id);
			ps.setString(3, startDate);
			ps.setString(4, dueDate);
			ps.setString(5, staffid);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public Integer deleteAssignment(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from assignment where id=?";
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

	public Integer updateAssignment(String AssignmentName, String student_id,String startDate,String dueDate ,String staffid,Integer id) {
		PreparedStatement ps = null;
		String sql = "UPDATE assignment set AssignmentName=?,student_id=?,startDate=?,DueDate=?,staff_id=? where id = ?";
		
		int row=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, AssignmentName);
			ps.setString(2, student_id);
			ps.setString(3, startDate);
			ps.setString(4, dueDate);
			ps.setString(5, staffid);
			ps.setInt(6, id);
			
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

}
