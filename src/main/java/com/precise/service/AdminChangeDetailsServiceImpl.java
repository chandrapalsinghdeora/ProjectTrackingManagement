package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.AdminChangeDetailsDao;
import com.precise.model.Employee;

@Service
public class AdminChangeDetailsServiceImpl implements AdminChangeDetailsService{

	@Autowired
	AdminChangeDetailsDao adminchangedetails;
	
	@Override
	public List<Employee> getadminpreviousdetails(int userid) {
        return adminchangedetails.getadminpreviousdetails(userid);
	}

	@Override
	public void updateDetails(Employee emp, int userid) {
        adminchangedetails.updateDetails(emp, userid);
	}

}
