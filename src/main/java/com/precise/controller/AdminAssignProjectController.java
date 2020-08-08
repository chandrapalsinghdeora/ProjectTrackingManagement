package com.precise.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Employee;
import com.precise.model.Project;
import com.precise.model.SessionBean;
import com.precise.service.AdminAssignProjectService;

@Controller
public class AdminAssignProjectController {

	@Autowired
	AdminAssignProjectService adminassignproject;
	
	@RequestMapping(value="/assignmodule", method = RequestMethod.GET)
	public ModelAndView getallprojects(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int userId=sessionBean.getUserId();
		List<Employee> teamlist1 = adminassignproject.getAllDesignation();
		List<Project> teamlist2 = adminassignproject.getAllProject(userId);
		request.setAttribute("teamlist1", teamlist1);
		request.setAttribute("teamlist2", teamlist2);
		return new ModelAndView("AssignProject"); 
	}	
	@RequestMapping(value="/pmgetAllModules")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		try{
			PrintWriter pw=response.getWriter();
			int id = Integer.parseInt(request.getParameter("projectId"));
			pw.print(adminassignproject.getmodules(id));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
	@RequestMapping(value="/getTeamLeaderValues")
	public void gettheteamleaders(HttpServletRequest request,HttpServletResponse response){
		try{
			PrintWriter pw=response.getWriter();
			pw.print(adminassignproject.getteamleaders(request.getParameter("empDesignation")));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
        
	}	
	
	@RequestMapping(value="/assignthemodule1", method = RequestMethod.POST)
	public String assignittotl(Project pro, HttpServletRequest request,HttpServletResponse response){
		adminassignproject.assignthemodule(pro);
        return "redirect:assignmodule";
	}
	
	public java.sql.Date dateFormatter(Date date) {
		String dateForMySql = "";
		java.sql.Date sqlDate = null;
		try {
			if (date == null) {
				dateForMySql = null;
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				dateForMySql = simpleDateFormat.format(date);
				java.util.Date stringToDate = simpleDateFormat.parse(dateForMySql);
				sqlDate = new java.sql.Date(stringToDate.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	
}
