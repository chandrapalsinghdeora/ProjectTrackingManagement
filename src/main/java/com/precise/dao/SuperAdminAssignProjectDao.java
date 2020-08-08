package com.precise.dao;

import java.util.List;

import com.precise.model.Employee;
import com.precise.model.Project;

public interface SuperAdminAssignProjectDao {

	public List<Project> getAllProject();
	public void assignthemodule(Project pro);
	public List<Employee> getallpm();
}
