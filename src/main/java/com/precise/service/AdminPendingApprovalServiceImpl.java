package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.AdminPendingApprovalDao;
import com.precise.model.Employee;

@Service
public class AdminPendingApprovalServiceImpl implements AdminPendingApprovalService {
	
	@Autowired
	AdminPendingApprovalDao adminpendingapprovaldaoit;

	public List<Employee> getAllPendingApproval() {
	         return adminpendingapprovaldaoit.getAllPendingApproval();
		}

	@Override
	public void approverequest(int id) {
		adminpendingapprovaldaoit.approverequest(id);		
	}

	@Override
	public void rejectrequest(int id) {
		adminpendingapprovaldaoit.rejectrequest(id);
	}

	@Override
	public List<Employee> getAllApproved() {
		return adminpendingapprovaldaoit.getAllApproved();
	}

	@Override
	public void approverole(int userid, int roleid) {
		adminpendingapprovaldaoit.approverole(userid, roleid);
	}

}
