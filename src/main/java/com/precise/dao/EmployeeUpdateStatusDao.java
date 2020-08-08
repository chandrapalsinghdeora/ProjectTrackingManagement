package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Project;

public interface EmployeeUpdateStatusDao {
	public List<Project> getAllProjects(int userId);
	public JSONArray getmodules(String projectName,int userId);
	public JSONArray getsubmodules(int id,int userid);
	public JSONArray gettasks(int id,int userid);
	public void add(Project pro, int userId);
	public void generateExcel(int userId);

}
