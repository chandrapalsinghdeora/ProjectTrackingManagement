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

import com.precise.model.Project;

@Repository
public class PMAssignedProjectsDaoImpl implements PMAssignedProjectsDao{

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
	public List<Project> getAllProject(int userId) {
		
		
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminassignedprojects(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAllProjects");
			callableSt.setInt(2, userId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
                pro.setProjectName(rs.getString("projectname"));
                pro.setProjectId(rs.getInt("projectid"));
                pro.setProjectDescription(rs.getString("projectdescription"));
                pro.setStartDate(rs.getDate("startdate"));
                pro.setEndDate(rs.getDate("enddate"));
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
	public List<Project> getAssignedProjects(int id) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminassignedprojects(?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAssignedProjects");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
		        pro.setProjectName(rs.getString("projectname"));
		        pro.setProjectDescription(rs.getString("projectdescription"));
		        pro.setStartDate(rs.getDate("startdate"));
		        pro.setEndDate(rs.getDate("enddate"));
				
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



	
	

}



