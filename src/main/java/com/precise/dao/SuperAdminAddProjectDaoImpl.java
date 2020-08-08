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
public class SuperAdminAddProjectDaoImpl implements SuperAdminAddProjectDao {

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
	public void addtheinformation(Project pro) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminaddproject(?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertaddtheinformation");
			callableSt.setString(2, pro.getProjectName());
			callableSt.setString(3, pro.getProjectDescription());
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
	public void edittheinformation(Project pro) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertedittheinformation");
			callableSt.setString(2, pro.getProjectName());
			callableSt.setString(3, pro.getProjectDescription());
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, pro.getProjectId());
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
	public void deletetheproject(int projectid) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deletetheproject");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, projectid);
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
	public List<Project> getallprojects(Project pro) {
        List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminaddproject(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "allprojects");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				 Project pr = new Project();
			     
				 pr.setProjectId(rs.getInt("projectid"));
                 pr.setProjectName(rs.getString("projectname"));
                 pr.setProjectDescription(rs.getString("projectdescription"));
				
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
	public List<Project> getallModules() {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminaddproject(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectmodules");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
				pro.setProjectName(rs.getString("projectname"));
				pro.setModuleId(rs.getInt("pk_mod_id"));
				pro.setModuleNameIndividually(rs.getString("mod_name"));
				pro.setModuleDescriptionIndividually(rs.getString("mod_description"));
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
	public JSONArray getmodules(int projectid) {
		
		final String procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectmodulesofthisproject");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, projectid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object=null;
			int x=1;
			while (rs.next()) {
				object=new JSONObject();
	        	object.put("modId", rs.getInt("pk_mod_id"));
	        	object.put("modName",  rs.getString("mod_name"));
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
	public List<Project> getallsubModules() {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminaddproject(?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectsubmodules");
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
				pro.setSubModuleId(rs.getInt("pk_submod_id"));
				pro.setProjectName(rs.getString("projectname"));
				pro.setModuleNameIndividually(rs.getString("mod_name"));
				pro.setSubModuleNameIndividually(rs.getString("submod_name"));
				pro.setSubModuleDescriptionIndividually(rs.getString("submod_description"));
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
	public void savesubmoduledetails(Project pro) throws SQLException {
		Connection connection = null;
		try{
			
		 	connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);	
			
			List<String> submodulelist = pro.getSubModuleName();
		 	List<String> submoduledescriptionlist = pro.getSubModuleDescription();
		 	
		 	CallableStatement savemodules =  connection.prepareCall("{call proc_adminaddproject(?,?,?,?,?,?,?,?,?)}");
		    
		 	for(int i=0;i<submodulelist.size();i++){
				savemodules.setString(1,"insertsubmoduledetails");
				savemodules.setString(2, "");
				savemodules.setString(3, "");
				savemodules.setString(4, "");
				savemodules.setString(5, "");
				savemodules.setInt(6, pro.getProjectId());
				savemodules.setInt(7, pro.getModuleId());
				savemodules.setString(8, submodulelist.get(i));
				savemodules.setString(9, submoduledescriptionlist.get(i));
				System.out.println(i+"::"+pro.getProjectId()+"::"+pro.getModuleId()+"::"+submodulelist.get(i)+"::"+submoduledescriptionlist.get(i));
				savemodules.addBatch();
			  }
		 	    
		 	savemodules.executeBatch();
		 	connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
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
	public List<Project> getallpmprojects(int userid) {
        List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "pmassignedprojects");
			callableSt.setString(2,null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, userid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				 Project pr = new Project();
			     
				 pr.setProjectId(rs.getInt("projectid"));
                 pr.setProjectName(rs.getString("projectname"));
                 pr.setProjectDescription(rs.getString("projectdescription"));
				
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
	public List<Project> getallpmModules(int userid) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectpmmodules");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4,null);
			callableSt.setString(5, null);
			callableSt.setInt(6,userid );
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
				pro.setProjectName(rs.getString("projectname"));
				pro.setModuleId(rs.getInt("pk_mod_id"));
				pro.setModuleNameIndividually(rs.getString("mod_name"));
				pro.setModuleDescriptionIndividually(rs.getString("mod_description"));
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
	public List<Project> getallpmsubModules(int userid) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectpmsubmodules");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, userid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Project pro = new Project();
				pro.setSubModuleId(rs.getInt("pk_submod_id"));
				pro.setProjectName(rs.getString("projectname"));
				pro.setModuleNameIndividually(rs.getString("mod_name"));
				pro.setSubModuleNameIndividually(rs.getString("submod_name"));
				pro.setSubModuleDescriptionIndividually(rs.getString("submod_description"));
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
