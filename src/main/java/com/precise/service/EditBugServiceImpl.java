package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.EditBugDao;
import com.precise.model.Bug;

@Service
public class EditBugServiceImpl implements EditBugService{

	@Autowired
	EditBugDao editbugdao;
	
	@Override
	public List<Bug> getbugdetails(int bugno) {
        return editbugdao.getbugdetails(bugno);
	}
	@Override
	public void updatetheinformation(Bug emp,String fname,int bugno,int id) {
		editbugdao.updatetheinformation(emp,fname,bugno,id);		
	}
	/*@Override
	public void updateDetails(Employee emp, int userid) {
        adminchangedetails.updateDetails(emp, userid);
	}*/
	@Override
	public List<Bug> getthemodules(int pid) {
		return editbugdao.getthemodules(pid);
	}
	@Override
	public List<Bug> getthesubmodules(int mid) {
		return editbugdao.getthesubmodules(mid);
	}
	@Override
	public List<Bug> gettheassigned(int smid) {
		return editbugdao.gettheassigned(smid);
	}
	@Override
	public byte[] getImageByteData(String bugNo) {
		editbugdao.getImageByteData(bugNo);
		return null;
	}
	

}
