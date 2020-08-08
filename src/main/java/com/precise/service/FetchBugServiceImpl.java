package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.FetchBugDao;
import com.precise.model.Bug;
@Service

public class FetchBugServiceImpl implements FetchBugService {
	@Autowired
	FetchBugDao fetchbugdao;


    @Override
	public List<Bug> gettheinformation(int userId) {
		return fetchbugdao.gettheinformation(userId);
	}
    @Override
   	public List<Bug> gettheinformationpm(int id) {
   		return fetchbugdao.gettheinformationpm(id);
   	}
    @Override
    public List<Bug> getAllAssign(){
    	return fetchbugdao.getAllAssign();
    }
    @Override
   	public List<Bug> getthereport(int id) {
   		return fetchbugdao.getthereport(id);
   	}
    @Override
   	public void generateExcel() {
   		fetchbugdao.generateExcel();
   	}
	@Override
	public List<Bug> getthereportspecific(String date1, String date2, int id) {
		return fetchbugdao.getthereportspecific(date1,date2,id);
	}
	@Override
	public List<Bug> getthereportproject(String project) {
		return fetchbugdao.getthereportproject(project);
	}
	@Override
	public List<Bug> getthereportassign(String assign, int id) {
		return fetchbugdao.getthereportassign(assign,id);
	}
	 @Override                                                    
	   	public List<Bug> ifile(int bugno) {
	   		return fetchbugdao.ifile(bugno);
	    }
	 @Override                                                    
	   	public List<Bug> ifileres(int bugno) {
	   		return fetchbugdao.ifileres(bugno);
	    }
	}


