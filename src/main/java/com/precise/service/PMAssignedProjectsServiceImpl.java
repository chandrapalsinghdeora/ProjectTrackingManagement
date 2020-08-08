package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.PMAssignedProjectsDao;
import com.precise.model.Project;

@Service
public class PMAssignedProjectsServiceImpl implements PMAssignedProjectsService {
	
	@Autowired
	PMAssignedProjectsDao pmassignprojectdao;


	
	@Override
	public List<Project> getAllProject(int userId) {
		return pmassignprojectdao.getAllProject(userId);
	}



	@Override
	public List<Project> getAssignedProjects(int id) {
		
		return pmassignprojectdao.getAssignedProjects(id);
	}


	



}
