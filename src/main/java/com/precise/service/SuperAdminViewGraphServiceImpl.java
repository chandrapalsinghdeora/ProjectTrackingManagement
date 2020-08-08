package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.SuperAdminViewGraphDao;
import com.precise.model.Project;

@Service
public class SuperAdminViewGraphServiceImpl implements SuperAdminViewGraphService{

	@Autowired
	SuperAdminViewGraphDao superadminviewgraph;
	
	@Override
	public List<Project> getthegraphofsubmodules(Project pro) {
		return superadminviewgraph.getthegraphofsubmodules(pro);
	}
	
	@Override
	public List<Project> getthegraphofprojects(Project pro) {
		return superadminviewgraph.getthegraphofprojects(pro);
	}

}
