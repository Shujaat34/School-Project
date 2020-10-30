package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.connection.DBConnection;
import com.school.dao.PeriodDAO;

public class PeriodDAOImpl implements PeriodDAO{

	Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT id , Period , duration FROM period";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Integer addPeriod(String period, String duration) {
		String sql = "INSERT INTO Period (period,duration) values(?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, period);
			ps.setString(2, duration);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public Integer deletePeriod(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from period where id=?";
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

	public Integer updatePeriod(String period, String duration,String id ) {
		PreparedStatement ps = null;
		String sql = "UPDATE period set period = ? , duration=? where id = ?";
		
		int row=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, period);
			ps.setString(2, duration);
			ps.setString(3, id);
			
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
}
