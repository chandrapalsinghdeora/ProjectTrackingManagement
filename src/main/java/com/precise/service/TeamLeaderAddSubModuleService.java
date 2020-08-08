package com.precise.service;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Project;

public interface TeamLeaderAddSubModuleService {
	
	public List<Project> getallprojects(int userid);
	public List<Project> getalltlprojects(int userid);
//	public void savesubmoduledetails(Project pro)throws SQLException;
	public JSONArray getmodules(int projectid, int userid);
	public void savesubmodules(Project pro);
}
