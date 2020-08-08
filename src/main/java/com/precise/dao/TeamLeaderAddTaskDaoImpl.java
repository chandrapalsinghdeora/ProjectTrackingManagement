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
public class TeamLeaderAddTaskDaoImpl implements TeamLeaderAddTaskDao {

	
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

	@Override
	public JSONArray getsubmodules(int moduleid, int userid) {
		final String procedureCall = "{call proc_teamleaderaddsubmodule(?,?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getsubmodules");
			callableSt.setInt(2, userid);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, moduleid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject obj = null;
			while (rs.next()) {
				obj = new JSONObject();
	        	obj.put("submoduleid", rs.getInt("pk_submod_id"));
	        	obj.put("submodulename", rs.getString("submod_name"));
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
	public void savetasks(Project pro) {
		Connection connection = null;
		try{
			
		 	connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);	
			
			List<String> taskname = pro.getTaskName();
			List<String> taskdesc = pro.getTaskDescription();
		 
			CallableStatement savetasks =  connection.prepareCall("{call proc_teamleaderaddsubmodule(?,?,?,?,?,?,?,?,?)}");
		    
		 	for(int i=0;i<taskname.size();i++){
		 		System.out.println("hyy");
		 		savetasks.setString(1,"savetasks");
		 		savetasks.setInt(2, 0);
		 		savetasks.setInt(3, pro.getProjectId());
		 		savetasks.setString(4, null);
		 		savetasks.setString(5, null);
		 		savetasks.setInt(6, pro.getModuleId());
		 		savetasks.setString(7, taskname.get(i));
		 		savetasks.setString(8, taskdesc.get(i));
		 		savetasks.setInt(9, pro.getSubModuleId());
				
		 		savetasks.addBatch();
			  }
		 	    
		 	savetasks.executeBatch();
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
	public List<Project> getalltasks(int submoduleid) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_teamleaderaddsubmodule(?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selecttasks");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, 0);
			callableSt.setString(7, null);
			callableSt.setString(8, null);
			callableSt.setInt(9, submoduleid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
				pro.setTaskNameIndividually(rs.getString("task_name"));
				pro.setTaskDescriptionIndividually(rs.getString("task_description"));
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
	public JSONArray gettasks(int submoduleid) {
		final String procedureCall = "{call proc_teamleaderaddsubmodule(?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getalltasks");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, 0);
			callableSt.setString(7, null);
			callableSt.setString(8, null);
			callableSt.setInt(9, submoduleid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject obj = null;
			while (rs.next()) {
				obj = new JSONObject();
	        	obj.put("taskid", rs.getInt("pk_task_id"));
	        	obj.put("taskname", rs.getString("task_name"));
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
	public JSONArray getothertasks(int taskid, int submoduleid) {
		final String procedureCall = "{call proc_teamleaderaddsubmodule(?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallothertasks");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, 0);
			callableSt.setString(7, null);
			callableSt.setString(8, null);
			callableSt.setInt(9, submoduleid);
			callableSt.setInt(10, taskid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject obj = null;
			while (rs.next()) {
				obj = new JSONObject();
	        	obj.put("othertaskid", rs.getInt("pk_task_id"));
	        	obj.put("othertaskname", rs.getString("task_name"));
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
	public void dependency(Project pro) {
		Connection connection = null;
		try{
		 	connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);	
			
		 
			CallableStatement savedependency =  connection.prepareCall("{call proc_teamleaderaddsubmodule(?,?,?,?,?,?,?,?,?,?,?)}");
		    
			savedependency.setString(1,"savedependency");
			savedependency.setInt(2, 0);
			savedependency.setInt(3, 0);
	 		savedependency.setString(4, null);
	 		savedependency.setString(5, null);
	 		savedependency.setInt(6, 0);
	 		savedependency.setString(7, null);
	 		savedependency.setString(8, null);
	 		savedependency.setInt(9, 0);
	 		savedependency.setInt(10, pro.getTaskId());
	 		savedependency.setInt(11, pro.getOtherTaskId());
			
	 		savedependency.addBatch();
			 
		 	    
	 		savedependency.executeBatch();
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
	public List<Project> getallthetasks(int userid) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_teamleaderaddsubmodule(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallthetasks");
			callableSt.setInt(2, userid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pr = new Project();
		        pr.setProjectId(rs.getInt("projectid"));
                pr.setProjectName(rs.getString("projectname"));
                pr.setTaskId(rs.getInt("pk_task_id"));
                pr.setModuleNameIndividually(rs.getString("mod_name"));
                pr.setSubModuleNameIndividually(rs.getString("submod_name"));
                pr.setTaskNameIndividually(rs.getString("task_name"));
                pr.setTaskDescriptionIndividually(rs.getString("task_description"));
                
				
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
	public void updateindividualtask(Project pro) {
		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_teamleaderaddsubmodule(?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateindividualtask");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setString(4,null);
			callableSt.setString(5,null);
			callableSt.setInt(6, 0);
			callableSt.setString(7, pro.getTaskNameIndividually());
			callableSt.setString(8, pro.getTaskDescriptionIndividually());
			callableSt.setInt(9, 0);
			callableSt.setInt(10, pro.getTaskId());
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
	public void deletetask(int id) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_teamleaderaddsubmodule(?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deleteindividualtask");
			callableSt.setInt(2, id);
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
