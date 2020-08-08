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
import com.precise.service.PMAssignedProjectsService;

@Controller
public class PMAssignedProjectsController {

	@Autowired
	PMAssignedProjectsService pmassignproject;
	
	@RequestMapping(value="/pmassigedproject", method = RequestMethod.GET)
	public ModelAndView getallprojects(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int userId=sessionBean.getUserId();
		List<Project> teamlist1 = pmassignproject.getAllProject(userId);
		request.setAttribute("teamlist1", teamlist1);
		
		return new ModelAndView("ProjectManagerAssignedProject"); 
        
	}	
	
	@RequestMapping(value="/allassignedprojectstopm",method = RequestMethod.GET)
	 public void getallModules(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("projectId"));
		
		List<Project> allmodule = pmassignproject.getAssignedProjects(id);

		String projectname[] = new String[allmodule.size()];
		String projectdescription[] = new String[allmodule.size()];
		Date startdate[] = new Date[allmodule.size()];
		Date enddate[] = new Date[allmodule.size()];
		
		for (int i = 0; i < allmodule.size(); i++) {
			projectname[i] = allmodule.get(i).getProjectName();
			projectdescription[i] = allmodule.get(i).getProjectDescription();
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
	      out.print("<table class="+"myTable"+">");  
	      out.print("<tr><th>ProjectName</th><th>ProjectDescription</th><th>StartDate</th><th>EndDate</th></tr>");  
	       for(int i=0;i<allmodule.size();i++)
	       {
	       out.print("<tr><td>"+projectname[i]+"</td><td>"+projectdescription[i]+"</td><td>"+startdate[i]+"</td><td>"+enddate[i]+"</td></tr>");  
	       }  
	       out.print("</table>");  
	       }
	}

	
}
