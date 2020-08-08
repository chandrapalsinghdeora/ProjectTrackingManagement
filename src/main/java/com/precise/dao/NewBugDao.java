package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Bug;

public interface NewBugDao {
	public void sendtheinformation(Bug emp,String fname,int id);
	public List<Bug> getAllQAProjects(int userId);
	public List<Bug> getAllPMProjects(int userId);
	public JSONArray getmodules(int projectId);
	public JSONArray getsubmodules(int moduleId);
	public JSONArray getassigned(int submoduleId);
	
}
