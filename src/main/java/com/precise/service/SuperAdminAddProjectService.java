package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;

import com.precise.model.Project;

public interface SuperAdminAddProjectService {

	public void addtheinformation(Project pro);
	public void edittheinformation(Project pro);
	public void deletetheproject(int projectid);
	public List<Project> getallprojects(Project pro);
	
	public List<Project> getallModules();
	public List<Project> getallpmprojects(int userid);
	
	public List<Project> getallpmModules(int userid);
	
	public JSONArray getmodules(int projectid);
	public List<Project> getallsubModules();
	public List<Project> getallpmsubModules(int userid);
	
	public void savesubmoduledetails(Project pro)throws SQLException;
}
