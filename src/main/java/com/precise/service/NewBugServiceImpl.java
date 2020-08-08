package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.NewBugDao;
import com.precise.model.Bug;

@Service
public class NewBugServiceImpl implements NewBugService {
	
	@Autowired
	NewBugDao newbugdao;


	@Override
	public void sendtheinformation(Bug emp,String fname,int id) {
		newbugdao.sendtheinformation(emp,fname,id);		
	}
	@Override
	public List<Bug> getAllQAProjects(int userId) {
		
		return newbugdao.getAllQAProjects(userId);
	}


	@Override
	public JSONArray getmodules(int projectId) {
		
		return newbugdao.getmodules(projectId);
	}


	@Override
	public JSONArray getsubmodules(int modueleId) {
		return newbugdao.getsubmodules(modueleId);
	}
	
	@Override
	public JSONArray getassigned(int submodueleId) {
		return newbugdao.getassigned(submodueleId);
	}
	@Override
	public List<Bug> getAllPMProjects(int userId) {
		return newbugdao.getAllPMProjects(userId);
	}
	
}
