package com.precise.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.mail.SendMail;
import com.precise.model.Employee;
import com.precise.model.SessionBean;
import com.precise.service.loginservice;

@Controller
public class logincontroller {
	
	
	@Autowired
	loginservice loginserve;
	
	@Autowired
	SendMail sendMail;


	@RequestMapping(value="/getemail",method = RequestMethod.GET)
	public ModelAndView getemail(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("Forgotgetmail");
	}
	

	@RequestMapping(value="/UpdatePassword",method = RequestMethod.GET)
	public ModelAndView getForgotupdatepassword(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelmap = new ModelAndView("Forgotupdatepassword");
		modelmap.addObject("token", Integer.parseInt(request.getParameter("tkn")));
		return modelmap;
	}
	
	@RequestMapping(value="/forgotemail",method = RequestMethod.POST)
	public ModelAndView email(HttpServletRequest request,HttpServletResponse response){
		/*SendMail s=new SendMail();*/
		String tomailid = request.getParameter("forgotemail");
		List<Employee> verifymail = loginserve.verifymail(tomailid);
		if(verifymail.size() == 0){
			String msg = "Sorry,Invalid Email";
			return new ModelAndView("Forgotgetmail", "msg", msg);
		}
		else{
			Random rand = new Random();
			int token = rand.nextInt(5000)+rand.nextInt(5000)+rand.nextInt(5000)+rand.nextInt(5000) + 1;
			
			loginserve.updatetokenindb(tomailid, token);
			
			String msg = "http://localhost:8080/ProjectTrackingManagement/UpdatePassword?tkn="+token;
			 sendMail.sendMail(msg,tomailid,"Password Update");
			return new ModelAndView("index");
		}
	}

	@RequestMapping(value="/updateuserpassword"/*,method = RequestMethod.POST*/)
	public ModelAndView updateuserpassword(HttpServletRequest request,HttpServletResponse response){
		int token = Integer.parseInt(request.getParameter("token"));
		String password = request.getParameter("userPassword");
		loginserve.updateuserpassword(token, password);
		return new ModelAndView("index");
	}
	
	
	@RequestMapping(value="/registerit", method = RequestMethod.GET)
	public ModelAndView registerIt(HttpServletRequest request,HttpServletResponse response){
		List<Employee> teamlist = loginserve.getAlldesignation();
		request.setAttribute("teamlist", teamlist);
		return new ModelAndView("register");
	}
	
	
	
	@RequestMapping(value="/sendtodb", method = RequestMethod.POST)
	public ModelAndView sendit(Employee emp,HttpServletRequest request,HttpServletResponse response){
        loginserve.sendtheinformation(emp);
        return new ModelAndView("index");
	}	
	


