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

import com.precise.model.Employee;
import com.precise.model.Project;
import com.precise.model.SessionBean;
import com.precise.service.TeamLeaderAssignModuleService;

@Controller
public class TeamLeaderAssignModuleController {

	@Autowired
	TeamLeaderAssignModuleService tlassignmoduleservice;
	String empDesignation="";
	String csd="";
	
	//for assign submodules tab of tl
	@RequestMapping(value="/tlassignmodule", method = RequestMethod.GET)
	public ModelAndView assignit(HttpServletRequest request,HttpServletResponse response){
		
		
		SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
        int userId=sessionBean.getUserId();
		
    // for getting all assigned project names
		List<Project> teamlist2 = tlassignmoduleservice.getAssignedProjects(userId);
		request.setAttribute("teamlist2", teamlist2);
		for(Project pp: teamlist2){
			empDesignation = pp.getEmpDesignation();
            break;
		}
		
		
	// for getting all employees names of the same designation
		List<Employee> employees = tlassignmoduleservice.getallemployees(empDesignation,userId);
		request.setAttribute("employees", employees);
		
		return new ModelAndView("TeamLeaderAssignModule"); 
        
	}
	//for assign task
	@RequestMapping(value="/tlassigntask", method = RequestMethod.GET)
	public ModelAndView assigntask(HttpServletRequest request,HttpServletResponse response){
		
		String empDesignation="";
		
		SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
        int userId=sessionBean.getUserId();
		
    // for getting all assigned project names
		List<Project> teamlist2 = tlassignmoduleservice.getAssignedProjects(userId);
		//System.out.println("csdhelo");
		request.setAttribute("teamlist2", teamlist2);
		
		for(Project pp: teamlist2){
			csd = pp.getEmpDesignation();
            break;
		}
		
		System.out.println("empDesignation:"+empDesignation);
		
	// for getting all employees names of the same designation
		List<Employee> employees = tlassignmoduleservice.getallemployees(empDesignation,userId);
		request.setAttribute("employees", employees);
		
		return new ModelAndView("TeamLeaderAssignTask"); 
        
	}
	
	
	//get all modules in module assigned of team leader
	@RequestMapping(value="/getAllModules")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
			PrintWriter pw=response.getWriter();
			pw.print(tlassignmoduleservice.getmodules(request.getParameter("projectName"),userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
        
	}	
	//get all modules in assign submodule of team leader
		@RequestMapping(value="/getAllTHEModules")
		public void getallthemodules(HttpServletRequest request,HttpServletResponse response){
			try{
				SessionBean sessionBean = (SessionBean) request.getSession()
						.getAttribute("sessionBean");
		        	
				int userId=sessionBean.getUserId();
				PrintWriter pw=response.getWriter();
				pw.print(tlassignmoduleservice.getassignmodules(request.getParameter("projectName"),userId));
				pw.flush();
				pw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	        
		}	
		
	//add entry to tbl_trns_assign_to_emp
	@RequestMapping(value="/assigntoemp", method= RequestMethod.POST)
	public String assignittoemployees(Project pro, HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
        int userId=sessionBean.getUserId();
		tlassignmoduleservice.assignittoemployees(pro,userId);
        return "redirect:tlassignmodule";
	}
	//add entry to tbl_trns_assign_task_to_emp
		@RequestMapping(value="/assigntasktoemp", method= RequestMethod.POST)
		public String assigntasktoemployees(Project pro, HttpServletRequest request,HttpServletResponse response){
			SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
	        int userId=sessionBean.getUserId();
			tlassignmoduleservice.assigntasktoemployees(pro,userId);
	        return "redirect:tlassigntask";
		}
		
		
	//to get project assigned to tl in assigned module tab of tl
	@RequestMapping(value="/assignedtlmodule", method = RequestMethod.GET)
	public ModelAndView getallmodules(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int userId=sessionBean.getUserId();
		List<Project> teamlist1 = tlassignmoduleservice.getAssignedModules(userId);
		request.setAttribute("teamlist1", teamlist1);

		for(Project pp: teamlist1){
			empDesignation = pp.getEmpDesignation();
            break;
		}
		
		return new ModelAndView("TLAssignedModule"); 
        
	}
	//to get information about assigned modules of team leader
	@RequestMapping(value="/alltlassignedmodules",method = RequestMethod.GET)
	 public void getallModules(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("moduleId"));
		
		List<Project> allmodule = tlassignmoduleservice.getAssignedModules(id);

		
			 String modulename = allmodule.get(0).getModuleNameIndividually();
			 String moduledescription = allmodule.get(0).getModuleDescriptionIndividually();
	         Date startdate = allmodule.get(0).getStartDate();
	         Date enddate = allmodule.get(0).getEndDate();
		
		
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
	       out.print("<tr><th>ModuleName</th><th>ModuleDescription</th><th>StartDate</th><th>EndDate</th></tr>");  
	       for(int i=0;i<allmodule.size();i++)
	       {
	       out.print("<tr><td>"+modulename+"</td><td>"+moduledescription+"</td><td>"+startdate+"</td><td>"+enddate+"</td></tr>");  
	       }  
	       out.print("</table>");  
	       }
	}
	//to get all the submodules in assign task
	@RequestMapping(value="/GetallTheSubModules")
	public void getthesubmodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
			int id = Integer.parseInt(request.getParameter("moduleId"));
			PrintWriter pw=response.getWriter();
			pw.print(tlassignmoduleservice.getsubmodules(id,userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	


}
	@RequestMapping(value="/GetAllTheSUBModules")
	public void getallthesubmodules(HttpServletRequest request,HttpServletResponse response){
		try{
			SessionBean sessionBean = (SessionBean) request.getSession()
					.getAttribute("sessionBean");
	        	
			int userId=sessionBean.getUserId();
			int id = Integer.parseInt(request.getParameter("moduleId"));
			PrintWriter pw=response.getWriter();
			pw.print(tlassignmoduleservice.getthesubmodules(id,userId));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	


}	
	//to get all the tasks in assign submodule
		@RequestMapping(value="/Getalltask")
		public void getthetasks(HttpServletRequest request,HttpServletResponse response){
			try{
				SessionBean sessionBean = (SessionBean) request.getSession()
						.getAttribute("sessionBean");
		        	
				int userId=sessionBean.getUserId();
				int id = Integer.parseInt(request.getParameter("subModuleId"));
				PrintWriter pw=response.getWriter();
				pw.print(tlassignmoduleservice.gettasks(id,userId));
				pw.flush();
				pw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		


	}
		//to get all the employees in assign submodule
				@RequestMapping(value="/Getallemployee")
				public void gettheemployees(HttpServletRequest request,HttpServletResponse response){
					try{
						SessionBean sessionBean = (SessionBean) request.getSession()
								.getAttribute("sessionBean");
				        	
						int userId=sessionBean.getUserId();
						int id = Integer.parseInt(request.getParameter("taskId"));
						PrintWriter pw=response.getWriter();
						//System.out.println("heyyyaaaaa::"+tlassignmoduleservice.getemployees(id,userId));
						if(tlassignmoduleservice.getemployees(id,userId).length()!=0)
						pw.print(tlassignmoduleservice.getemployees(id,userId));
						else
						{
							pw.print(tlassignmoduleservice.getalltheemployees(csd,userId));
						}
						pw.flush();
						pw.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				


			}

	
	}
	
