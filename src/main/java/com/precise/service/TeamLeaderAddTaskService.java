package com.precise.service;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Project;

public interface TeamLeaderAddTaskService {

	public List<Project> getallprojects(int userid);
	public JSONArray getsubmodules(int moduleid, int userid);
	public void savetasks(Project pro);
	public void updateindividualtask(Project pro);
	public void deletetask(int id);
	public List<Project> getalltasks(int submoduleid);
	public List<Project> getallthetasks(int userid);
	
	public JSONArray gettasks(int submoduleid);
	public JSONArray getothertasks(int taskid, int submoduleid);
	
	public void dependency(Project pro);
}
