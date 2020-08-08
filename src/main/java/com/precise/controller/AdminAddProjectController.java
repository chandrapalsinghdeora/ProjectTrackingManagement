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
import com.precise.service.AdminAddProjectService;
import com.precise.service.SuperAdminAddProjectService;

@Controller
public class AdminAddProjectController {

	@Autowired
	AdminAddProjectService adminaddproject;
	
	
	@Autowired
	SuperAdminAddProjectService superadminaddproject;
	
	@RequestMapping(value="/pmhome", method = RequestMethod.GET)
	public ModelAndView pmHome(HttpServletRequest request,HttpServletResponse response){
        return new ModelAndView("ProjectManager");
	}	
	
	@RequestMapping(value="/pmaddmodules", method = RequestMethod.GET)
	public ModelAndView getallprojects(Project pro,HttpServletRequest request,HttpServletResponse response){
	    SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userId = sessionBean.getUserId();
	    List<Project> projectlist = adminaddproject.getallprojects(pro, userId);
	    request.setAttribute("projectlist", projectlist);
        return new ModelAndView("PMAddModule");
	}	
	
	@RequestMapping(value="/pmaddsubmodules", method = RequestMethod.GET)
	public ModelAndView saaddsubmodules(Project pro,HttpServletRequest request,HttpServletResponse response){
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userId = sessionBean.getUserId();
        List<Project> projects= adminaddproject.getallprojects(pro, userId);
	    request.setAttribute("projects", projects);
        return new ModelAndView("PMAddSubmodule");
	}
	
	@RequestMapping(value={"/pmsavesubmodules"},method={RequestMethod.POST})
	public String savesubModules(Project pro,HttpServletRequest request){
		try {
            superadminaddproject.savesubmoduledetails(pro);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return "redirect:pmaddsubmodules";
	}
	
	@RequestMapping(value={"/savemodules"},method={RequestMethod.POST})
	public String saveModules(Project pro,HttpServletRequest request){
		try {
		     adminaddproject.savemoduledetails(pro);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "redirect:pmaddmodules";
	}
	
}
