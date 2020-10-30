package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.connection.DBConnection;
import com.school.dao.ResultDAO;

public class ResultDAOImpl implements ResultDAO{
	Connection con = DBConnection.getConnection();
	
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql =  "SELECT r.id, ad.student_name ,s.name , r.percentage FROM result r INNER JOIN ssubject s ON s.id=r.subject_id INNER JOIN admission ad ON\r\n" + 
				"ad.id = r.Student_id ;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public double getTotalAvgPercentage(String studentName) {
		String sql =  "SELECT r.percentage FROM result r INNER JOIN ssubject s ON s.id=r.subject_id INNER JOIN admission ad ON\r\n" + 
				"ad.id = r.Student_id where ad.student_name=? ;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count =0;
		double sum=0;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentName);
			rs = ps.executeQuery();
			while(rs.next()) {
				count++;
				sum += rs.getInt("r.percentage");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count==0){
			return -1;
		}
		return sum/count;
	}
}
