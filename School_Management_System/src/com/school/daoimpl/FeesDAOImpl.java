package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.connection.DBConnection;
import com.school.dao.FeesDAO;

public class FeesDAOImpl implements FeesDAO{

	Connection con = DBConnection.getConnection();
	
	@Override
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql =  "SELECT f.id,ad.student_name , f.amount , f.status_ , f.month_ FROM fees f "
				+ "INNER JOIN admission ad ON f.Admission_id = ad.id;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public Integer addFees(String amount, String status,String month , String admission_id) {
		String sql = "INSERT INTO fees (amount,status_,month_,admission_id) values (?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, amount);
			ps.setString(2, status);
			ps.setString(3, month);
			ps.setString(4, admission_id);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public Integer deleteFees(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from fees where id=?";
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
}
