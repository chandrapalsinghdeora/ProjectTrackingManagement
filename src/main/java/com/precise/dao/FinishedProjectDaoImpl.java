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
public class FinishedProjectDaoImpl implements FinishedProjectDao{

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
	public List<Project> getAllFinishedProjects(int userId) {
	        List<Project> listRole = new ArrayList<Project>();
			final String procedureCall = "{call proc_adminviewgraphprogress(?)}";
			Connection connection = null;
			try {
				connection = jdbctemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "getFinish");
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					 Project pr = new Project();
				     
					 pr.setProjectId(rs.getInt("projectid"));
	                 pr.setProjectName(rs.getString("projectname"));
					
	                listRole.add(pr);
					
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
	public List<Project> getAllPMFinishedProjects(int userId) {
		  List<Project> listRole = new ArrayList<Project>();
			final String procedureCall = "{call proc_adminviewgraphprogress(?,?)}";
			Connection connection = null;
			try {
				connection = jdbctemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "getPMFinshedProject");
				callableSt.setInt(2, userId);
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					 Project pr = new Project();
				     
					 pr.setProjectId(rs.getInt("projectid"));
	                 pr.setProjectName(rs.getString("projectname"));
					
	                listRole.add(pr);
					
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



