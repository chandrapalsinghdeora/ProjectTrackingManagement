package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.TeamLeaderAddSubModuleDao;
import com.precise.model.Project;

@Service
public class TeamLeaderAddSubModuleServiceImpl implements TeamLeaderAddSubModuleService{

	@Autowired
	TeamLeaderAddSubModuleDao teamleaderaddsubmoduledao;
	
	@Override
	public List<Project> getallprojects(int userid) {
		return teamleaderaddsubmoduledao.getallprojects(userid);
	}

	@Override
	public JSONArray getmodules(int projectid, int userid) {
		return teamleaderaddsubmoduledao.getmodules(projectid, userid);
	}

	@Override
	public void savesubmodules(Project pro) {
		teamleaderaddsubmoduledao.savesubmodules(pro);
	}

	@Override
	public List<Project> getalltlprojects(int userid) {
		return teamleaderaddsubmoduledao.getalltlprojects(userid);
	}


/*	@Override
	public void savesubmoduledetails(Project pro) throws SQLException {
		teamleaderaddsubmoduledao.savesubmoduledetails(pro);
	}
*/
}
