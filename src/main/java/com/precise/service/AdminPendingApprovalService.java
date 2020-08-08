package com.precise.service;

import java.util.List;

import com.precise.model.Employee;

public interface AdminPendingApprovalService {
	
	public List<Employee> getAllPendingApproval();
	public void approverequest(int id);
	public void rejectrequest(int id);
	
	public List<Employee> getAllApproved();
	public void approverole(int userid,int roleid);
}
