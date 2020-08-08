package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Employee;
import com.precise.model.Project;

@Repository
public class AdminAssignProjectDaoImpl implements AdminAssignProjectDao{

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
	public List<Employee> getAllDesignation() {
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_adminassignproject(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectdesignations");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			System.out.println("vale of rs is::"+rs.getFetchSize());
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
	public List<Project> getAllProject(int userid) {
		
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminassignproject(?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallproject");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, userid);
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
	public JSONArray getteamleaders(String empDesignation) {
		final String procedureCall = "{call proc_adminassignproject(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectteamleaders");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setDate(5, null);
			callableSt.setDate(6, null);
			callableSt.setInt(7, 0);
			callableSt.setString(8, empDesignation);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("pkId", rs.getInt("pk_userid"));
	        	object.put("name",  rs.getString("name"));
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
	public void assignthemodule(Project pro) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminassignproject(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "assignthemodule");
			callableSt.setInt(2, pro.getProjectId());
			callableSt.setInt(3, pro.getUserId());
			callableSt.setInt(4, pro.getModuleId());
			callableSt.setDate(5, new java.sql.Date(pro.getStartDate().getTime()));
			callableSt.setDate(6, new java.sql.Date(pro.getEndDate().getTime()));
			callableSt.execute();
			
			
			
			// to update the isassigned variable 
			
			procedureCall = "{call proc_adminassignproject(?,?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateisassigned");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, pro.getModuleId());
			
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

	@Override
	public JSONArray getmodules(int id) {
		final String procedureCall = "{call proc_adminassignproject(?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getmodules");
			callableSt.setInt(2, id);
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
	

}



