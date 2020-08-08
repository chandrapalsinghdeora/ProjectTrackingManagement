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
public class AdminPendingApprovalDaoImpl implements AdminPendingApprovalDao {

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

	
	public List<Employee> getAllPendingApproval()
	{
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_admin_pendingapproval(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAllPendingApproval");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			System.out.println("hyy");
			while (rs.next()) {
				Employee emp = new Employee();
				
				emp.setUserName(rs.getString("username"));
                emp.setUserId(rs.getInt("pk_userid"));
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
	public void approverequest(int id) {
		
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_admin_pendingapproval(?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "approverequest");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, id);
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
	public void rejectrequest(int id) {

		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_admin_pendingapproval(?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "rejectrequest");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, id);
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
	public List<Employee> getAllApproved() {
		
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_admin_pendingapproval(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAllApproved");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Employee emp = new Employee();
                emp.setUserName(rs.getString("username"));
                emp.setUserId(rs.getInt("pk_userid"));
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
	public void approverole(int userid, int roleid) {
		
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_admin_pendingapproval(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "approverole");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, userid);
			callableSt.setInt(6, roleid);
			callableSt.execute();
			
			
			// to update isroleassigned
			procedureCall = "{call proc_admin_pendingapproval(?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateisroleassigned");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, userid);
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
	
	
	
