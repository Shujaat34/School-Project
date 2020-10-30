package com.school.dao;

import java.sql.ResultSet;

public interface FeesDAO {
	public ResultSet getDataResultSet();
	public Integer addFees(String amount, String status,String month , String admission_id);
	public Integer deleteFees(Integer id);
}
