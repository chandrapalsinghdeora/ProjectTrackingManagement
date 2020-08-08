package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Employee;
import com.precise.model.Project;

@Repository
public class SuperAdminAssignProjectDaoImpl implements SuperAdminAssignProjectDao{

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
	public List<Project> getAllProject() {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminassignproject(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "sagetallproject");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
                pro.setProjectName(rs.getString("projectname"));
                pro.setProjectId(rs.getInt("projectid"));
                listRole.add(pro);
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
	public void assignthemodule(Project pro) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminassignproject(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "saassignprojecttopm");
			callableSt.setInt(2, pro.getProjectId());
			callableSt.setInt(3, pro.getUserId());
			callableSt.setInt(4, 0);
			callableSt.setDate(5, new java.sql.Date(pro.getStartDate().getTime()));
			callableSt.setDate(6, new java.sql.Date(pro.getEndDate().getTime()));
			callableSt.execute();
			
			
			
			// to update the isassigned variable 
			
			procedureCall = "{call proc_adminassignproject(?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "saupdateisassigned");
			callableSt.setInt(2, pro.getProjectId());
			
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
	public List<Employee> getallpm() {
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_adminassignproject(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallpm");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Employee emp = new Employee();
                emp.setUserId((rs.getInt("pk_userid")));
                emp.setName(rs.getString("name"));
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
	
	public java.sql.Date dateFormatter(Date date) {
		String dateForMySql = "";
		java.sql.Date sqlDate = null;
		try {
			if (date == null) {
				dateForMySql = null;
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				dateForMySql = simpleDateFormat.format(date);
				java.util.Date stringToDate = simpleDateFormat.parse(dateForMySql);
				sqlDate = new java.sql.Date(stringToDate.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}

}
