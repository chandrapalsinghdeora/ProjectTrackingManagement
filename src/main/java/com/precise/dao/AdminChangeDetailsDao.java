package com.precise.dao;

import java.util.List;

import com.precise.model.Employee;

public interface AdminChangeDetailsDao {
	public List<Employee> getadminpreviousdetails(int userid);
	public void updateDetails(Employee emp, int userid);
}
