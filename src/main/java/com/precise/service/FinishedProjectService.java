package com.precise.service;

import java.util.List;

import com.precise.model.Project;

public interface FinishedProjectService {
	
	public List<Project> getAllFinishedProjects(int userid);
	public List<Project> getAllPMFinishedProjects(int userid);

}
