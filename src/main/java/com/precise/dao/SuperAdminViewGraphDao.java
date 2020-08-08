package com.precise.dao;

import java.util.List;

import com.precise.model.Project;

public interface SuperAdminViewGraphDao {

	public List<Project> getthegraphofsubmodules(Project pro);
	public List<Project> getthegraphofprojects(Project pro);
}
