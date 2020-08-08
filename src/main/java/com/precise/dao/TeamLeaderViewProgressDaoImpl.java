package com.precise.dao;

import java.awt.Desktop;
import java.io.File;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Employee;
import com.precise.model.Project;

@Repository
public class TeamLeaderViewProgressDaoImpl implements TeamLeaderViewProgressDao{

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
	public List<Project> getAssignedProject(int userId) {
		
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_teamleaderviewprogress(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectallprojects");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, userId);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			while (rs.next()) {
				Project pro = new Project();
				pro.setProjectName(rs.getString("projectname"));
                pro.setProjectId(rs.getInt("pk_projectid"));
				
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
	public void getexcel(int id) {
		final String procedureCall = "{call proc_teamleaderviewprogress(?,?)}";
		Connection connection = null;
        
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectcreatepwexcel");
			callableSt.setInt(2, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			System.out.println("rs here::"+rs);
			try {
				
			    HSSFWorkbook workbook = new HSSFWorkbook();
			    HSSFSheet sheet = workbook.createSheet("project_excel");
			    HSSFRow rowhead = sheet.createRow((short) 0);
			    rowhead.createCell((short) 0).setCellValue("Employee Name");
			    rowhead.createCell((short) 1).setCellValue("Project Name");
			    rowhead.createCell((short) 2).setCellValue("Module Name");
			    rowhead.createCell((short) 3).setCellValue("SubModule Name");
			    rowhead.createCell((short) 4).setCellValue("Task Name");
			    rowhead.createCell((short) 5).setCellValue("Hours");
			    rowhead.createCell((short) 6).setCellValue("Description");
			    int i = 1;
			    
			    while (rs.next()){
			        HSSFRow row = sheet.createRow((short) i);
			        row.createCell((short) 0).setCellValue(rs.getString("name"));
			        row.createCell((short) 1).setCellValue(rs.getString("project_name"));
			        row.createCell((short) 2).setCellValue(rs.getString("mod_name"));
			        row.createCell((short) 3).setCellValue(rs.getString("submod_name"));
			        row.createCell((short) 4).setCellValue(rs.getString("task_name"));
			        row.createCell((short) 5).setCellValue(rs.getString("time"));
			        row.createCell((short) 6).setCellValue(rs.getString("description"));
			        i++;
			    }
			    
			    String yemi = "D:/test.xls";
			    FileOutputStream fileOut = new FileOutputStream(yemi);
			    workbook.write(fileOut);
			    fileOut.close();
			    
			    Desktop.getDesktop().open(new File("D:\\test.xls"));
			    
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
	public JSONArray getmodule(int id,int userid) {
		

		final String procedureCall = "{call proc_teamleaderviewprogress(?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		
		
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectmodules");
			callableSt.setInt(2, id);
			callableSt.setInt(3, 0);
			callableSt.setInt(4,userid );
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject obj = null;
			int x=1;
			while (rs.next()) {
				obj = new JSONObject();
				obj.put("modId", rs.getInt("pk_mod_id"));
				obj.put("name", rs.getString("mod_name"));
				array.put(obj);
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
	public void getmoduleexcel(int id) {
		final String procedureCall = "{call proc_teamleaderviewprogress(?,?,?)}";
		Connection connection = null;
        
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectcreatemwexcel");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			try {
				
			    HSSFWorkbook workbook = new HSSFWorkbook();
			    HSSFSheet sheet = workbook.createSheet("module_excel");
			    HSSFRow rowhead = sheet.createRow((short) 0);
			    rowhead.createCell((short) 0).setCellValue("Employee Name");
			    rowhead.createCell((short) 1).setCellValue("Project Name");
			    rowhead.createCell((short) 2).setCellValue("Module Name");
			    rowhead.createCell((short) 3).setCellValue("SubModule Name");
			    rowhead.createCell((short) 4).setCellValue("Task Name");
			    rowhead.createCell((short) 5).setCellValue("Hours");
			    rowhead.createCell((short) 6).setCellValue("Description");
			    int i = 1;
			    
			    while (rs.next()){
			        HSSFRow row = sheet.createRow((short) i);
			        row.createCell((short) 0).setCellValue(rs.getString("name"));
			        row.createCell((short) 1).setCellValue(rs.getString("project_name"));
			        row.createCell((short) 2).setCellValue(rs.getString("mod_name"));
			        row.createCell((short) 3).setCellValue(rs.getString("submod_name"));
			        row.createCell((short) 3).setCellValue(rs.getString("task_name"));
			        row.createCell((short) 4).setCellValue(rs.getString("time"));
			        row.createCell((short) 5).setCellValue(rs.getString("description"));
			        i++;
			    }
			    
			    String yemi = "D:/test.xls";
			    FileOutputStream fileOut = new FileOutputStream(yemi);
			    workbook.write(fileOut);
			    fileOut.close();
			    
			    Desktop.getDesktop().open(new File("D:\\test.xls"));
			    
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
	public List<Employee> getAssignedUsers(int userId) {
		List<Employee> listRole = new ArrayList<Employee>();
		final String procedureCall = "{call proc_teamleaderviewprogress(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt1 = connection.prepareCall(procedureCall);
			callableSt1.setString(1, "selectusers");
			callableSt1.setInt(2, 0);
			callableSt1.setInt(3, 0);
			callableSt1.setInt(4, userId);
			ResultSet rs1 = null;
			rs1 = callableSt1.executeQuery();
			CallableStatement callableSt2 = connection.prepareCall(procedureCall);
			callableSt2.setString(1, "selectallusers");
			callableSt2.setInt(2, 0);
			callableSt2.setInt(3, 0);
			callableSt2.setInt(4, userId);
			ResultSet rs2 = null;
			rs2 = callableSt2.executeQuery();
			while (rs1.next()) {
				Employee emp = new Employee();
				emp.setUserName(rs1.getString("name"));
                emp.setUserId(rs1.getInt("pk_userid"));
                listRole.add(emp);
			}
			while (rs2.next()) {
				int flag=0;
				rs1 = callableSt1.executeQuery();
				while(rs1.next())
				{
					if(rs1.getInt("pk_userid")==rs2.getInt("pk_userid"))
					{
					  flag=1;	
					}
				}
				if(flag==0)
				{
				Employee emp = new Employee();
				emp.setUserName(rs2.getString("name"));
                emp.setUserId(rs2.getInt("pk_userid"));
                listRole.add(emp);
				}
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
	public void getuserexcel(int id) {
		final String procedureCall = "{call proc_teamleaderviewprogress(?,?,?,?)}";
		Connection connection = null;
        
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectcreateuwexcel");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			System.out.println("rs here::"+rs);
			try {
				
			    HSSFWorkbook workbook = new HSSFWorkbook();
			    HSSFSheet sheet = workbook.createSheet("user_excel");
			    HSSFRow rowhead = sheet.createRow((short) 0);
			    rowhead.createCell((short) 0).setCellValue("Employee Name");
			    rowhead.createCell((short) 1).setCellValue("Project Name");
			    rowhead.createCell((short) 2).setCellValue("Module Name");
			    rowhead.createCell((short) 3).setCellValue("SubModule Name");
			    rowhead.createCell((short) 4).setCellValue("Task Name");
			    rowhead.createCell((short) 5).setCellValue("Hours");
			    rowhead.createCell((short) 6).setCellValue("Description");
			    int i = 1;
			    
			    while (rs.next()){
			        HSSFRow row = sheet.createRow((short) i);
			        row.createCell((short) 0).setCellValue(rs.getString("name"));
			        row.createCell((short) 1).setCellValue(rs.getString("project_name"));
			        row.createCell((short) 2).setCellValue(rs.getString("mod_name"));
			        row.createCell((short) 3).setCellValue(rs.getString("submod_name"));
			        row.createCell((short) 4).setCellValue(rs.getString("task_name"));
			        row.createCell((short) 5).setCellValue(rs.getString("time"));
			        row.createCell((short) 6).setCellValue(rs.getString("description"));
			        i++;
			    }
			    
			    String yemi = "D:/test.xls";
			    FileOutputStream fileOut = new FileOutputStream(yemi);
			    workbook.write(fileOut);
			    fileOut.close();
			    
			    Desktop.getDesktop().open(new File("D:\\test.xls"));
			    
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
	public JSONArray getthesubmodules(int id) {
		
		final String procedureCall = "{call proc_teamleaderviewprogress(?,?,?,?,?)}";
		Connection connection = null;
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectsubmodules");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, id);
			
			rs = callableSt.executeQuery();
			JSONObject obj = null;
			int x=1;
			while(rs.next()){
				obj = new JSONObject();
				obj.put("tlsubmodid", rs.getInt("pk_submod_id"));
				obj.put("tlsubmodname", rs.getString("submod_name"));
				array.put(obj);
				x=x+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return array;
		
	}

	@Override
	public void getsubmoduleexcel(int id) {
		final String procedureCall = "{call proc_teamleaderviewprogress(?,?,?,?,?,?)}";
		Connection connection = null;
        
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectcreatesmwexcel");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			try {
				
			    HSSFWorkbook workbook = new HSSFWorkbook();
			    HSSFSheet sheet = workbook.createSheet("submodule_excel");
			    HSSFRow rowhead = sheet.createRow((short) 0);
			    rowhead.createCell((short) 0).setCellValue("Employee Name");
			    rowhead.createCell((short) 1).setCellValue("Project Name");
			    rowhead.createCell((short) 2).setCellValue("Module Name");
			    rowhead.createCell((short) 3).setCellValue("SubModule Name");
			    rowhead.createCell((short) 4).setCellValue("Task Name");
			    rowhead.createCell((short) 5).setCellValue("Hours");
			    rowhead.createCell((short) 6).setCellValue("Description");
			    int i = 1;
			    
			    while (rs.next()){
			        HSSFRow row = sheet.createRow((short) i);
			        row.createCell((short) 0).setCellValue(rs.getString("name"));
			        row.createCell((short) 1).setCellValue(rs.getString("project_name"));
			        row.createCell((short) 2).setCellValue(rs.getString("mod_name"));
			        row.createCell((short) 3).setCellValue(rs.getString("submod_name"));
			        row.createCell((short) 4).setCellValue(rs.getString("task_name"));
			        row.createCell((short) 5).setCellValue(rs.getString("time"));
			        row.createCell((short) 6).setCellValue(rs.getString("description"));
			        i++;
			    }
			    
			    String yemi = "D:/test.xls";
			    FileOutputStream fileOut = new FileOutputStream(yemi);
			    workbook.write(fileOut);
			    fileOut.close();
			    
			    Desktop.getDesktop().open(new File("D:\\test.xls"));
			    
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
	public JSONArray getthemodule(int id,int userid) {
		
		final String procedureCall = "{call proc_teamleaderviewprogress(?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectthemodules");
			callableSt.setInt(2, id);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, userid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject obj = null;
			int x=1;
			while (rs.next()) {
				obj = new JSONObject();
				obj.put("modId", rs.getInt("pk_mod_id"));
				obj.put("name", rs.getString("mod_name"));
				array.put(obj);
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


