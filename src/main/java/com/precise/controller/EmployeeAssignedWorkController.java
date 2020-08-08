package com.precise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Project;
import com.precise.model.SessionBean;
import com.precise.service.EmployeeAssignedModuleService;

@Controller
public class EmployeeAssignedWorkController {

	@Autowired
	EmployeeAssignedModuleService employeeassignedmodule;
	
	@RequestMapping(value="/empassignedsubmod", method=RequestMethod.GET)
	public ModelAndView getallempprojects(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");

		int userId=sessionBean.getUserId();
		List<Project> listRole = employeeassignedmodule.getAllProjects(userId);
		request.setAttribute("projectlist", listRole);
		return new ModelAndView("EmployeeAssignedSubModule"); 
        
	}
	
	@RequestMapping(value="/empassignedtask", method=RequestMethod.GET)
	public ModelAndView getalltasks(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");

		int userId=sessionBean.getUserId();
		List<Project> listRole = employeeassignedmodule.getAllTProjects(userId);
		request.setAttribute("projectlist", listRole);
		return new ModelAndView("EmployeeAssignedTask"); 
        
	}
	
	@RequestMapping(value="/allassignedsubmodules",method = RequestMethod.GET)
	 public void getallsubModules(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		PrintWriter out = response.getWriter();
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");

		int userId=sessionBean.getUserId();
		int id = Integer.parseInt(request.getParameter("moduleId"));
		
		List<Project> allmodule = employeeassignedmodule.getAssignedSubModules(id,userId);

		String submodulename[] = new String[allmodule.size()];
		String submoduledescription[] = new String[allmodule.size()];
		Date startdate[] = new Date[allmodule.size()];
		Date enddate[] = new Date[allmodule.size()];
		
		for (int i = 0; i < allmodule.size(); i++) {
			submodulename[i] = allmodule.get(i).getSubModuleNameIndividually();
			submoduledescription[i] = allmodule.get(i).getSubModuleDescriptionIndividually();
	        startdate[i] = allmodule.get(i).getStartDate();
	        enddate[i] = allmodule.get(i).getEndDate();
		}
		
		System.out.println("List Size is"+allmodule.size());
		
	    if(allmodule.size()==0) {      
	        out.println("<p>No Record Found!</p>");   
	       }
	    else{  
	       out.print("<style>"+
".myTable { "+
"  width: 100%;"+
"  text-align: left;"+
"  background-color: white;"+
" border-collapse: collapse; "+
" }"+
"h1 {color: blue;}"+
"h3 {color: blue;}"+
".myTable th {"+ 
"  background-color: blue;"+
" color: white; "+
" }"+
".myTable td,"+ 
".myTable th { "+
"  padding: 10px;"+
" border: 1px solid blue;"+ 
" }"+
"</style>");
	       out.print("<hr>");
	       out.print("<h3>"+"StartDate:"+startdate[0]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"EndDate:"+enddate[0]+"</h3>");	       out.print("<table class="+"myTable"+">");  
	       out.print("<tr><th>SubModuleName</th><th>SubModuleDescription</th></tr>");  
	       for(int i=0;i<allmodule.size();i++)
	       {
	       out.print("<tr><td>"+submodulename[i]+"</td><td>"+submoduledescription[i]+"</td></tr>");  
	       }  
	       out.print("</table>");  
	       }
	}
	
	
	
	@RequestMapping(value="/GetAllModules")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
			PrintWriter pw=response.getWriter();
			pw.print(employeeassignedmodule.getmodules(Integer.parseInt(request.getParameter("projectId")),userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/GetAllTModules")
	public void getthetaskmodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
			PrintWriter pw=response.getWriter();
			pw.print(employeeassignedmodule.gettmodules(Integer.parseInt(request.getParameter("projectId")),userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/getallsubmodules")
	public void jsonsubmodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
			PrintWriter pw=response.getWriter();
			pw.print(employeeassignedmodule.getjsonsubmodules(Integer.parseInt(request.getParameter("moduleId")),userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/alltasks",method = RequestMethod.GET)
	 public void getalltasks(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		PrintWriter out = response.getWriter();
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");

		int userId=sessionBean.getUserId();
		int id = Integer.parseInt(request.getParameter("submoduleId"));
		
		List<Project> allmodule = employeeassignedmodule.getAssignedTasks(id,userId);

		String taskname[] = new String[allmodule.size()];
		String taskdescription[] = new String[allmodule.size()];
		Date startdate[] = new Date[allmodule.size()];
		Date enddate[] = new Date[allmodule.size()];
		
		for (int i = 0; i < allmodule.size(); i++) {
			taskname[i] = allmodule.get(i).getTaskNameIndividually();
			taskdescription[i] = allmodule.get(i).getTaskDescriptionIndividually();
	        startdate[i] = allmodule.get(i).getStartDate();
	        enddate[i] = allmodule.get(i).getEndDate();
		}
		
		System.out.println("List Size is"+allmodule.size());
		
	    if(allmodule.size()==0) {      
	        out.println("<p>No Record Found!</p>");   
	       }
	    else{  
	       out.print("<style>"+
".myTable { "+
"  width: 100%;"+
"  text-align: left;"+
"  background-color: white;"+
" border-collapse: collapse; "+
" }"+
"h1 {color: blue;}"+
"h3 {color: blue;}"+
".myTable th {"+ 
"  background-color: blue;"+
" color: white; "+
" }"+
".myTable td,"+ 
".myTable th { "+
"  padding: 10px;"+
" border: 1px solid blue;"+ 
" }"+
"</style>");
	       out.print("<hr>");
	       out.print("<h3>"+"StartDate:"+startdate[0]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"EndDate:"+enddate[0]+"</h3>");	       out.print("<table class="+"myTable"+">");  
	       out.print("<tr><th>TaskName</th><th>TaskDescription</th></tr>");  
	       for(int i=0;i<allmodule.size();i++)
	       {
	       out.print("<tr><td>"+taskname[i]+"</td><td>"+taskdescription[i]+"</td></tr>");  
	       }  
	       out.print("</table>");  
	       }
	}
}
