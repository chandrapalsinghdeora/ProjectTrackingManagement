package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.SuperAdminAddProjectDao;
import com.precise.model.Project;

@Service
public class SuperAdminAddProjectServiceImpl implements SuperAdminAddProjectService{

	@Autowired
	SuperAdminAddProjectDao superadminaddprojectdao;
	
	@Override
	public void addtheinformation(Project pro) {
		superadminaddprojectdao.addtheinformation(pro);		
	}
	
	@Override
	public void deletetheproject(int projectid) {
		superadminaddprojectdao.deletetheproject(projectid);		
	}

	@Override
	public void edittheinformation(Project pro) {
		superadminaddprojectdao.edittheinformation(pro);		
	}
	@Override
	public List<Project> getallprojects(Project pro) {
		return superadminaddprojectdao.getallprojects(pro);
	}

	@Override
	public List<Project> getallModules() {
		return superadminaddprojectdao.getallModules();
	}

	@Override
	public JSONArray getmodules(int projectid) {
		return superadminaddprojectdao.getmodules(projectid);
	}

	@Override
	public List<Project> getallsubModules() {
		return superadminaddprojectdao.getallsubModules();
	}

	@Override
	public void savesubmoduledetails(Project pro) throws SQLException {
		superadminaddprojectdao.savesubmoduledetails(pro);
	}

	@Override
	public List<Project> getallpmprojects(int userid) {
		return superadminaddprojectdao.getallpmprojects(userid);
	}

	@Override
	public List<Project> getallpmModules(int userid) {
		return superadminaddprojectdao.getallpmModules(userid);
	}

	@Override
	public List<Project> getallpmsubModules(int userid) {
		return superadminaddprojectdao.getallpmsubModules(userid);
	}




}
