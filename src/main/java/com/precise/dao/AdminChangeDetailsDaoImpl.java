package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Employee;

@Repository
public class AdminChangeDetailsDaoImpl implements AdminChangeDetailsDao {

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
	public List<Employee> getadminpreviousdetails(int userid) {
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_admin_changedetails(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getpreviousdetails");
			callableSt.setInt(2, userid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Employee emp = new Employee();
                
                emp.setUserId(rs.getInt("pk_userid"));
                emp.setEmail(rs.getString("email"));
                emp.setUserName(rs.getString("username"));
                emp.setUserPassword(rs.getString("password"));
                emp.setName(rs.getString("name"));
                emp.setEmpDesignation(rs.getString("designation"));
				
                listRole.add(emp);
				
				x=x+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return listRole;
		
	}

	@Override
	public void updateDetails(Employee emp, int userid) {
		

		 String procedureCall = "";
			Connection connection = null;
			try {

				connection = jdbctemplate.getDataSource().getConnection();
				connection.setAutoCommit(false);
				procedureCall = "{call proc_admin_changedetails(?,?,?,?,?,?)}";
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "updatedetails");
				callableSt.setInt(2, userid);
				callableSt.setString(3, emp.getName());
				callableSt.setString(4, emp.getUserName());
				callableSt.setString(5, emp.getEmail());
				callableSt.setString(6, emp.getUserPassword());
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
