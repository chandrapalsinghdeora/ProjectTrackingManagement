
package com.precise.dao;

import java.util.List;

import com.precise.model.Bug;

public interface EditBugDao {
	public List<Bug> getbugdetails(int bugno);
	public List<Bug> getthemodules(int pid);
	public List<Bug> getthesubmodules(int mid);
	public List<Bug> gettheassigned(int smid);
	public void updatetheinformation(Bug emp,String fname, int bugno,int id);
	public byte[] getImageByteData(String bugNo);
	//public void updateDetails(Employee emp, int userid);
}
