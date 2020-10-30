package com.school.dao;

import java.sql.ResultSet;

public interface ResultDAO {
	public ResultSet getDataResultSet();
	public double getTotalAvgPercentage(String studentName);
	
}
