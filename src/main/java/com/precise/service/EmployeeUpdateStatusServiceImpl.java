package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.EmployeeUpdateStatusDao;
import com.precise.model.Project;

@Service
public class EmployeeUpdateStatusServiceImpl implements EmployeeUpdateStatusService {
	
	@Autowired
	EmployeeUpdateStatusDao employeeupdatestatusdao;


	
	@Override
	public List<Project> getAllProjects(int userId) {
		
		return employeeupdatestatusdao.getAllProjects(userId);
	}


	@Override
	public JSONArray getmodules(String projectName,int userId) {
		
		return employeeupdatestatusdao.getmodules(projectName,userId);
	}


	@Override
	public JSONArray getsubmodules(int id,int userid) {
		return employeeupdatestatusdao.getsubmodules(id,userid);
	}


	@Override
	public void add(Project pro, int userId) {
		employeeupdatestatusdao.add(pro, userId);
	}
	
	
	
	@Override
	public void generateExcel(int userId) {
		employeeupdatestatusdao.generateExcel(userId);
		
	}


	@Override
	public JSONArray gettasks(int id, int userid) {
		return employeeupdatestatusdao.gettasks(id,userid);
		
	}
	



}
