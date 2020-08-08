package com.precise.service;

import java.util.List;

import com.precise.model.Employee;
import com.precise.model.Project;

public interface SuperAdminAssignProjectService {

	public List<Project> getAllProject();
	public List<Employee> getallpm();
	public void assignthemodule(Project pro);
}
