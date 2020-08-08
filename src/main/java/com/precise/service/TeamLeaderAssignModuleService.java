package com.precise.service;


import java.util.List;

import org.json.JSONArray;

import com.precise.model.Employee;
import com.precise.model.Project;

public interface TeamLeaderAssignModuleService {
	public List<Project> getAssignedProjects(int userId);
	public JSONArray getmodules(String projectName,int userId);
	public JSONArray getassignmodules(String projectName,int userId);
	public List<Project> getAssignedModules(int id);
	public List<Employee> getallemployees(String empDesignation,int userId);
	public void assignittoemployees(Project pro,int userId);
	public void assigntasktoemployees(Project pro,int userId);
	public JSONArray getsubmodules(int id,int userid);
	public JSONArray getthesubmodules(int id,int userid);
	public JSONArray gettasks(int id,int userid);
	public JSONArray getemployees(int id,int userid);
	public JSONArray getalltheemployees(String empDesignation,int userid);
}