package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.Project;

public interface AdminAddProjectService {
	
	public void addtheinformation(Project pro);
	public List<Project> getallprojects(Project pro, int userId);
	public void savemoduledetails(Project pro)throws SQLException;
	public void updateindividualmodule(Project pro);
	public void updateindividualsubmodule(Project pro);
	public void deletemodule(int moduleid);
	public void deletesubmodule(int submoduleid);
	
}
