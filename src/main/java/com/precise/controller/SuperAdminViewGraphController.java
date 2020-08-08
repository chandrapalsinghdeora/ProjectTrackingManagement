package com.precise.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Project;
import com.precise.model.SessionBean;
import com.precise.service.SuperAdminViewGraphService;
import com.precise.service.SuperAdminViewProgressExcelService;

@Controller
public class SuperAdminViewGraphController {

	@Autowired
	SuperAdminViewGraphService superadminviewgraph;
	
	@Autowired
	SuperAdminViewProgressExcelService superadminprogressservice;
	
	@RequestMapping(value="/superadmingetgraph",method = RequestMethod.GET)
	public ModelAndView getemail(HttpServletRequest request,HttpServletResponse response){
		 SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
	        
	        List<Project> allprojects = superadminprogressservice.getAllProject(userId);
	        request.setAttribute("projects", allprojects);
		return new ModelAndView("SuperAdminViewProgressGraph");
	}
	
	
	@RequestMapping(value="/superadmingraphprojectwise", method= RequestMethod.POST)
	public ModelAndView getthegraphofproject(Project pro, HttpServletRequest request, HttpServletResponse response)
	{
		List<Project> listRole = superadminviewgraph.getthegraphofprojects(pro);
		request.setAttribute("listrole", listRole);
		return new ModelAndView("AdminProjectGraph");
	}
	
	
	@RequestMapping(value="/superadmingraphmodulewise", method= RequestMethod.POST)
	public ModelAndView getthegraphofsubmodules(Project pro, HttpServletRequest request, HttpServletResponse response)
	{
		List<Project> listRole = superadminviewgraph.getthegraphofsubmodules(pro);
		request.setAttribute("listrole", listRole);
		return new ModelAndView("AdminGraph");
	}
	
}
