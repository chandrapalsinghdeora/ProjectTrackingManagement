package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.FinishedProjectDao;
import com.precise.model.Project;

@Service
public class FinishedProjectServiceImpl implements FinishedProjectService {
	
	@Autowired
	FinishedProjectDao finishedprojectdao;

	@Override
	public List<Project> getAllFinishedProjects(int userid) {
		return finishedprojectdao.getAllFinishedProjects(userid);
	}

	@Override
	public List<Project> getAllPMFinishedProjects(int userid) {
		return finishedprojectdao.getAllPMFinishedProjects(userid);
	}


	


}
