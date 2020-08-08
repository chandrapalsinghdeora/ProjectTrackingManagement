package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Project;

public interface TeamLeaderAddTaskDao {
	 public List<Project> getallprojects(int userid);
	 public JSONArray getsubmodules(int moduleid, int userid);
	 public void savetasks(Project pro);
	 public void updateindividualtask(Project pro);
	 public List<Project> getalltasks(int submoduleid);
	 public List<Project> getallthetasks(int userid);
	 public void deletetask(int id);
	 public JSONArray gettasks(int submoduleid);
	 public JSONArray getothertasks(int taskid, int submoduleid);
	 
	 public void dependency(Project pro);
}
