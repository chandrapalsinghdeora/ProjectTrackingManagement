package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Employee;
import com.precise.model.Project;

public interface TeamLeaderAssignModuleDao {
	public List<Project> getAssignedProjects(int userId);
	public JSONArray getmodules(String projectName,int userid);
	public JSONArray getassignmodules(String projectName,int userid);
	public JSONArray getsubmodules(int id,int userid);
	public JSONArray getthesubmodules(int id,int userid);
	public JSONArray gettasks(int id,int userid);
	public JSONArray getemployees(int id,int userid);
	public List<Project> getAssignedModules(int id);
	public List<Employee> getallemployees(String empDesignation,int userId);
	public void assignittoemployees(Project pro,int userId);
	public void assigntasktoemployees(Project pro,int userId);
	public JSONArray getalltheemployees(String designation,int userid);
}
