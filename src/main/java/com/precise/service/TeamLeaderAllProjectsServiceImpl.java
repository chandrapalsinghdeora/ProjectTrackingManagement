package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.TeamLeaderAllProjectsDao;
import com.precise.model.Project;

@Service
public class TeamLeaderAllProjectsServiceImpl implements TeamLeaderAllProjectsService{

	@Autowired
	TeamLeaderAllProjectsDao teamleaderdao;
	
	@Override
	public List<Project> getallassignedprojects(int userId) {
		return teamleaderdao.getallassignedprojects(userId);
	}

	@Override
	public List<Project> getmodules(int projectId) {
        return teamleaderdao.getmodules(projectId);
	}

}
