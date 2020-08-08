package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.AdminAddProjectDao;
import com.precise.model.Project;

@Service
public class AdminAddProjectServiceImpl implements AdminAddProjectService {
	
	@Autowired
	AdminAddProjectDao adminaddprojectdao;


	@Override
	public void addtheinformation(Project pro) {
		adminaddprojectdao.addtheinformation(pro);		
	}


	@Override
	public List<Project> getallprojects(Project pro, int userId) {
          return adminaddprojectdao.getallprojects(pro, userId);
 	}


	@Override
	public void savemoduledetails(Project pro) throws SQLException {
		adminaddprojectdao.savemoduledetails(pro);
	}


	@Override
	public void updateindividualmodule(Project pro) {
		adminaddprojectdao.updateindividualmodule(pro);
	}


	@Override
	public void deletemodule(int moduleid) {
		adminaddprojectdao.deletemodule(moduleid);
	}


	@Override
	public void updateindividualsubmodule(Project pro) {
		adminaddprojectdao.updateindividualsubmodule(pro);
	}


	@Override
	public void deletesubmodule(int submoduleid) {
		adminaddprojectdao.deletesubmodule(submoduleid);
	}




}
