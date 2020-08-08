package com.precise.dao;

import java.util.List;


import com.precise.model.Project;

public interface PMAssignedProjectsDao {
	public List<Project> getAllProject(int userId);
	public List<Project> getAssignedProjects(int id);
	
}
