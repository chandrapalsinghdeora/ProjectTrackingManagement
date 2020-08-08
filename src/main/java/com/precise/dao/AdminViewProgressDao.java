package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Employee;
import com.precise.model.Project;

public interface AdminViewProgressDao {

	public List<Project> getAllProject(int userid);
	public List<Employee> getallusers();
	
	public void createpwexcel(int projectid);
	public void createmwexcel(int projectid, int moduleid);
	public void createuwexcel(int userid);
	public void createsmwexcel(int projectid, int moduleid, int submoduleid);
	
	public JSONArray getmodules(int projectid);
	public JSONArray getsubmodules(int moduleid);
}