	@RequestMapping(value="/login",method = {RequestMethod.POST,RequestMethod.GET})
	 public ModelAndView login(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		  String dbusername,dbpassword,dbname,dbrolename;
		  int dbuserid,dbroleid;
		  int isverified=0;
	      String check = "false";
		
		  String currentname="";
		  String currentusername="";
          int currentuserid=0;
          int currentroleid=0;
          String currentrolename="";
		  

		  String name = request.getParameter("name");
		  String password = request.getParameter("password");
		  
		  
		  List<Employee> list = loginserve.get();
		  
		  for(Employee emp: list){

			  dbusername = emp.getUserName();
			  dbpassword = emp.getUserPassword();
			  dbuserid = emp.getUserId();
			  dbname = emp.getName();
			  dbroleid = emp.getRoleId();
			  dbrolename = emp.getRoleName();
			  isverified = emp.getIsVerified();
			  System.out.println("isverified::"+isverified);
			  if(dbroleid == 4 && dbusername.equals(name) && dbpassword.equals(password)){
				  currentname = dbname;
				  currentuserid = dbuserid;   
				  currentusername = dbusername;
				  currentroleid = dbroleid;
				  currentrolename = dbrolename;
		    	  
				  check = "satrue";
		    	  break;
			  }
			  
			  else if(dbroleid == 1 && dbusername.equals(name) && dbpassword.equals(password)){
				  currentname = dbname;
				  currentuserid = dbuserid;   
				  currentusername = dbusername;
				  currentroleid = dbroleid;
				  currentrolename = dbrolename;
		    	  
				  check = "pmtrue";
		    	  break;
			  }
			  else if(dbroleid == 2 && dbusername.equals(name) && dbpassword.equals(password))
			  {
					  currentname = dbname;
					  currentuserid = dbuserid;   
					  currentusername = dbusername;
					  currentroleid = dbroleid;
					  currentrolename = dbrolename;
			    	  
					  check = "tltrue";
			    	  break;
				  
			  }
			  else if(dbroleid == 3 && dbusername.equals(name) && dbpassword.equals(password))
			  {
					  currentname = dbname;
					  currentuserid = dbuserid;   
					  currentusername = dbusername;
					  currentroleid = dbroleid;
					  currentrolename = dbrolename;
			    	  
					  check = "emptrue";
			    	  break;
				  
			  }
			  else if(dbroleid == 5 && dbusername.equals(name) && dbpassword.equals(password))
			  {
					  currentname = dbname;
					  currentuserid = dbuserid;   
					  currentusername = dbusername;
					  currentroleid = dbroleid;
					  currentrolename = dbrolename;
			    	  
					  check = "qatrue";
			    	  break;
				  
			  }
			  else if(dbroleid == 0 && isverified == 0 && dbusername.equals(name) && dbpassword.equals(password))
			  {
			    	  System.out.println("not verified");
					  check = "notverified";
			    	  break;
				  
			  }
			  else if(dbroleid == 0 && isverified == 1 && dbusername.equals(name) && dbpassword.equals(password))
			  {
				  System.out.println("not given role");
				  check = "noroleemp";
		    	  break;
		    	  
			  }
			  else
			  {
				  check = "invalid";
			  }
		  }
	  
		  SessionBean sessionbean = new SessionBean();
		  sessionbean.setUserId(currentuserid);
		  sessionbean.setName(currentname);
		  sessionbean.setUserName(currentusername);
		  sessionbean.setRoleId(currentroleid);
		  sessionbean.setRoleName(currentrolename);
		  
		  session.setAttribute("sessionBean", sessionbean);
		  
		  if(check.equals("satrue")){
              String message1 = "Admin Hello";
			  return new ModelAndView("SuperAdmin", "message", message1);
		  }
		  else if(check.equals("pmtrue")){
              String message1 = "PM Hello";
			  return new ModelAndView("ProjectManager", "message", message1);
		  }
		  else if(check.equals("qatrue")){
              String message1 = "QA Hello";
			  return new ModelAndView("QA", "message", message1);
		  }
		  else if(check.equals("tltrue"))
		  {
			  String message2 = "TL Hello";
			  return new ModelAndView("TeamLeader", "message", message2);
		  }
		  else if(check.equals("emptrue"))
		  {
			  String message2 = "EMP Hello";
			  return new ModelAndView("Employee", "message", message2);
		  }
		  else if(check.equals("notverified")){
			  String message2 = "Sorry, You are not approved";
			  return new ModelAndView("notverified", "message", message2);
		  }
		  else if(check.equals("noroleemp")){
			  String message2 = "Sorry, You have approved but not given any Role";
			  return new ModelAndView("noroleemp", "message", message2);
		  }
		  else if(check.equals("invalid")){
			  String message2 = "invalid credentials";
			  return new ModelAndView("index", "invalidmessage", message2);
		  }
		  else{
			  return null;
		  }
	      
		  
	 }
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public ModelAndView logoutit(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		session.removeAttribute("sessionBean");
		request.getSession().invalidate();
		return new ModelAndView("index");
		
	}
 
}
