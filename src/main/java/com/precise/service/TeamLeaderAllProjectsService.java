package com.precise.service;

import java.util.List;

import com.precise.model.Project;

public interface TeamLeaderAllProjectsService {

	public List<Project> getallassignedprojects(int userId);
	public List<Project> getmodules(int projectId);
}
