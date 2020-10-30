package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.bean.LoginBean;
import com.school.connection.DBConnection;
import com.school.dao.LoginDAO;

public class LoginDAOImpl implements LoginDAO{

	Connection con = DBConnection.getConnection();
	@Override
	public boolean letLoginin(LoginBean loginBean) {
		PreparedStatement ps = null;
		String sql = "Select * from login where username=? and password=?";
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
