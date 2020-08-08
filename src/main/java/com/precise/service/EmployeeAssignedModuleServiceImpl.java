package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.EmployeeAssignedModuleDao;
import com.precise.model.Project;

@Service
public class EmployeeAssignedModuleServiceImpl implements EmployeeAssignedModuleService {
	
	@Autowired
	EmployeeAssignedModuleDao employeeassignmoduledao;


	@Override
	public List<Project> getAssignedSubModules(int id, int userId) {
		return employeeassignmoduledao.getAssignedSubModules(id, userId);
	}


	@Override
	public List<Project> getAllProjects(int userId) {
		
		return employeeassignmoduledao.getAllProjects(userId);
	}

	
	@Override
	public List<Project> getAllTProjects(int userId) {
		
		return employeeassignmoduledao.getAllTProjects(userId);
	}


	@Override
	public JSONArray getmodules(int projectId,int userId) {
		
		return employeeassignmoduledao.getmodules(projectId,userId);
	}


	@Override
	public JSONArray getjsonsubmodules(int moduleid, int userId) {
		return employeeassignmoduledao.getjsonsubmodules(moduleid, userId);
	}


	@Override
	public List<Project> getAssignedTasks(int id, int userId) {
		return employeeassignmoduledao.getAssignedTasks(id, userId);
	}


	@Override
	public JSONArray gettmodules(int projectId, int userId) {
		return employeeassignmoduledao.gettmodules(projectId,userId);
	}
	
	



}
