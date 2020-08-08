package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Project;

public interface EmployeeAssignedModuleDao {
	
	public List<Project> getAllProjects(int userId);
	
	public List<Project> getAllTProjects(int userId);
	
	public JSONArray getmodules(int projectId,int userId);
	
	public JSONArray gettmodules(int projectId,int userId);

	public List<Project> getAssignedSubModules(int id, int userId);
	
	public List<Project> getAssignedTasks(int id, int userId);
	
	public JSONArray getjsonsubmodules(int moduleId,int userId);
}
