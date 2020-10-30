package com.school.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.school.connection.DBConnection;



public class Validation {
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	Connection con = DBConnection.getConnection();
	
	public boolean isNumberUnique(String num,String table,String column) {
		String sql = "Select * from "+table+" where "+column+"=?";
		try {			
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
			rs = ps.executeQuery();
			while(rs.next()) {
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean isNumberDuplicate(String num,String table,String column) {
		String sql = "Select * from "+table+" where "+column+"=?";
		try {			
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
			rs = ps.executeQuery();
			while(rs.next()) {
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean isStringUnique(String name,String table,String column) {
		String sql = "Select * from "+table+" where "+column+"=?";
		try {			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	// Name Validate
	public  boolean nameValidate(String name) {
		boolean namecheck =  Pattern.matches("[a-zA-Z[^0-9]]{1,30}",name);
			if (namecheck == true && !(name.isEmpty()) ) {
				namecheck = true;
			}
			else {
				namecheck = false;
			}
			return namecheck;
	}
	
	// ContactNumber Validate
	public boolean contactNumberValidation(String number) {
		boolean numcheck = Pattern.matches("[0-9]{11}",number );
		if(numcheck == true)
		return true;
		
		
		return false;
	}
	//
	public boolean nicValidiate(String number) {
		boolean numcheck = Pattern.matches("[0-9]{13}",number );
		if(numcheck == true)
		return true;
		
		
		return false;
	}
	////////
	public boolean isNumber(String number) {
		boolean numcheck = Pattern.matches("[0-9]{1,20}",number);
		return numcheck;
	}
	//////  For Fees/Number Valididation method      /////////
	
	public boolean isFloat(String number) {
		boolean numcheck = Pattern.matches("^(\\d+\\.)?\\d+$", number);

		return numcheck;
	}
	public boolean feesValidiation(String num) {
		boolean feescheck = Pattern.matches("[0-9]{1,8}",num);
		if(feescheck == true)
		return true;
		
		else
		return false;
	}
	
	public boolean ageValidiation(String age) {
		boolean agecheck = Pattern.matches("[0-9]{1,2}", age);
		if(agecheck == true)
			return true;
		
		else
			return false;
	}
	
	public boolean gendercheck(String gender) {
		
		if(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))
			return true;
		
		else
			return false;
	}
	

}
