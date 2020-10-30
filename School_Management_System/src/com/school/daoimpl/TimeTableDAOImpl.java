package com.school.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.connection.DBConnection;
import com.school.dao.TimeTableDAO;

public class TimeTableDAOImpl implements TimeTableDAO{

	Connection con = DBConnection.getConnection();
	
	
	public ResultSet getDataResultSet(String CLASS , int day) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select tit.id,  s.name as '/Subject/', (case \r\n" + 
				"				when tit.day1=1 then \"Monday\"\r\n" + 
				"				when tit.day1=2 then \"Tuesday\"\r\n" + 
				"				when tit.day1=3 then \"Wednesday\"\r\n" + 
				"				when tit.day1=4 then \"Thursday\"\r\n" + 
				"				else \"Friday\"\r\n" + 
				"			end) as Day, tt.from , tt.to  ,(case \r\n" + 
				"				when p.id =1 then \"First\"\r\n" + 
				"				when p.id =2 then \"Second\"\r\n" + 
				"				when p.id =3 then \"Third\"\r\n" + 
				"				when p.id =4 then \"Fourth\"\r\n" + 
				"				when p.id =5 then \"Fifth\"\r\n" + 
				"				when p.id =6 then \"Sixth\"\r\n" +
				"				when p.id =7 then \"Seventh\"\r\n" +
				"				when p.id =8 then \"Eighth\"\r\n" + 
				"				else \"Nineth\"\r\n" + 
				"end) as Period\r\n" + 
				"	\r\n" + 
				" from time_table tit inner join ssubject s on\r\n" + 
				"s.id = tit.subject_id inner join ttime tt on tt.id = tit.ttime_id inner join class c on c.id = tit.class_id inner join\r\n" + 
				"period p on p.id = tit.period_id where c.class = ? and tit.day1=?";//  order by tit.day1 , p.period asc "; 

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, CLASS);
			ps.setInt(2, day);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Integer updateSubjectId(int subject_id, int id) {
		PreparedStatement ps = null;
		String sql = "update time_table set subject_id=? where id =?";
		
		int row=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, subject_id);
			ps.setInt(2, id);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	
}
