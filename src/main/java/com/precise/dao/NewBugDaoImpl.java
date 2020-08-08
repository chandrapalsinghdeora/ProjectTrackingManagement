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

import com.precise.model.Bug;

@Repository
public class NewBugDaoImpl implements NewBugDao {

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
	public void sendtheinformation(Bug emp,String fname,int id){
		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call pro_BugTracking(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertBug");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, emp.getProjectId());
			callableSt.setString(4,  emp.getBugName());
			callableSt.setString(5, emp.getBugType());
			callableSt.setInt(6, emp.getModuleId());
			callableSt.setInt(7,emp.getSubModuleId());
			callableSt.setString(8,  emp.getBugStatus());
			callableSt.setString(9, emp.getDescription());
			callableSt.setString(10,emp.getRound());
			callableSt.setString(11, emp.getAssignTo());
			callableSt.setString(12, emp.getDepends());
			callableSt.setString(13, null);
			callableSt.setString(14, null);
			callableSt.setString(15, null);
			callableSt.setInt(16, 0);
			callableSt.setInt(17, 0);
			callableSt.setInt(18,0);
			callableSt.setString(19, fname);
			callableSt.setInt(20, id);
			callableSt.setString(21, null);
			callableSt.setString(22, emp.getBugSeverity());
			System.out.println("jjj"+emp.getBugSeverity());
			callableSt.execute();
			callableSt.setString(1, "insertResponse");
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
	public List<Bug> getAllQAProjects(int userId) {
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallqaproject");
 			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Bug pro = new Bug();
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
	public List<Bug> getAllPMProjects(int userId) {
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallpmproject");
			callableSt.setInt(2,userId );
 			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Bug pro = new Bug();
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
	public JSONArray getmodules(int projectId){
		final String procedureCall = "{call pro_BugTracking(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallmodules");
			callableSt.setInt(2, 0);
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
			callableSt.setString(13, null);
			callableSt.setString(14, null);
			callableSt.setString(15, null);
			callableSt.setInt(16, projectId);
			System.out.print("PPPPPPPPPPDDDD");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("modId", rs.getInt("pk_mod_id"));
	        	object.put("modname",  rs.getString("mod_name"));
	        	array.put(object);
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
		return array;
	}
	
	
	@Override
	public JSONArray getsubmodules(int moduleId) {
		final String procedureCall = "{call pro_BugTracking(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallsubmodules");
			callableSt.setInt(2, 0);
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
			callableSt.setString(13, null);
			callableSt.setString(14, null);
			callableSt.setString(15, null);
			callableSt.setInt(16, 0);
			callableSt.setInt(17, moduleId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("submodId", rs.getInt("pk_submod_id"));
	        	object.put("submodname",  rs.getString("submod_name"));
	        	array.put(object);
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
		return array;
      	}
	
	@Override
	public JSONArray getassigned(int submoduleId) {
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getassigned1");
			callableSt.setInt(2, submoduleId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("assignId", rs.getInt("fk_user_id"));
	        	object.put("assignname",  rs.getString("name"));
	        	array.put(object);
			}
			String procedurecalled="{call pro_BugTracking(?,?)}";
			 callableSt = connection.prepareCall(procedurecalled);
			 callableSt.setString(1, "getassigned2");
				callableSt.setInt(2, submoduleId);
				 rs = null;
				rs = callableSt.executeQuery();
				while (rs.next()) {
					object=new JSONObject();
		        	object.put("assignId", rs.getInt("fk_user_id"));
		        	object.put("assignname",  rs.getString("name"));
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
}