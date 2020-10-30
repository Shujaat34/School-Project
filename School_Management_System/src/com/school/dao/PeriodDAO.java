package com.school.dao;

import java.sql.ResultSet;

public interface PeriodDAO {
	public ResultSet getDataResultSet();
	public Integer addPeriod(String period, String duration);
	public Integer deletePeriod(Integer id);
	public Integer updatePeriod(String period, String duration,String id );
}
