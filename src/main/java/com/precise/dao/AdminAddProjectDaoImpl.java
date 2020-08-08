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
public class AdminAddProjectDaoImpl implements AdminAddProjectDao{

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
	public List<Project> getallprojects(Project pro, int userId) {
        List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "pmassignedprojects");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, 0);
			callableSt.setString(8, null);
			callableSt.setString(9, null);
			callableSt.setInt(10, userId);
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
	public void savemoduledetails(Project pro) throws SQLException {
		System.out.println("AdminAddProjectDaoImpl.savemoduledetails()");
		
		Connection connection = null;
		try{
			
		 	connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);	
			
			List<String> modulelist = pro.getModuleName();
		 	List<String> moduledescriptionlist = pro.getModuleDescription();
		 	
		 	CallableStatement savemodules =  connection.prepareCall("{call proc_adminaddproject(?,?,?,?,?,?)}");
		    
		 	for(int i=0;i<modulelist.size();i++){
		 		System.out.println("hyy");
				savemodules.setString(1,"insertmoduledetails");
				savemodules.setString(2, "");
				savemodules.setString(3, "");
				savemodules.setString(4, modulelist.get(i));
				savemodules.setString(5, moduledescriptionlist.get(i));
				savemodules.setInt(6, pro.getProjectId());
				
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
	public void updateindividualmodule(Project pro) {
		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateindividualmodule");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, pro.getModuleNameIndividually());
			callableSt.setString(5, pro.getModuleDescriptionIndividually());
			callableSt.setInt(6, 0);
			callableSt.setInt(7, pro.getModuleId());
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
	public void deletemodule(int moduleid) {
		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deletethemodule");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, moduleid);
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
	public void updateindividualsubmodule(Project pro) {
		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateindividualsubmodule");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, 0);
			callableSt.setString(8, pro.getSubModuleNameIndividually());
			callableSt.setString(9, pro.getSubModuleDescriptionIndividually());
			callableSt.setInt(10, 0);
			callableSt.setInt(11, pro.getSubModuleId());
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
	public void deletesubmodule(int submoduleid) {
		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_adminaddproject(?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deletethesubmodule");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, 0);
			callableSt.setString(8, null);
			callableSt.setString(9, null);
			callableSt.setInt(10, 0);
			callableSt.setInt(11, submoduleid);
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



