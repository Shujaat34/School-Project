package com.school.dao;

import java.sql.ResultSet;

public interface TimeTableDAO {
	public ResultSet getDataResultSet(String CLASS , int day);
	public Integer updateSubjectId(int subject_id, int id);
}
