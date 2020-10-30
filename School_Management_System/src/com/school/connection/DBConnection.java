package com.school.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnection {
	
	static Connection con = null;
	public static Connection getConnection() {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:/Schooldb","root","");
		}
		}catch(SQLException e) {
			System.out.println("SQLEXCEPTTION in LoginBean Class");
			JOptionPane.showMessageDialog(null, "Open the Wamp Server First","Alert",JOptionPane.DEFAULT_OPTION);
			
		}catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Import the Jar FIle First of Connection","Alert",JOptionPane.DEFAULT_OPTION);
			System.out.println("Class not Found in LoginBean Class");
			
		}
		return con;
	}

}
