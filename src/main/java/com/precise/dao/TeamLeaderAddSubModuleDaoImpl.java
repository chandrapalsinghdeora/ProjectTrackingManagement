package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Project;

@Repository
public class TeamLeaderAddSubModuleDaoImpl implements TeamLeaderAddSubModuleDao{

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
	public List<Project> getallprojects(int userid) {
		
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_teamleaderaddsubmodule(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getalltlprojects");
			callableSt.setInt(2, userid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pr = new Project();
		        pr.setProjectId(rs.getInt("pk_projectid"));
                pr.setProjectName(rs.getString("projectname"));
                pr.setModuleNameIndividually(rs.getString("mod_name"));
                pr.setSubModuleId(rs.getInt("pk_submod_id"));
                pr.setSubModuleNameIndividually(rs.getString("submod_name"));
                pr.setSubModuleDescriptionIndividually(rs.getString("submod_description"));
				
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
	public JSONArray getmodules(int projectid, int userid) {
		final String procedureCall = "{call proc_teamleaderaddsubmodule(?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getmodules");
			callableSt.setInt(2, userid);
			callableSt.setInt(3, projectid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject obj = null;
			while (rs.next()) {
				obj = new JSONObject();
	        	obj.put("moduleid", rs.getInt("pk_mod_id"));
	        	obj.put("modulename", rs.getString("mod_name"));
	        	array.put(obj);
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
		return array;

	}

	@Override
	public void savesubmodules(Project pro) {
		Connection connection = null;
		try{
			
		 	connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);	
			
			List<String> submodulename = pro.getSubModuleName();
			List<String> submoduledesc = pro.getSubModuleDescription();
		 
			CallableStatement savesubmodules =  connection.prepareCall("{call proc_teamleaderaddsubmodule(?,?,?,?,?,?)}");
		    
		 	for(int i=0;i<submodulename.size();i++){
		 		savesubmodules.setString(1,"savesubmodules");
		 		savesubmodules.setInt(2, 0);
		 		savesubmodules.setInt(3, pro.getProjectId());
		 		savesubmodules.setString(4, submodulename.get(i));
		 		savesubmodules.setString(5, submoduledesc.get(i));
		 		savesubmodules.setInt(6, pro.getModuleId());
				
		 		savesubmodules.addBatch();
			  }
		 	    
		 	savesubmodules.executeBatch();
		 	connection.commit();
		}catch (Exception e) {
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
	public List<Project> getalltlprojects(int userid) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_teamleaderaddsubmodule(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallprojects");
			callableSt.setInt(2, userid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pr = new Project();
		        pr.setProjectId(rs.getInt("pk_projectid"));
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
