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
import com.precise.service.TeamLeaderAddTaskService;

@Controller
public class TeamLeaderAddTaskController {
	
	@Autowired
	TeamLeaderAddTaskService teamleaderaddtask;
	
	public int subModuleId;
	
	@RequestMapping(value="/tlhome", method = RequestMethod.GET)
	public ModelAndView tlHome(Project pro,HttpServletRequest request,HttpServletResponse response){
        return new ModelAndView("TeamLeader");
	}	
	
	@RequestMapping(value="/tladdtask", method = RequestMethod.GET)
	public ModelAndView getalltheprojects(Project pro,HttpServletRequest request,HttpServletResponse response){
	    SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userid = sessionBean.getUserId();
	    List<Project> projectlist1 = teamleaderaddtask.getallprojects(userid);
	    request.setAttribute("projectlist", projectlist1);
	    List<Project> projectlist = teamleaderaddtask.getallthetasks(userid);
	    request.setAttribute("tasklist", projectlist);
        return new ModelAndView("TeamLeaderDefineTask");
	}	
	@RequestMapping(value="/tlupdatetask", method = RequestMethod.POST)
	public String updateSubModule(Project pro,HttpServletRequest request){
		teamleaderaddtask.updateindividualtask(pro);
		return "redirect:tladdtask";
	}
	@RequestMapping(value="/deletetask", method = RequestMethod.POST)
	public String deletesubModule(Project pro,HttpServletRequest request){
		teamleaderaddtask.deletetask(Integer.parseInt(request.getParameter("deleteTaskId")));
		return "redirect:tladdtask";
	}
	
	
	
	// will get all the models automatically from the teamleaderaddsubmodulecontroller class
    // because jsp page already has that link
	
	// for submodules
	@RequestMapping(value="/submodofthismodule")
	public void getthesubmodules(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userid = sessionBean.getUserId();
		try{
			
			PrintWriter pw=response.getWriter();
			pw.print(teamleaderaddtask.getsubmodules(Integer.parseInt(request.getParameter("moduleId")), userid));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/savetasks", method = RequestMethod.POST)
	public String savetasks(Project pro,HttpServletRequest request,HttpServletResponse response){
	    teamleaderaddtask.savetasks(pro);
        return "redirect:tladdtask";
	}	
	
	
	
	
	@RequestMapping(value="/taskdependency", method = RequestMethod.GET)
	public ModelAndView tasks(Project pro,HttpServletRequest request,HttpServletResponse response){
	    SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userid = sessionBean.getUserId();
	    List<Project> projectlist = teamleaderaddtask.getallprojects(userid);
	    request.setAttribute("projectlist", projectlist);
	    
        return new ModelAndView("TaskDependency");
	}
	
	
	// for task dependency purpose
	@RequestMapping(value="/tasksofthissubmodule")
	public void getthesubmodtasks(HttpServletRequest request,HttpServletResponse response){
		try{
			
			PrintWriter pw=response.getWriter();
			subModuleId = Integer.parseInt(request.getParameter("submoduleId"));
			pw.print(teamleaderaddtask.gettasks(Integer.parseInt(request.getParameter("submoduleId"))));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/othertasksofthissubmodule")
	public void getthesubmodothertasks(HttpServletRequest request,HttpServletResponse response){
		try{
			
			PrintWriter pw=response.getWriter();
			pw.print(teamleaderaddtask.getothertasks(Integer.parseInt(request.getParameter("taskId")),subModuleId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/assigntaskdependency", method = RequestMethod.POST)
	public String taskdependency(Project pro,HttpServletRequest request,HttpServletResponse response){
	    teamleaderaddtask.dependency(pro);
        return "redirect:taskdependency";
	}
	
	
}
