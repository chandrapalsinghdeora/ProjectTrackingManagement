
package com.precise.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Bug;

@Repository 
public class FetchBugDaoImpl implements FetchBugDao {
	
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
	public List<Bug> gettheinformation(int userId)
	{
			List<Bug> listRole = new ArrayList<Bug>();
			final String procedureCall = "{call pro_BugTracking(?,?)}";
			Connection connection = null;
			try {
				connection = jdbctemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectbugentryqa");
				callableSt.setInt(2,userId );
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					Bug b = new Bug();
	                b.setBugNo(rs.getInt("bugNo"));
	                b.setProjectName(rs.getString("projectname"));
	                b.setProjectId(rs.getInt("projectid"));
	                b.setModule(rs.getString("mod_name"));
	                b.setBugName(rs.getString("bugName"));
	                b.setBugSeverity(rs.getString("bugSeverity"));
	                b.setBugType(rs.getString("bugType"));
	                b.setBugStatus(rs.getString("bugStatus"));
	                b.setName(rs.getString("name"));
	                b.setDate(rs.getDate("date"));
	                listRole.add(b);
					
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
	public List<Bug> gettheinformationpm(int id)
	{
			List<Bug> listRole = new ArrayList<Bug>();
			final String procedureCall = "{call pro_BugTracking(?,?)}";
			Connection connection = null;
			try {
				connection = jdbctemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectbugentrypm");
				callableSt.setInt(2, id);
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					Bug b = new Bug();
	                b.setBugNo(rs.getInt("bugNo"));
	                b.setProjectName(rs.getString("projectname"));
	                b.setModule(rs.getString("mod_name"));
	                b.setBugName(rs.getString("bugName"));
	                b.setBugType(rs.getString("bugType"));
	                b.setBugSeverity(rs.getString("bugSeverity"));
	                b.setBugStatus(rs.getString("bugStatus"));
	                b.setAssignTo(rs.getString("name"));
	                b.setQAName(rs.getString("username"));
	               b.setDate(rs.getDate("date"));
	                listRole.add(b);
					
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
	public List<Bug> getthereport(int id)
	{
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectbr");
			callableSt.setInt(2, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			
			int x=1;
			while (rs.next()) {
				Bug emp = new Bug();
				emp.setBugNo(rs.getInt("bugNo"));
                emp.setProjectName(rs.getString("projectname"));
                emp.setBugName(rs.getString("bugName"));
                emp.setBugType(rs.getString("bugType"));
                emp.setBugStatus(rs.getString("bugStatus"));
                emp.setStatus(rs.getString("status"));
                emp.setModule(rs.getString("mod_name"));
                emp.setSubModule(rs.getString("submod_name"));
                emp.setName(rs.getString("name"));
                emp.setDescription(rs.getString("description"));
                emp.setRound(rs.getString("round"));
                emp.setDate(rs.getDate("date"));
                emp.setDepends(rs.getString("depends"));
               emp.setSummary(rs.getString("summary"));
                
                
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
public List<Bug> getAllAssign()
{
	List<Bug> listRole = new ArrayList<Bug>();
	final String procedureCall = "{call pro_BugTracking(?)}";
	Connection connection = null;
	try {
		connection = jdbctemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "getallassign");
		ResultSet rs = null;
		rs = callableSt.executeQuery();
		
		int x=1;
		while (rs.next()) {
			Bug emp=new Bug();
           emp.setAssignTo(rs.getString("name"));
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
	public void generateExcel() {
		
		
			final String procedureCall = "{call pro_BugTracking(?)}";
			Connection connection = null;
	        
			try {
				connection = jdbctemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectcreateexcel");
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				try {
					
				    HSSFWorkbook workbook = new HSSFWorkbook();
				    HSSFSheet sheet = workbook.createSheet("Bug Report For PM");
				    HSSFRow rowhead = sheet.createRow((short) 0);
				    rowhead.createCell((short) 0).setCellValue("Bug NO");
				    rowhead.createCell((short) 1).setCellValue("Projet Name");
				    rowhead.createCell((short) 2).setCellValue("Bug Name");
				    rowhead.createCell((short) 3).setCellValue("Bug Type");
				    rowhead.createCell((short) 4).setCellValue("QA Bug Status");
				    rowhead.createCell((short) 5).setCellValue("Status by Dev");
				    rowhead.createCell((short) 6).setCellValue("Module");
				    rowhead.createCell((short) 7).setCellValue("Sub Module");
				    rowhead.createCell((short) 8).setCellValue("Assign To");
				    rowhead.createCell((short) 9).setCellValue("Description By QA");
				    rowhead.createCell((short) 10).setCellValue("Round");
				    rowhead.createCell((short) 11).setCellValue("Date");
				    rowhead.createCell((short) 12).setCellValue("Depends");
				    rowhead.createCell((short) 13).setCellValue("Summary By Dev");
				    int i = 1;
				    
				    while (rs.next()){
				        HSSFRow row = sheet.createRow((short) i);
				        row.createCell((short) 0).setCellValue(rs.getString("bugNo"));
				        row.createCell((short) 1).setCellValue(rs.getString("projectName"));
				        row.createCell((short) 2).setCellValue(rs.getString("bugName"));
				        row.createCell((short) 3).setCellValue(rs.getString("bugType"));
				        row.createCell((short) 4).setCellValue(rs.getString("bugStatus"));
				        row.createCell((short) 5).setCellValue(rs.getString("status"));
				        row.createCell((short) 6).setCellValue(rs.getString("mod_name"));
				        row.createCell((short) 7).setCellValue(rs.getString("submod_name"));
				        row.createCell((short) 8).setCellValue(rs.getString("name"));
				        row.createCell((short) 9).setCellValue(rs.getString("description"));
				        row.createCell((short) 10).setCellValue(rs.getString("round"));
				        row.createCell((short) 11).setCellValue(rs.getString("date"));
				        row.createCell((short) 12).setCellValue(rs.getString("depends"));
				        row.createCell((short) 13).setCellValue(rs.getString("summary"));
				        i++;
				    }
				    
				    String yemi = "D:/BugSheet.xls";
				    FileOutputStream fileOut = new FileOutputStream(yemi);
				    workbook.write(fileOut);
				    fileOut.close();
				    
				    
				    } catch (SQLException e1) {
				        e1.printStackTrace();
				    } catch (FileNotFoundException e1) {
				        e1.printStackTrace();
				    } catch (IOException e1) {
				        e1.printStackTrace();
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
			
		}

	@Override
	public List<Bug> getthereportspecific(String date1, String date2, int id){
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?,?,?)}";
		
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectbugentry2datecondition");
			callableSt.setInt(2, id);
			callableSt.setString(3, date1);
			callableSt.setString(4, date2);
			
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			
			int x=1;
			while (rs.next()) {
				Bug emp = new Bug();
				emp.setBugNo(rs.getInt("bugNo"));
               emp.setProjectName(rs.getString("projectname"));
               emp.setBugName(rs.getString("bugName"));
               emp.setBugType(rs.getString("bugType"));
               emp.setBugStatus(rs.getString("bugStatus"));
               emp.setStatus(rs.getString("status"));
               emp.setModule(rs.getString("mod_name"));
               emp.setSubModule(rs.getString("submod_name"));
               emp.setName(rs.getString("name"));
               emp.setDescription(rs.getString("description"));
               emp.setRound(rs.getString("round"));
               emp.setDate(rs.getDate("date"));
               emp.setDepends(rs.getString("depends"));
              emp.setSummary(rs.getString("summary"));
                
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
	public List<Bug> getthereportproject(String project){
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?,?)}";
		
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectbugentry2projectcondition");
			callableSt.setInt(2, 0);
			callableSt.setString(3, project);
			
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			
			int x=1;
			while (rs.next()) {
				Bug emp = new Bug();
				emp.setBugNo(rs.getInt("bugNo"));
               emp.setProjectName(rs.getString("projectname"));
               emp.setBugName(rs.getString("bugName"));
               emp.setBugType(rs.getString("bugType"));
               emp.setBugStatus(rs.getString("bugStatus"));
               emp.setStatus(rs.getString("status"));
               emp.setModule(rs.getString("mod_name"));
               emp.setSubModule(rs.getString("submod_name"));
               emp.setName(rs.getString("name"));
               emp.setDescription(rs.getString("description"));
               emp.setRound(rs.getString("round"));
               emp.setDate(rs.getDate("date"));
               emp.setDepends(rs.getString("depends"));
              emp.setSummary(rs.getString("summary"));
                
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
	public List<Bug> getthereportassign(String assign, int id){
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?,?)}";
		
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectbugentry2assigncondition");
			callableSt.setInt(2, id);
			callableSt.setString(3, assign);
			
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			
			int x=1;
			while (rs.next()) {
				Bug emp = new Bug();
			   emp.setBugNo(rs.getInt("bugNo"));
		       emp.setProjectName(rs.getString("projectname"));
		       emp.setBugName(rs.getString("bugName"));
		       emp.setBugType(rs.getString("bugType"));
		       emp.setBugStatus(rs.getString("bugStatus"));
		       emp.setStatus(rs.getString("status"));
		       emp.setModule(rs.getString("mod_name"));
		       emp.setSubModule(rs.getString("submod_name"));
		       emp.setName(rs.getString("name"));
		       emp.setDescription(rs.getString("description"));
		       emp.setRound(rs.getString("round"));
		       emp.setDate(rs.getDate("date"));
		       emp.setDepends(rs.getString("depends"));
		       emp.setSummary(rs.getString("summary"));
		        
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
	public List<Bug> ifile(int bugno)
	{
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectfiledev");
			callableSt.setInt(2, bugno);
			
			
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			
			int x=1;
			while (rs.next()) {
				Bug emp = new Bug();
				
               emp.setImage(rs.getString("image"));
                
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
	public List<Bug> ifileres(int bugno)
	{
		List<Bug> listRole = new ArrayList<Bug>();
		final String procedureCall = "{call pro_BugTracking(?,?)}";
		
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectfileqa");
			callableSt.setInt(2, bugno);
			
			
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			
			int x=1;
			while (rs.next()) {
				Bug emp = new Bug();
				
               emp.setImage(rs.getString("image1"));
                
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
}