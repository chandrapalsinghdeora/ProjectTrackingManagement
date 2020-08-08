package com.precise.service;

import java.util.List;

import com.precise.model.Bug;

public interface EditBugService {

	public List<Bug> getbugdetails(int bugno);
	public List<Bug> getthemodules(int pid);
	public List<Bug> getthesubmodules(int mid);
	public List<Bug> gettheassigned(int smid);
	public byte[] getImageByteData(String bugNo);
	//public void updateDetails(Employee emp, int userid);
	public void updatetheinformation(Bug emp,String fname,int bugno,int id);
}
