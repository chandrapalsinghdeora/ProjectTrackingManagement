package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.TeamLeaderAddTaskDao;
import com.precise.model.Project;

@Service
public class TeamLeaderAddTaskServiceImpl implements TeamLeaderAddTaskService {

	@Autowired
	TeamLeaderAddTaskDao teamleaderaddtask;
	
	@Override
	public List<Project> getallprojects(int userid) {
		return teamleaderaddtask.getallprojects(userid);
	}

	@Override
	public JSONArray getsubmodules(int moduleid, int userid) {
		return teamleaderaddtask.getsubmodules(moduleid, userid);
	}

	@Override
	public void savetasks(Project pro) {
		teamleaderaddtask.savetasks(pro);
	}

	@Override
	public List<Project> getalltasks(int submoduleid) {
		return teamleaderaddtask.getalltasks(submoduleid);
	}


	@Override
	public JSONArray gettasks(int submoduleid) {
		return teamleaderaddtask.gettasks(submoduleid);
	}

	@Override
	public JSONArray getothertasks(int taskid, int submoduleid) {
		return teamleaderaddtask.getothertasks(taskid, submoduleid);
	}

	@Override
	public void dependency(Project pro) {
		teamleaderaddtask.dependency(pro);
	}

	@Override
	public List<Project> getallthetasks(int userid) {
		return teamleaderaddtask.getallthetasks(userid);
	}

	@Override
	public void updateindividualtask(Project pro) {
		teamleaderaddtask.updateindividualtask(pro);
	}

	@Override
	public void deletetask(int id) {
		teamleaderaddtask.deletetask(id);
		
	}

}
