package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.TeamLeaderAssignModuleDao;
import com.precise.model.Employee;
import com.precise.model.Project;
@Service
public class TeamLeaderAssignModuleServiceImpl implements TeamLeaderAssignModuleService {
	@Autowired
	TeamLeaderAssignModuleDao tlassignmoduledao;
	@Override
	public List<Project> getAssignedProjects(int userId) {
		return tlassignmoduledao.getAssignedProjects(userId);
	}
	@Override
	public JSONArray getmodules(String projectName,int userid) {
		 return tlassignmoduledao.getmodules(projectName,userid);
	}
	@Override
	public List<Employee> getallemployees(String empDesignation, int userId) {
		 return tlassignmoduledao.getallemployees(empDesignation,userId);
	}
	@Override
	public void assignittoemployees(Project pro,int userId) {
		tlassignmoduledao.assignittoemployees(pro,userId);
	}
	@Override
	public List<Project> getAssignedModules(int id) {
		return tlassignmoduledao.getAssignedModules(id);
	}
	@Override
	public JSONArray getassignmodules(String projectName, int userId) {
		
		return tlassignmoduledao.getassignmodules(projectName,userId);
	}
	@Override
	public JSONArray getsubmodules(int id, int userid) {
		return tlassignmoduledao.getsubmodules(id,userid);
	}
	@Override
	public JSONArray gettasks(int id, int userid) {
		
		return tlassignmoduledao.gettasks(id,userid);	}
	@Override
	public void assigntasktoemployees(Project pro, int userId) {
		tlassignmoduledao.assigntasktoemployees(pro,userId);
		
	}
	@Override
	public JSONArray getemployees(int id, int userid) {
		return tlassignmoduledao.getemployees(id,userid);
	}
	
	@Override
	public JSONArray getalltheemployees(String empDesignation, int userid) {
		return tlassignmoduledao.getalltheemployees(empDesignation,userid);
	}
	@Override
	public JSONArray getthesubmodules(int id, int userid) {
		return tlassignmoduledao.getthesubmodules(id,userid);
	}

}