package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Employee;
import com.precise.model.Project;

public interface AdminAssignProjectDao {
	public List<Employee> getAllDesignation();
	public List<Project> getAllProject(int userid);

	public void assignthemodule(Project pro);
	
	public JSONArray getteamleaders(String empDesignation);
	public JSONArray getmodules(int id);
}
