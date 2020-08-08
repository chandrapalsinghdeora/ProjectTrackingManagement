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
public class EmployeeAssignedModuleDaoImpl implements EmployeeAssignedModuleDao{

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
	public List<Project> getAssignedSubModules(int id, int userId) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_employeeassignedwork(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAssignedsubModules");
			callableSt.setInt(2, userId);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			while (rs.next()) {
				Project pro = new Project();
		        pro.setSubModuleNameIndividually(rs.getString("submod_name"));
		        pro.setSubModuleDescriptionIndividually(rs.getString("submod_description"));
		        pro.setStartDate(rs.getDate("start_date"));
		        pro.setEndDate(rs.getDate("end_date"));
                listRole.add(pro);
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
	public List<Project> getAllProjects(int userId) {
		
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_employeeassignedwork(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "empallprojects");
			callableSt.setInt(2, userId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
				pro.setProjectName(rs.getString("projectname"));
				pro.setModuleNameIndividually(rs.getString("mod_name"));
                pro.setSubModuleNameIndividually(rs.getString("submod_name"));
                pro.setSubModuleDescriptionIndividually(rs.getString("submod_description"));
                pro.setStartDate(rs.getDate("start_date"));
                pro.setEndDate(rs.getDate("end_date"));
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
	public List<Project> getAllTProjects(int userId) {
		
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_employeeassignedwork(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "empalltprojects");
			callableSt.setInt(2, userId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
                pro.setProjectName(rs.getString("projectname"));
                pro.setProjectId(rs.getInt("projectid"));
                pro.setTaskNameIndividually(rs.getString("task_name"));
                pro.setTaskDescriptionIndividually(rs.getString("task_description"));
                pro.setModuleNameIndividually(rs.getString("mod_name"));
                pro.setSubModuleNameIndividually(rs.getString("submod_name"));
                pro.setStartDate(rs.getDate("start_date"));
                pro.setEndDate(rs.getDate("end_date"));
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
	public JSONArray getmodules(int projectId,int userId) {
		
        JSONArray array=new JSONArray();
		final String procedureCall = "{call proc_employeeassignedwork(?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "modulesofthisproject");
			callableSt.setInt(2, userId);
			callableSt.setInt(3, projectId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("moduleid", rs.getInt("pk_mod_id"));
	        	object.put("modulename",  rs.getString("mod_name"));
	        	array.put(object);
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
	public JSONArray gettmodules(int projectId, int userId) {
        JSONArray array=new JSONArray();
		final String procedureCall = "{call proc_employeeassignedwork(?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "modulesofthistproject");
			callableSt.setInt(2, userId);
			callableSt.setInt(3, projectId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("moduleid", rs.getInt("pk_mod_id"));
	        	object.put("modulename",  rs.getString("mod_name"));
	        	array.put(object);
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
	public JSONArray getjsonsubmodules(int moduleId, int userId) {
		
        JSONArray array=new JSONArray();
		final String procedureCall = "{call proc_employeeassignedwork(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAssignedtsubModules");
			callableSt.setInt(2, userId);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, moduleId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("submoduleid", rs.getInt("pk_submod_id"));
	        	object.put("submodulename",  rs.getString("submod_name"));
	        	array.put(object);
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
	public List<Project> getAssignedTasks(int id, int userId) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_employeeassignedwork(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAssignedtasks");
			callableSt.setInt(2, userId);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setString(5, null);
			callableSt.setInt(6, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			while (rs.next()) {
				Project pro = new Project();
		        pro.setTaskNameIndividually(rs.getString("task_name"));
		        pro.setTaskDescriptionIndividually(rs.getString("task_description"));
		        pro.setStartDate(rs.getDate("start_date"));
		        pro.setEndDate(rs.getDate("end_date"));
                listRole.add(pro);
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


	





