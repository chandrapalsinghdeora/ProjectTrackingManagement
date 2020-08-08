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
import com.precise.service.EmployeeUpdateStatusService;

@Controller
public class EmployeeUpdateStatusController {

	@Autowired
	EmployeeUpdateStatusService employeeupdatestatusservice;
	
	@RequestMapping(value="/updatestatus", method = RequestMethod.GET)
	public ModelAndView getallmodules(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int userId=sessionBean.getUserId();
		List<Project> teamlist1 = employeeupdatestatusservice.getAllProjects(userId);
		request.setAttribute("teamlist1", teamlist1);
		return new ModelAndView("EmployeeUpdateStatus"); 
        
	}
	
	@RequestMapping(value="/GetallModules")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
			PrintWriter pw=response.getWriter();
			pw.print(employeeupdatestatusservice.getmodules(request.getParameter("projectName"),userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/GetallTasks")
	public void getthetasks(HttpServletRequest request,HttpServletResponse response){
		System.out.println("insie gettheteamleaders method");
		try{
			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
			int id = Integer.parseInt(request.getParameter("submoduleId"));
			System.out.println("request values::"+request.getParameter("submoduleId"));
			PrintWriter pw=response.getWriter();
			pw.print(employeeupdatestatusservice.gettasks(id,userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

		@RequestMapping(value="/GetallSubModules")
		public void getthesubmodules(HttpServletRequest request,HttpServletResponse response){
			try{
				SessionBean sessionBean = (SessionBean) request.getSession()
						.getAttribute("sessionBean");
		        	
				int userId=sessionBean.getUserId();
				int id = Integer.parseInt(request.getParameter("moduleId"));
				PrintWriter pw=response.getWriter();
				pw.print(employeeupdatestatusservice.getsubmodules(id,userId));
				pw.flush();
				pw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
	
	
	}

		@RequestMapping(value="/addentrytotable", method = RequestMethod.POST)
		public String assignittotl(Project pro, HttpServletRequest request,HttpServletResponse response){
			SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
        	int userId = sessionBean.getUserId();
			employeeupdatestatusservice.add(pro, userId);
	        return "redirect:updatestatus";
		}
		
        @RequestMapping(value="/excel", method = RequestMethod.GET)
		public void generate(HttpServletRequest request,HttpServletResponse response){
        	SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
        	int userId = sessionBean.getUserId();
			employeeupdatestatusservice.generateExcel(userId);
		}
}
