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

///  savemodule url already exist in adminaddprojectcontroller class.

@Controller
public class SuperAdminAddProjectController {

	@Autowired
	SuperAdminAddProjectService superadminaddproject;
	
	@Autowired
	AdminAddProjectService adminaddproject;
	
	@Autowired
	TeamLeaderAddSubModuleService teamleaderaddsubmoduleservice;
	
	@RequestMapping(value={"/superadminhome"},method={RequestMethod.GET})
	public ModelAndView superadminHome(HttpServletRequest request){
		return new ModelAndView("SuperAdmin");
	}
	
	@RequestMapping(value={"/emphome"},method={RequestMethod.GET})
	public ModelAndView employeeHome(HttpServletRequest request){
		return new ModelAndView("Emplpoyee");
	}
	
	@RequestMapping(value={"/qahome"},method={RequestMethod.GET})
	public ModelAndView qaHome(HttpServletRequest request){
		return new ModelAndView("QA");
	}
	@RequestMapping(value={"/sasavemodules"},method={RequestMethod.POST})
	public String saveModules(Project pro,HttpServletRequest request){
		try {
		     adminaddproject.savemoduledetails(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
			adminaddproject.updateindividualmodule(pro);
		return "redirect:saaddmodules";
	}
	@RequestMapping(value={"/pmsavemodules1"},method={RequestMethod.POST})
	public String pmsaveModules(Project pro,HttpServletRequest request){
		try {
		     adminaddproject.savemoduledetails(pro);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			adminaddproject.updateindividualmodule(pro);
		return "redirect:pmaddmodules1";
	}
	
	@RequestMapping(value="/updateindividualmodule", method = RequestMethod.POST)
	public String updateModule(Project pro,HttpServletRequest request){
	    adminaddproject.updateindividualmodule(pro);
		return "redirect:saaddmodules";
	}
	@RequestMapping(value="/pmupdateindividualmodule1", method = RequestMethod.POST)
	public String pmupdateModule(Project pro,HttpServletRequest request){
	    adminaddproject.updateindividualmodule(pro);
		return "redirect:pmaddmodules1";
	}
	
	@RequestMapping(value="/updateindividualsubmodule", method = RequestMethod.POST)
	public String updateSubModule(Project pro,HttpServletRequest request){
	    adminaddproject.updateindividualsubmodule(pro);
		return "redirect:saaddsubmodules";
	}
	@RequestMapping(value="/pmupdateindividualsubmodule1", method = RequestMethod.POST)
	public String pmupdateSubModule(Project pro,HttpServletRequest request){
	    adminaddproject.updateindividualsubmodule(pro);
		return "redirect:pmaddsubmodules1";
	}
	
	@RequestMapping(value="/deleteModule", method = RequestMethod.POST)
	public String deleteModule(Project pro,HttpServletRequest request){
	    adminaddproject.deletemodule(Integer.parseInt(request.getParameter("deleteModuleId")));
		return "redirect:saaddmodules";
	}
	@RequestMapping(value="/pmdeleteModule1", method = RequestMethod.POST)
	public String pmdeleteModule(Project pro,HttpServletRequest request){
	    adminaddproject.deletemodule(Integer.parseInt(request.getParameter("deleteModuleId")));
		return "redirect:pmaddmodules1";
	}
	
	@RequestMapping(value="/deletesubmodule", method = RequestMethod.POST)
	public String deletesubModule(Project pro,HttpServletRequest request){
	    adminaddproject.deletesubmodule(Integer.parseInt(request.getParameter("deleteSubModuleId")));
		return "redirect:saaddsubmodules";
	}
	@RequestMapping(value="/pmdeletesubmodule1", method = RequestMethod.POST)
	public String pmdeletesubModule(Project pro,HttpServletRequest request){
	    adminaddproject.deletesubmodule(Integer.parseInt(request.getParameter("deleteSubModuleId")));
		return "redirect:pmaddsubmodules1";
	}
	
	
	@RequestMapping(value="/saaddprojects", method = RequestMethod.GET)
	public ModelAndView addit(Project pro,HttpServletRequest request,HttpServletResponse response){
		List<Project> projects= superadminaddproject.getallprojects(pro);
	    request.setAttribute("projects", projects);
        return new ModelAndView("AddProject");
	}	
	
	@RequestMapping(value="/sagetallmodules")
	public void getmodulesofthisproject(HttpServletRequest request,HttpServletResponse response){
		try{
			PrintWriter pw=response.getWriter();
			pw.print(superadminaddproject.getmodules(Integer.parseInt(request.getParameter("projectId"))));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
        
	}	
	
	
	@RequestMapping(value="/saaddsubmodules", method = RequestMethod.GET)
	public ModelAndView saaddsubmodules(Project pro,HttpServletRequest request,HttpServletResponse response){
		List<Project> projectlist= superadminaddproject.getallprojects(pro);
	    request.setAttribute("projectlist", projectlist);
	    List<Project> allsubmodule = superadminaddproject.getallsubModules();
	    request.setAttribute("submodulelist", allsubmodule);
        return new ModelAndView("SAAddSubModule");
	}
	@RequestMapping(value="/pmaddsubmodules1", method = RequestMethod.GET)
	public ModelAndView pmaddsubmodules(Project pro,HttpServletRequest request,HttpServletResponse response){
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userId = sessionBean.getUserId();
		List<Project> projectlist= superadminaddproject.getallpmprojects(userId);
	    request.setAttribute("projectlist", projectlist);
	    List<Project> allsubmodule = superadminaddproject.getallpmsubModules(userId);
	    request.setAttribute("submodulelist", allsubmodule);
        return new ModelAndView("PMAddSubmodule");
	}
	
	@RequestMapping(value="/saaddprojectdetails", method = RequestMethod.POST)
	public String addthegivenproject(Project pro,HttpServletRequest request,HttpServletResponse response){
        if(Integer.parseInt(request.getParameter("projectId"))==0){
        	superadminaddproject.addtheinformation(pro);	
        }
        else
        {
        	superadminaddproject.edittheinformation(pro);
        }
	    return "redirect:saaddprojects";	
	}	
	
	@RequestMapping(value="/deleteProject", method = RequestMethod.POST)
	public String deletethegivenproject(Project pro,HttpServletRequest request,HttpServletResponse response){
        int projectid = Integer.parseInt(request.getParameter("deleteProjectId"));
        superadminaddproject.deletetheproject(projectid);
	    return "redirect:saaddprojects";	
	}
	
	@RequestMapping(value="/saaddmodules", method = RequestMethod.GET)
	public ModelAndView getallprojects(Project pro,HttpServletRequest request,HttpServletResponse response){
		List<Project> projectlist = superadminaddproject.getallprojects(pro);
	    request.setAttribute("projectlist", projectlist);
	    List<Project> allmodule = superadminaddproject.getallModules();
	    request.setAttribute("modulelist", allmodule);
        return new ModelAndView("SAAddModule");
	}	
	@RequestMapping(value="/pmaddmodules1", method = RequestMethod.GET)
	public ModelAndView getallpmprojects(Project pro,HttpServletRequest request,HttpServletResponse response){
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userId = sessionBean.getUserId();
		List<Project> projectlist = superadminaddproject.getallpmprojects(userId);
	    request.setAttribute("projectlist", projectlist);
	    List<Project> allmodule = superadminaddproject.getallpmModules(userId);
	    request.setAttribute("modulelist", allmodule);
        return new ModelAndView("PMAddModule");
	}	

	// savemodules link already exist in another controller
	
	@RequestMapping(value={"/sasavesubmodules"},method={RequestMethod.POST})
	public String savesubModules(Project pro,HttpServletRequest request){
		try {
            superadminaddproject.savesubmoduledetails(pro);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return "redirect:saaddsubmodules";
	}
	
	@RequestMapping(value={"/pmsavesubmodules1"},method={RequestMethod.POST})
	public String pmsavesubModules(Project pro,HttpServletRequest request){
		try {
            superadminaddproject.savesubmoduledetails(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:pmaddsubmodules1";
	}
	
}
