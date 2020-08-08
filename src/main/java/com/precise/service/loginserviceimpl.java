package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.logindao;
import com.precise.model.Employee;


@Service
public class loginserviceimpl implements loginservice {

	@Autowired
	logindao logindaoit;

/*	public void send(Users users) {
		logindaoit.send(users);
		
	}

	public List<Users> get() {
		 System.out.println("loginserviceimpl.get()");
		 return logindaoit.get();
	}

	public List<Users> getuser(String Str) {
         System.out.println("loginserviceimpl.getuser()");
         return logindaoit.getuser(Str);
	}*/

	public List<Employee> getAlldesignation() {
         return logindaoit.getAlldesignation();
	}

	@Override
	public void sendtheinformation(Employee emp) {
		  logindaoit.sendtheinformation(emp);
	}
	 
	@Override
    public List<Employee> get()
	{
		return logindaoit.get();
	}

	@Override
	public List<Employee> verifymail(String mail) {
		return logindaoit.verifymail(mail);
	}

	@Override
	public void updatetokenindb(String mail, int token) {
	    logindaoit.updatetokenindb(mail, token);
	}

	@Override
	public void updateuserpassword(int token, String password) {
		logindaoit.updateuserpassword(token, password);
	}
}
