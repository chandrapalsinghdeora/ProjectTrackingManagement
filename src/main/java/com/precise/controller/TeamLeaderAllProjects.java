// all assigned projects to the team leader

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

import com.precise.dao.TeamLeaderAllProjectsDao;
import com.precise.model.Project;
import com.precise.model.SessionBean;


@Controller
public class TeamLeaderAllProjects {

	@Autowired
	TeamLeaderAllProjectsDao projectsservice;
	
	@RequestMapping(value="/viewprojects",method = RequestMethod.GET)
	 public ModelAndView getallassignedprojects(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int userId=sessionBean.getUserId();
		List<Project> assignedproject = projectsservice.getallassignedprojects(userId);
		request.setAttribute("assignedprojects", assignedproject);
		return new ModelAndView("TLAssignedProject");
	}
	
	@RequestMapping(value="/getmodules",method = RequestMethod.GET)
	 public void getallModules(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		
		PrintWriter out = response.getWriter();
		
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		
		List<Project> allmodule = projectsservice.getmodules(projectId);

		int moduleid[] = new int[allmodule.size()];
		String modulename[] = new String[allmodule.size()];
		String moduledescription[] = new String[allmodule.size()];
		String projectname[] = new String[allmodule.size()];
		Date startdate[] = new Date[allmodule.size()];
		Date enddate[] = new Date[allmodule.size()];
		
		for (int i = 0; i < allmodule.size(); i++) {
			moduleid[i] = allmodule.get(i).getModuleId();
			modulename[i] = allmodule.get(i).getModuleNameIndividually();
			moduledescription[i] = allmodule.get(i).getModuleDescriptionIndividually();
            projectname[i] = allmodule.get(i).getProjectName();
            startdate[i] = allmodule.get(i).getStartDate();
            enddate[i] = allmodule.get(i).getEndDate();
		}
		
	    if(allmodule.size()==0) {      
	        out.println("<p>No Record Found!</p>");   
	       }
	    else{  
	       out.print("<style>"+
".myTable { "+
"  width: 100%;"+
"  text-align: left;"+
"  background-color: lemonchiffon;"+
 " border-collapse: collapse; "+
 " }"+
 "h1 {color: red;}"+
 "h3 {color: red;}"+
".myTable th {"+ 
"  background-color: goldenrod;"+
 " color: white; "+
 " }"+
".myTable td,"+ 
".myTable th { "+
"  padding: 10px;"+
 " border: 1px solid goldenrod;"+ 
 " }"+
"</style>");
	       out.print("<hr>");
	       out.print("<h1>"+projectname[0]+"</h1>");
	       out.print("<table class="+"myTable"+">");  
	       out.print("<tr><th>ModuleName</th><th>ModuleDescription</th></tr>");  
	       for(int i=0;i<allmodule.size();i++)
	       {
	       out.print("<tr><td>"+modulename[i]+"</td><td>"+moduledescription[i]+"</td></tr>");  
	       }  
	       out.print("</table>");  
	       out.print("<h3>"+"StartDate:"+startdate[0]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"EndDate:"+enddate[0]+"</h3>");
	       }
	}
	
}
