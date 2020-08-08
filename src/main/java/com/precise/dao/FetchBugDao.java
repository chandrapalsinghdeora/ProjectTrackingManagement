package com.precise.dao;

import java.util.List;

import com.precise.model.Bug;

public interface FetchBugDao {
	public List<Bug> gettheinformation(int userId);
	public List<Bug> gettheinformationpm(int id);
	public List<Bug> getAllAssign();
	public List<Bug> getthereport(int id);
	public void generateExcel();
	public List<Bug> getthereportspecific (String date1,String date2,int id);
	public List<Bug> getthereportproject(String project);
	public List<Bug> getthereportassign (String assign,int id);
	public  List<Bug>  ifile(int bugno); 
	public  List<Bug>  ifileres(int bugno); 
}
