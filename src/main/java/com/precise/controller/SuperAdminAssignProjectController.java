package com.precise.controller;

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
import com.precise.service.SuperAdminAssignProjectService;

@Controller
public class SuperAdminAssignProjectController {

	@Autowired
	SuperAdminAssignProjectService superadminassignproject;
	
	@RequestMapping(value="/saassignproject", method = RequestMethod.GET)
	public ModelAndView getallprojects(HttpServletRequest request,HttpServletResponse response){
		
		List<Project> projectlist = superadminassignproject.getAllProject();
		request.setAttribute("projectlist", projectlist);
		
		List<Employee> pmlist = superadminassignproject.getallpm();
		request.setAttribute("pmlist", pmlist);
		
		return new ModelAndView("SAAssignProject"); 
        
	}	
	

	@RequestMapping(value="/assignprojecttopm", method = RequestMethod.POST)
	public String assignittotl(Project pro, HttpServletRequest request,HttpServletResponse response){
        superadminassignproject.assignthemodule(pro);
        
        return "redirect:saassignproject";
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

