package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.TeamLeaderViewProgressDao;
import com.precise.model.Employee;
import com.precise.model.Project;
@Service
public class TeamLeaderViewProgressServiceImpl implements TeamLeaderViewProgressService {
	@Autowired
	TeamLeaderViewProgressDao tlviewprogressdao;
	@Override
	public List<Project> getAssignedProject(int userId) {
		return tlviewprogressdao.getAssignedProject(userId);
	}
	@Override
	public void getexcel(int id) {
		tlviewprogressdao.getexcel(id);
		
	}
	@Override
	public JSONArray getmodule(int id,int userid) {
		return tlviewprogressdao.getmodule(id,userid);

	}
	@Override
	public void getmoduleexcel(int id) {
		tlviewprogressdao.getmoduleexcel(id);
		
	}
	@Override
	public List<Employee> getAssignedUsers(int userId) {
		return tlviewprogressdao.getAssignedUsers(userId);
	}
	@Override
	public void getuserexcel(int id) {
		tlviewprogressdao.getuserexcel(id);
		
	}
	@Override
	public JSONArray getthesubmodules(int id) {
		return tlviewprogressdao.getthesubmodules(id);
	}
	@Override
	public void getsubmoduleexcel(int id) {
		
		tlviewprogressdao.getsubmoduleexcel(id);
	}
	@Override
	public JSONArray getthemodule(int id,int userid) {
		return tlviewprogressdao.getthemodule(id,userid);
	}
	

}