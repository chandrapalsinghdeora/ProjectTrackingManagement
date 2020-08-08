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
import com.precise.service.TeamLeaderViewProgressService;


@Controller
public class TeamLeaderViewProgressController {

	@Autowired
	TeamLeaderViewProgressService tlprogressservice;
	
	@RequestMapping(value="/getexcels", method = RequestMethod.GET)
	public ModelAndView getdifferentexcels(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
        int userId=sessionBean.getUserId();
		List<Project> teamlist2 = tlprogressservice.getAssignedProject(userId);
		request.setAttribute("teamlist2", teamlist2);
		List<Employee> teamlist1 = tlprogressservice.getAssignedUsers(userId);
		request.setAttribute("teamlist1", teamlist1);
		return new ModelAndView("TeamLeaderViewProgressExcel");
	}	
	@RequestMapping(value="/generate", method = RequestMethod.POST)
	public String generateexcel(HttpServletRequest request,HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("ddproject"));
		 tlprogressservice.getexcel(id);
		return "redirect:getexcels";
	}
	@RequestMapping(value="/getAllModule")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
	        int userId=sessionBean.getUserId();
			PrintWriter pw=response.getWriter();
			pw.print(tlprogressservice.getmodule(Integer.parseInt(request.getParameter("projectName")),userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
        
	}
	//to get all the module in modulewise excel
	@RequestMapping(value="/getAlltheModule")
	public void getallthemodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
	        int userId=sessionBean.getUserId();
			PrintWriter pw=response.getWriter();
			pw.print(tlprogressservice.getthemodule(Integer.parseInt(request.getParameter("projectName")),userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
        
	}	
	@RequestMapping(value="/moduleexcel", method = RequestMethod.POST)
	public String generatetheexcel(HttpServletRequest request,HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("moduleId"));
		 tlprogressservice.getmoduleexcel(id);
		 return "redirect:getexcels";
		
	}
	@RequestMapping(value="/userexcel", method = RequestMethod.POST)
	public String generateuserexcel(HttpServletRequest request,HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("userId"));
		 tlprogressservice.getuserexcel(id);
		 return "redirect:getexcels";
		
	}
	@RequestMapping(value="/getAllthesubModule")
	public void getthesubmodules(HttpServletRequest request,HttpServletResponse response){
		try{
			int id = Integer.parseInt(request.getParameter("moduleId"));
			PrintWriter pw=response.getWriter();
			pw.print(tlprogressservice.getthesubmodules(id));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	


}
	@RequestMapping(value="/submoduleexcel", method = RequestMethod.POST)
	public String generatesubmoduleexcel(HttpServletRequest request,HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("subModuleId"));
		 tlprogressservice.getsubmoduleexcel(id);
		 return "redirect:getexcels";
		
	}
	
	@RequestMapping(value="/gettlgraph", method = RequestMethod.GET)
	public ModelAndView gettlgraph(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
        int userId=sessionBean.getUserId();
		List<Project> teamlist2 = tlprogressservice.getAssignedProject(userId);
		request.setAttribute("teamlist2", teamlist2);
		
		return new ModelAndView("TeamLeaderViewProgressGraph");
	}	

}
