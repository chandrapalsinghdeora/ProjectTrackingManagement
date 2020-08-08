package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.SuperAdminViewProgressExcelDao;
import com.precise.model.Employee;
import com.precise.model.Project;

@Service
public class SuperAdminViewProgressExcelServiceImpl implements SuperAdminViewProgressExcelService {

	@Autowired
	SuperAdminViewProgressExcelDao adminviewprogress;
	
	@Override
	public List<Project> getAllProject(int userid) {
		return adminviewprogress.getAllProject(userid);
	}

	@Override
	public void createpwexcel(int projectid) {
		adminviewprogress.createpwexcel(projectid);	
	}

	@Override
	public JSONArray getmodules(int projectid) {
        return adminviewprogress.getmodules(projectid);
	}

	@Override
	public void createmwexcel(int projectid, int moduleid) {
		adminviewprogress.createmwexcel(projectid, moduleid);	
	}

	@Override
	public List<Employee> getallusers() {
		return adminviewprogress.getallusers();
	}

	@Override
	public void createuwexcel(int userid) {
		adminviewprogress.createuwexcel(userid);	
	}

	@Override
	public JSONArray getsubmodules(int moduleid) {
		return adminviewprogress.getsubmodules(moduleid);
	}

	@Override
	public void createsmwexcel(int projectid, int moduleid, int submoduleid) {
		adminviewprogress.createsmwexcel(projectid, moduleid, submoduleid);	
	}

}
