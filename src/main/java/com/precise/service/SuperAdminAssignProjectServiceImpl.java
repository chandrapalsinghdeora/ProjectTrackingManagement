package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.SuperAdminAssignProjectDao;
import com.precise.model.Employee;
import com.precise.model.Project;

@Service
public class SuperAdminAssignProjectServiceImpl implements SuperAdminAssignProjectService {

	@Autowired
	SuperAdminAssignProjectDao superadminassignproject;
	
	@Override
	public List<Project> getAllProject() {
		return superadminassignproject.getAllProject();
	}

	@Override
	public void assignthemodule(Project pro) {
		superadminassignproject.assignthemodule(pro);
	}

	@Override
	public List<Employee> getallpm() {
		return superadminassignproject.getallpm();
	}

}
