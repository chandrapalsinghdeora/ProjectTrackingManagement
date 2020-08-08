package com.precise.dao;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Employee;


@Repository
public class logindaoimpl implements logindao {

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


	public List<Employee> getAlldesignation() {
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_login(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectdesignations");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpDesignationId(rs.getInt("team_id"));
                emp.setEmpDesignation(rs.getString("team_name"));
				
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
	public void sendtheinformation(Employee emp) {
		
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_login(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "sendtheinformation");
			callableSt.setString(2, emp.getUserName());
			callableSt.setString(3, emp.getUserPassword());
			callableSt.setString(4, emp.getName());
			callableSt.setString(5, emp.getEmail());
			callableSt.setString(6, emp.getEmpDesignation());
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
	
	
	@Override
	public List<Employee> get()
	{
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_login(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "get");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Employee emp = new Employee();
                
                emp.setUserId(rs.getInt("pk_userid"));
                emp.setUserName(rs.getString("username"));
                emp.setUserPassword(rs.getString("password"));
                emp.setName(rs.getString("name"));
                
                emp.setRoleId(rs.getInt("fk_roleid"));
                emp.setRoleName(rs.getString("rolename"));
				emp.setIsVerified(rs.getInt("isverified"));
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
	public List<Employee> verifymail(String mail) {
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_login(?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "verifyemail");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, mail);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Employee emp = new Employee();
                
                emp.setEmail(rs.getString("email"));
               
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
	public void updatetokenindb(String mail, int token) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_login(?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatetoken");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, mail);
			callableSt.setString(6, null);
			callableSt.setInt(7, token);
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

	@Override
	public void updateuserpassword(int token, String password) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_login(?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatepass");
			callableSt.setString(2, null);
			callableSt.setString(3, password);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setString(6, null);
			callableSt.setInt(7, token);
			callableSt.execute();

			
			procedureCall = "{call proc_login(?,?,?,?,?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "resettoken");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setString(6, null);
			callableSt.setInt(7, token);
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
