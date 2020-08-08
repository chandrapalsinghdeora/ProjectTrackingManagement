package com.precise.controller;

import java.io.PrintWriter;
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
import com.precise.service.AdminViewProgressService;


@Controller
public class AdminViewProgressController {

	@Autowired
	AdminViewProgressService adminprogressservice;
	
	@RequestMapping(value="/getdifferentexcels", method = RequestMethod.GET)
	public ModelAndView getdifferentexcels(HttpServletRequest request,HttpServletResponse response){
        SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int userId=sessionBean.getUserId();
        
        List<Project> allprojects = adminprogressservice.getAllProject(userId);
        request.setAttribute("projects", allprojects);
		
        
        List<Employee> allusers = adminprogressservice.getallusers();
        request.setAttribute("allusers", allusers);
        
        return new ModelAndView("ProjectManagerViewProgressExcel");
	}	
	
	
	
	
	// to generate excel project wise
	@RequestMapping(value="/projectwise", method = RequestMethod.POST)
	public void projectwiseexcel(HttpServletRequest request,HttpServletResponse response){
        int projectid = Integer.parseInt(request.getParameter("ddproject"));
        adminprogressservice.createpwexcel(projectid);
	}
	
	
	// to get all modules of a particular selected project by admin
	@RequestMapping(value="/getallmodulesofthispro")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		try{
	        	
			PrintWriter pw=response.getWriter();
			pw.print(adminprogressservice.getmodules(Integer.parseInt(request.getParameter("projectId"))));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	
	// to get all submodules of a particular selected module by admin
	@RequestMapping(value="/getallsubmodulesofthismodule")
	public void getthesubmodules(HttpServletRequest request,HttpServletResponse response){
		try{
	        	
			PrintWriter pw=response.getWriter();
			pw.print(adminprogressservice.getsubmodules(Integer.parseInt(request.getParameter("moduleId"))));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	// to generate excel module wise
	@RequestMapping(value="/modulewise", method = RequestMethod.POST)
	public void modulewiseexcel(HttpServletRequest request,HttpServletResponse response){
        int projectid = Integer.parseInt(request.getParameter("ddmodule1"));
        int moduleid = Integer.parseInt(request.getParameter("ddmodule2"));
        adminprogressservice.createmwexcel(projectid, moduleid);
	}
	
	
	// to generate excel user wise
	@RequestMapping(value="/userwise", method = RequestMethod.POST)
	public void userwiseexcel(HttpServletRequest request,HttpServletResponse response){
        int userid = Integer.parseInt(request.getParameter("dduser"));
        adminprogressservice.createuwexcel(userid);
	}
	
	
	// to generate sub module wise
	@RequestMapping(value="/submodulewise", method = RequestMethod.POST)
	public void submodulewiseexcel(HttpServletRequest request,HttpServletResponse response){
		int projectid = Integer.parseInt(request.getParameter("ddsubmodule1"));
		int moduleid = Integer.parseInt(request.getParameter("ddsubmodule2"));
		int submoduleid = Integer.parseInt(request.getParameter("ddsubmodule3"));
		adminprogressservice.createsmwexcel(projectid, moduleid, submoduleid);
	}
	//to view graph
	@RequestMapping(value="/getpmgraph", method = RequestMethod.GET)
	public ModelAndView getpmgraph(HttpServletRequest request,HttpServletResponse response){
        SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int userId=sessionBean.getUserId();
        
        List<Project> allprojects = adminprogressservice.getAllProject(userId);
        request.setAttribute("projects", allprojects);
        return new ModelAndView("ProjectManagerViewProgressGraph");
	}	
	
	
}
