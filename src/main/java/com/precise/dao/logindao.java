package com.precise.dao;

import java.util.List;

import com.precise.model.Employee;

public interface logindao {
	
	public List<Employee> getAlldesignation();
	public void sendtheinformation(Employee emp);
	public List<Employee> get();
/*	
	public void send(Users users);
	public List<Users> get();
	public List<Users> getuser(String Str);*/
	public List<Employee> verifymail(String mail);
	public void updatetokenindb(String mail, int token);
	public void updateuserpassword(int token, String password);
}
