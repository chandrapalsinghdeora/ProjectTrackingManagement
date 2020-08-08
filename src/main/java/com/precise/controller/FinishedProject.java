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
import com.precise.service.FinishedProjectService;


@Controller
public class FinishedProject {
	@Autowired
	FinishedProjectService finishedprojectservice;
	
	@RequestMapping(value="/safinishedproject", method = RequestMethod.GET)
	public ModelAndView safinishedproject(HttpServletRequest request,HttpServletResponse response){
		 SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		    int userId = sessionBean.getUserId();
		List<Project> allprojects = finishedprojectservice.getAllFinishedProjects(userId);
        request.setAttribute("projects", allprojects);
		return new ModelAndView("SAFinishedProject"); 
	}
	@RequestMapping(value="/pmfinishedprojects", method = RequestMethod.GET)
	public ModelAndView pmfinishedproject(HttpServletRequest request,HttpServletResponse response){
		 SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		    int userId = sessionBean.getUserId();
		List<Project> allprojects = finishedprojectservice.getAllPMFinishedProjects(userId);
        request.setAttribute("projects", allprojects);
		return new ModelAndView("PMFinishedProject"); 
	}
}
