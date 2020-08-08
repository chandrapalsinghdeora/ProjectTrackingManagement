package com.precise.dao;

import java.util.List;

import com.precise.model.Employee;

public interface AdminPendingApprovalDao {

		public List<Employee> getAllPendingApproval();
		public void approverequest(int id);
		public void rejectrequest(int id);
		
		public List<Employee> getAllApproved();
        public void approverole(int userid, int roleid);
}
