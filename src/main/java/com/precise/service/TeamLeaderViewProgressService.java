package com.precise.service;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Employee;
import com.precise.model.Project;

public interface TeamLeaderViewProgressService {
	public List<Project> getAssignedProject(int userId);
	public List<Employee> getAssignedUsers(int userId);
	public void getexcel(int id);
	public void getmoduleexcel(int id);
	public void getuserexcel(int id);
	public void getsubmoduleexcel(int id);
	public JSONArray getmodule(int id,int userid);
	public JSONArray getthemodule(int id,int userid);
	public JSONArray getthesubmodules(int id);
}
