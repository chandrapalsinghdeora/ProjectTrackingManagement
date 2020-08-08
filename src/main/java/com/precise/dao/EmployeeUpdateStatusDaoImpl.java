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

import com.precise.model.Project;

@Repository
public class EmployeeUpdateStatusDaoImpl implements EmployeeUpdateStatusDao{

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
	public List<Project> getAllProjects(int userId) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_employeeupdatestatus(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt1 = connection.prepareCall(procedureCall);
			callableSt1.setString(1, "getAllProjects");
			callableSt1.setInt(2,userId);
			ResultSet rs1 = null;
			rs1 = callableSt1.executeQuery();
			CallableStatement callableSt2 = connection.prepareCall(procedureCall);
			callableSt2.setString(1, "getAllTheProjects");
			callableSt2.setInt(2,userId);
			ResultSet rs2 = null;
			rs2 = callableSt2.executeQuery();
			while (rs1.next()) {
				Project pro = new Project();
                pro.setProjectName(rs1.getString("projectname"));
                pro.setProjectId(rs1.getInt("projectid"));
                listRole.add(pro);
			}
			while (rs2.next()) {
				int flag=0;
				rs1 = callableSt1.executeQuery();
				while(rs1.next())
				{
					if(rs1.getInt("projectid")==rs2.getInt("projectid"))
					{
					  flag=1;	
					}
				}
				if(flag==0)
				{
				Project pro = new Project();
                pro.setProjectName(rs2.getString("projectname"));
                pro.setProjectId(rs2.getInt("projectid"));
                listRole.add(pro);
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
	public JSONArray getmodules(String projectName,int userId) {
		final String procedureCall = "{call proc_employeeupdatestatus(?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt1 = connection.prepareCall(procedureCall);
			callableSt1.setString(1, "getmodules");
			callableSt1.setInt(2, userId);
			callableSt1.setInt(3,0);
			callableSt1.setString(4, projectName);
			
			ResultSet rs1 = null;
			rs1 = callableSt1.executeQuery();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAllmodules");
			callableSt.setInt(2, userId);
			callableSt.setInt(3,0);
			callableSt.setString(4, projectName);
			
			ResultSet rs2 = null;
			rs2 = callableSt.executeQuery();
			JSONObject object1=null;
			JSONObject object2=null;
			while (rs1.next()) {
				object1=new JSONObject();
	        	object1.put("modid", rs1.getInt("pk_mod_id"));
	        	object1.put("modname",  rs1.getString("mod_name"));
	        	array1.put(object1);
			}
			while (rs2.next()) {
				int flag=0;
				rs1 = callableSt1.executeQuery();
				while(rs1.next())
				{
					if(rs1.getInt("pk_mod_id")==rs2.getInt("pk_mod_id"))
					{
					  flag=1;	
					}
				}
				if(flag==0)
				{
				object2=new JSONObject();
	        	object2.put("modid", rs2.getInt("pk_mod_id"));
	        	object2.put("modname",  rs2.getString("mod_name"));
	        	array2.put(object2);
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
		array.put(array1);
		array.put(array2);
		return array;
	
	}

	@Override
	public JSONArray getsubmodules(int id,int userid) {
		final String procedureCall = "{call proc_employeeupdatestatus(?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getsubmodules");
			callableSt.setInt(2, userid);
			callableSt.setInt(3, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			CallableStatement callableSt1 = connection.prepareCall(procedureCall);
			callableSt1.setString(1, "getAllsubmodules");
			callableSt1.setInt(2, userid);
			callableSt1.setInt(3, id);
			ResultSet rs1 = null;
			rs1 = callableSt1.executeQuery();

			JSONObject object1=null;
			JSONObject object2=null;
			while (rs.next()) {
				object1=new JSONObject();
	        	object1.put("submodid", rs.getInt("pk_submod_id"));
	        	object1.put("submodname",  rs.getString("submod_name"));
	        	array1.put(object1);
			}
			while (rs1.next()) {
				int flag=0;
				rs = callableSt.executeQuery();
				while(rs.next())
				{
					if(rs.getInt("pk_submod_id")==rs1.getInt("fk_submodule_id"))
					{
					  flag=1;	
					}
				}
				if(flag==0)
				{
				object2=new JSONObject();
	        	object2.put("submodid", rs1.getInt("fk_submodule_id"));
	        	object2.put("submodname",  rs1.getString("submod_name"));
	        	array2.put(object2);
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
		array.put(array1);
		array.put(array2);
		return array;
	
      	}

	@Override
	public void add(Project pro, int userId) {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbctemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_employeeupdatestatus(?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "add");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, pro.getTaskId());
			callableSt.setString(4, pro.getProjectName());
			callableSt.setInt(5, pro.getModuleId());
			callableSt.setInt(6, pro.getSubModuleId());
			callableSt.setString(7,  pro.getTime());
			callableSt.setString(8, pro.getSubModuleDescriptionIndividually());
			callableSt.setDate(9,null);
			callableSt.setInt(10, userId);
			callableSt.execute();
			
			
			if(pro.getTaskId()!=0)
			{
			procedureCall = "{call proc_employeeupdatestatus(?,?,?,?,?,?,?,?,?,?,?,?)}";
			 callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "update");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setString(4,null);
			callableSt.setInt(5, 0);
			callableSt.setInt(6,0);
			callableSt.setString(7,  null);
			callableSt.setString(8, null);
			callableSt.setDate(9,null);
			callableSt.setInt(10, 0);
			callableSt.setInt(11, pro.getIsCompleted());
			callableSt.setInt(12, pro.getTaskId());
			//callableSt.execute();
			callableSt.execute();
			}
			else
			{
				procedureCall = "{call proc_employeeupdatestatus(?,?,?,?,?,?,?,?,?,?,?,?)}";
				 callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "updatesubmoduleiscompleted");
				callableSt.setInt(2, 0);
				callableSt.setInt(3, 0);
				callableSt.setString(4,null);
				callableSt.setInt(5, 0);
				callableSt.setInt(6,0);
				callableSt.setString(7,  null);
				callableSt.setString(8, null);
				callableSt.setDate(9,null);
				callableSt.setInt(10, 0);
				callableSt.setInt(11, pro.getIsCompleted());
				callableSt.setInt(12, pro.getSubModuleId());
				//callableSt.execute();
				callableSt.execute();
			}
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
	public void generateExcel(int userId) {
			final String procedureCall = "{call proc_employeeupdatestatus(?,?)}";
			Connection connection = null;
			try {
				connection = jdbctemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "generateexcel");
				callableSt.setInt(2,userId );
				ResultSet rs = null;
				rs = callableSt.executeQuery();
			try {
			    HSSFWorkbook workbook = new HSSFWorkbook();
			    HSSFSheet sheet = workbook.createSheet("lawix10");
			    HSSFRow rowhead = sheet.createRow((short) 0);
			    rowhead.createCell((short) 0).setCellValue("Employee Name");
			    rowhead.createCell((short) 1).setCellValue("Project Name");
			    rowhead.createCell((short) 2).setCellValue("Module Name");
			    rowhead.createCell((short) 3).setCellValue("SubModule Name");
			    rowhead.createCell((short) 4).setCellValue("Hours");
			    rowhead.createCell((short) 5).setCellValue("Description");
			    int i = 1;
			    while (rs.next()){
			        HSSFRow row = sheet.createRow((short) i);
			        row.createCell((short) 0).setCellValue(rs.getString("username"));
			        row.createCell((short) 1).setCellValue(rs.getString("project_name"));
			        row.createCell((short) 2).setCellValue(rs.getString("mod_name"));
			        row.createCell((short) 3).setCellValue(rs.getString("submod_name"));
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
			}
			 catch (SQLException e) {
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
	public JSONArray gettasks(int id, int userid) {
		final String procedureCall = "{call proc_employeeupdatestatus(?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "gettasks");
			callableSt.setInt(2, userid);
			callableSt.setInt(3, id);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object1=null;
			while (rs.next()) {
				object1=new JSONObject();
	        	object1.put("taskid", rs.getInt("pk_task_id"));
	        	object1.put("taskname",  rs.getString("task_name"));
	        	array.put(object1);
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


	





