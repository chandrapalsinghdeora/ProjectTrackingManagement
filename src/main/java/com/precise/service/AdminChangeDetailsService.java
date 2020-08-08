package com.precise.service;

import java.util.List;

import com.precise.model.Employee;

public interface AdminChangeDetailsService {

	public List<Employee> getadminpreviousdetails(int userid);
	public void updateDetails(Employee emp, int userid);
}
