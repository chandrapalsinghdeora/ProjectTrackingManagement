package com.precise.dao;

import java.util.List;

import com.precise.model.Project;

public interface TeamLeaderAllProjectsDao {

	public List<Project> getallassignedprojects(int userId);
	public List<Project> getmodules(int projectId);
}
