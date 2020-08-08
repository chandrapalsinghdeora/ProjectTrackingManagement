package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.AdminAssignProjectDao;
import com.precise.model.Employee;
import com.precise.model.Project;

@Service
public class AdminAssignProjectServiceImpl implements AdminAssignProjectService {
	
	@Autowired
	AdminAssignProjectDao adminassignprojectdao;


	@Override
	public List<Employee> getAllDesignation() {
		return adminassignprojectdao.getAllDesignation();		
	}
	
	@Override
	public List<Project> getAllProject(int userid) {
		return adminassignprojectdao.getAllProject(userid);
	}


	@Override
	public JSONArray getteamleaders(String empDesignation) {
        return adminassignprojectdao.getteamleaders(empDesignation);
	}

	@Override
	public void assignthemodule(Project pro) {
		adminassignprojectdao.assignthemodule(pro);
	}

	@Override
	public JSONArray getmodules(int id) {
        return adminassignprojectdao.getmodules(id);
	}



}
