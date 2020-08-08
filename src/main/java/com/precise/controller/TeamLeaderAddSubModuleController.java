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

import com.precise.model.Project;
import com.precise.model.SessionBean;
import com.precise.service.AdminAddProjectService;
import com.precise.service.SuperAdminAddProjectService;
import com.precise.service.TeamLeaderAddSubModuleService;


@Controller
public class TeamLeaderAddSubModuleController { 
	
	@Autowired
	TeamLeaderAddSubModuleService teamleaderaddsubmoduleservice;
	@Autowired
	AdminAddProjectService adminaddproject;
	@Autowired
	SuperAdminAddProjectService superadminaddproject;
	
	
	@RequestMapping(value="/tladdsubmodule", method = RequestMethod.GET)
	public ModelAndView getallprojects(Project pro,HttpServletRequest request,HttpServletResponse response){
	    SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userid = sessionBean.getUserId();
	    List<Project> projectlist1 = teamleaderaddsubmoduleservice.getalltlprojects(userid);
	    request.setAttribute("projectlist", projectlist1);
	    List<Project> projectlist = teamleaderaddsubmoduleservice.getallprojects(userid);
	    request.setAttribute("submodulelist", projectlist);
        return new ModelAndView("TeamLeaderAddSubmodules");
	}	
	@RequestMapping(value="/tldeletesubmodule1", method = RequestMethod.POST)
	public String deletesubModule(Project pro,HttpServletRequest request){
	    adminaddproject.deletesubmodule(Integer.parseInt(request.getParameter("deleteSubModuleId")));
		return "redirect:tladdsubmodule";
	}
	@RequestMapping(value={"/tlsavesubmodules1"},method={RequestMethod.POST})
	public String savesubModules(Project pro,HttpServletRequest request){
		try {
            superadminaddproject.savesubmoduledetails(pro);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return "redirect:tladdsubmodule";
	}
	@RequestMapping(value="/tlupdateindividualsubmodule1", method = RequestMethod.POST)
	public String updateSubModule(Project pro,HttpServletRequest request){
	    adminaddproject.updateindividualsubmodule(pro);
		return "redirect:tladdsubmodule";
	}
	
	
	
	@RequestMapping(value="/assignedmoduletotl")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userid = sessionBean.getUserId();
		try{
			
			PrintWriter pw=response.getWriter();
			pw.print(teamleaderaddsubmoduleservice.getmodules(Integer.parseInt(request.getParameter("projectId")), userid));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
