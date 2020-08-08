package com.precise.service;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.*;

public interface EmployeeUpdateStatusService {
	public List<Project> getAllProjects(int userId);
	public JSONArray getmodules(String projectName,int userId);
	public JSONArray getsubmodules(int id,int userid);
	public JSONArray gettasks(int id,int userid);
	public void add(Project pro, int userId);
	public void generateExcel(int userId);
	
}