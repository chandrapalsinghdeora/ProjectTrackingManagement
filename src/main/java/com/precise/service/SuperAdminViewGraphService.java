package com.precise.service;

import java.util.List;

import com.precise.model.Project;

public interface SuperAdminViewGraphService {

	public List<Project> getthegraphofsubmodules(Project pro);
	public List<Project> getthegraphofprojects(Project pro);
}
