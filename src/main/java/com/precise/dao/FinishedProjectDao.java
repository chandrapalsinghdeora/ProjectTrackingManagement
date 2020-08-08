package com.precise.dao;

import java.util.List;

import com.precise.model.Project;

public interface FinishedProjectDao {
	public List<Project> getAllFinishedProjects( int userId);
	public List<Project> getAllPMFinishedProjects( int userId);
    

}
