package com.precise.service;

import java.util.List;


import com.precise.model.*;

public interface PMAssignedProjectsService {
	public List<Project> getAllProject(int userId);
	public List<Project> getAssignedProjects(int id);
	
}
