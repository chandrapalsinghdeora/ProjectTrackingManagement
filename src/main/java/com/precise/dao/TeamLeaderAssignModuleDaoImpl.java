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
public class TeamLeaderAssignModuleDaoImpl implements TeamLeaderAssignModuleDao{

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
	public List<Project> getAssignedProjects(int userId) {
		
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAssignedProjects");
			callableSt.setInt(2,userId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
                pro.setProjectName(rs.getString("projectname"));
                pro.setEmpDesignation(rs.getString("designation"));
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
	public JSONArray getmodules(String projectName,int userid) {
		
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getmodules");
			callableSt.setInt(2,userid );
			callableSt.setString(3, projectName);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("modId", rs.getInt("pk_mod_id"));
	        	object.put("name",  rs.getString("mod_name"));
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
	public List<Employee> getallemployees(String empDesignation,int userId) {
		
		
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallemployees");
			callableSt.setInt(2,userId);
			callableSt.setString(3,null);
			callableSt.setString(4,empDesignation);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				 Employee emp = new Employee();
                 emp.setName(rs.getString("name"));
                 emp.setUserId(rs.getInt("pk_userid"));
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
	public void assignittoemployees(Project pro,int userId) {
		
		
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "assignittoemployees");
			callableSt.setInt(2, pro.getUserId());
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, pro.getModuleId());
			callableSt.setDate(6, new java.sql.Date(pro.getStartDate().getTime()) );
			callableSt.setDate(7, new java.sql.Date(pro.getEndDate().getTime()));
			callableSt.setInt(8,userId );
			callableSt.setInt(9,pro.getSubModuleId());
			callableSt.execute();
			procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "update");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, pro.getModuleId());
			callableSt.execute();
			procedureCall = "{call proc_teamleaderassignmodule(?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatesubmoduleisassigned");
			callableSt.setInt(2, pro.getSubModuleId());
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
	public List<Project> getAssignedModules(int id) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAssignedModules");
			callableSt.setInt(2, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
				pro.setProjectName(rs.getString("projectname"));
		        pro.setModuleNameIndividually(rs.getString("mod_name"));
		        pro.setModuleDescriptionIndividually(rs.getString("mod_description"));
		        pro.setStartDate(rs.getDate("startdate"));
		        pro.setEndDate(rs.getDate("enddate"));
		        pro.setEmpDesignation(rs.getString("designation"));
				
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
	public JSONArray getassignmodules(String projectName, int userid) {
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getmodules");
			callableSt.setInt(2,userid );
			callableSt.setString(3, projectName);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("modId", rs.getInt("pk_mod_id"));
	        	object.put("name",  rs.getString("mod_name"));
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
	public JSONArray getsubmodules(int id, int userid) {
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getsubmodules");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("pkId", rs.getInt("pk_submod_id"));
	        	object.put("name",  rs.getString("submod_name"));
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
	public JSONArray gettasks(int id, int userid) {
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "gettasks");
			callableSt.setInt(2, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("pkId", rs.getInt("pk_task_id"));
	        	object.put("name",  rs.getString("task_name"));
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
	public void assigntasktoemployees(Project pro, int userId) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "assigntasktoemployees");
			callableSt.setInt(2, pro.getUserId());
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, 0);
			callableSt.setDate(6, new java.sql.Date(pro.getStartDate().getTime()) );
			callableSt.setDate(7, new java.sql.Date(pro.getEndDate().getTime()));
			callableSt.setInt(8,userId );
			callableSt.setInt(9,pro.getSubModuleId());
			callableSt.setInt(10,pro.getTaskId());
			callableSt.execute();
			procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "update");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, pro.getModuleId());
			callableSt.execute();
			procedureCall = "{call proc_teamleaderassignmodule(?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatetaskisassigned");
			callableSt.setInt(2, pro.getTaskId());
			callableSt.execute();
			procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getsubmodulesisassigned");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, pro.getModuleId());
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
	public JSONArray getemployees(int id, int userid) {
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getemployees");
			callableSt.setInt(2, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("userId", rs.getInt("pk_userid"));
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
	public JSONArray getalltheemployees(String designation, int userid) {
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallemployees");
			callableSt.setInt(2, userid);
			callableSt.setString(3, null);
			callableSt.setString(4,designation );
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("userId", rs.getInt("pk_userid"));
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
	public JSONArray getthesubmodules(int id, int userid) {
		final String procedureCall = "{call proc_teamleaderassignmodule(?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getthesubmodules");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("pkId", rs.getInt("pk_submod_id"));
	        	object.put("name",  rs.getString("submod_name"));
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