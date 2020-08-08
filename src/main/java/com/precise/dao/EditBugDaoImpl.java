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

import com.precise.model.Bug;

@Repository
public class EditBugDaoImpl implements EditBugDao {

	@Autowired
	private NamedParameterJdbcTemplate namedparameterjdbctemplate;
	
	@Autowired
	private JdbcTemplate jdbctemplate;
    String fname1;
	
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
	public List<Bug> getbugdetails(int bugno) {
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "editbugno");
			callableSt.setInt(2,bugno);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Bug emp = new Bug();
                 emp.setProjectId(rs.getInt("ProjectId"));
                 emp.setBugName(rs.getString("BugName"));
                 emp.setBugType(rs.getString("BugType"));
                 emp.setBugSeverity(rs.getString("BugSeverity"));
                 emp.setModuleId(rs.getInt("ModuleId"));
                 emp.setSubModuleId(rs.getInt("SubModuleId"));
                 emp.setBugStatus(rs.getString("bugStatus"));
                 emp.setDescription(rs.getString("description"));
                 emp.setImage(rs.getString("image"));
                 fname1 =rs.getString("image");
                 emp.setRound(rs.getString("round"));
                 emp.setAssign(rs.getInt("Assign"));
                 emp.setDepends(rs.getString("Depends"));
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
	public void updatetheinformation(Bug emp,String fname,int bugno,int id) {
		final String procedureCall = "{call pro_BugTracking(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateqabug");
			callableSt.setInt(2,bugno);
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
			if(fname==null)
			{
				callableSt.setString(19,fname1);
			}	
			else
			{	
			 callableSt.setString(19, fname);
			}
			 callableSt.setInt(20, id);
			 callableSt.setString(21, null);
			 callableSt.setString(22, emp.getBugSeverity());
			callableSt.execute();
			connection.commit();
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
		
	}

	@Override
	public List<Bug> getthemodules(int pid) {
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallthemodules");
			callableSt.setInt(2,pid);
 			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Bug pro = new Bug();
                pro.setModuleId(rs.getInt("pk_mod_id"));
                pro.setModule(rs.getString("mod_name"));
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
	public List<Bug> getthesubmodules(int mid) {
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallthesubmodules");
			callableSt.setInt(2,mid);
 			ResultSet rs = null;
			rs = callableSt.executeQuery();
			System.out.println("vale of rs is::"+rs.getFetchSize());
			int x=1;
			while (rs.next()) {
				Bug pro = new Bug();
                pro.setSubModuleId(rs.getInt("pk_submod_id"));
                pro.setSubModule(rs.getString("submod_name"));
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
	public List<Bug> gettheassigned(int smid) {
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getallassigned1");
			callableSt.setInt(2,smid);
 			ResultSet rs = null;
			rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				Bug pro1 = new Bug();
                pro1.setUserId(rs.getInt("fk_user_id"));
                pro1.setName(rs.getString("name"));
               
				 listRole.add(pro1);
				x=x+1;
			}
			CallableStatement callableSt1 = connection.prepareCall(procedureCall);
			callableSt1.setString(1, "getallassigned2");
			callableSt1.setInt(2,smid);
 			ResultSet rs1 = null;
			rs1 = callableSt1.executeQuery();
			while (rs1.next()) {
				Bug pro1 = new Bug();
                pro1.setUserId(rs1.getInt("fk_user_id"));
                pro1.setName(rs1.getString("name"));
               
				 listRole.add(pro1);
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
	public byte[] getImageByteData(String bugNo) {
		Connection connection;
		byte[] bytes = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement cs = connection.prepareCall("{call pro_BugTracking(?,?)}");
			cs.setString(1, "selectFiledata");
			cs.setInt(2, Integer.parseInt(bugNo));
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				bytes = rs.getBytes("image");
				System.out.println(bytes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bytes;
	}
}
