package com.precise.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Bug;

@Repository
public class NewResponseDaoImpl implements NewResponseDao {

	@Autowired
	private NamedParameterJdbcTemplate namedparameterjdbctemplate;
	
	@Autowired
	private JdbcTemplate jdbctemplate;

	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	public NamedParameterJdbcTemplate getNamedparameterjdbctemplate() {
		return namedparameterjdbctemplate;
	}

	public void setNamedparameterjdbctemplate(NamedParameterJdbcTemplate namedparameterjdbctemplate) {
		this.namedparameterjdbctemplate = namedparameterjdbctemplate;
	}
	@Override
	public void sendtheresponse(Bug emp,String fname){
		
		String procedureCall = "";
		Connection connection = null;
		try {
			String p=emp.getStatus();
         if(p.equalsIgnoreCase("fixed"))
         {
			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call pro_BugTracking(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatedev");
			callableSt.setInt(2,emp.getBugNo());
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setString(6, null);
			callableSt.setString(7, null);
			callableSt.setString(8, null);
			callableSt.setString(9, null);
			callableSt.setString(10, null);
			callableSt.setString(11, null);
			callableSt.setString(12, null);
			callableSt.setString(13, emp.getStatus());
			callableSt.setString(14, emp.getSummary());
			callableSt.setString(15, emp.getDelievered());
			callableSt.setInt(16,0);
			callableSt.setInt(17,0);
			callableSt.setInt(18,0);
			callableSt.setString(19, fname);
			
			callableSt.execute();
			connection.commit();
         }
         else
         {
        	 connection = jdbctemplate.getDataSource().getConnection();
 			connection.setAutoCommit(false);
 			procedureCall = "{call pro_BugTracking(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
 			CallableStatement callableSt = connection.prepareCall(procedureCall);
 			callableSt.setString(1, "updatedev1");
 			callableSt.setInt(2,emp.getBugNo());
 			callableSt.setString(3, null);
 			callableSt.setString(4, null);
 			callableSt.setString(5, null);
 			callableSt.setString(6, null);
 			callableSt.setString(7, null);
 			callableSt.setString(8, null);
 			callableSt.setString(9, null);
 			callableSt.setString(10, null);
 			callableSt.setString(11, null);
 			callableSt.setString(12, null);
 			callableSt.setString(13, emp.getStatus());
 			callableSt.setString(14, emp.getSummary());
 			callableSt.setString(15, null);
 			callableSt.setInt(16,0);
 			callableSt.setInt(17,0);
 			callableSt.setInt(18,0);
 			callableSt.setString(19, fname);
 			callableSt.execute();
 			connection.commit();
         }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	@Override
	public void closethebug(Bug emp,int bugno){
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call pro_BugTracking(?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateqa");
			callableSt.setInt(2, bugno);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setString(6, null);
			callableSt.setString(7, null);
			callableSt.setString(8,"Closed" );
			callableSt.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
}
}