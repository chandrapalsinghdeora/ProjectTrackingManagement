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
import com.precise.service.SuperAdminViewProgressExcelService;


@Controller
public class SuperAdminViewProgressExcelController {

	@Autowired
	SuperAdminViewProgressExcelService superadminprogressservice;
	
	@RequestMapping(value="/getsuperadminexcels", method = RequestMethod.GET)
	public ModelAndView getdifferentexcels(HttpServletRequest request,HttpServletResponse response){
        SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int userId=sessionBean.getUserId();
        List<Project> allprojects = superadminprogressservice.getAllProject(userId);
        request.setAttribute("projects", allprojects);
	 List<Employee> allusers = superadminprogressservice.getallusers();
        request.setAttribute("allusers", allusers);
        return new ModelAndView("SuperAdminViewProgressExcel");
	}	
	/// to generate excel project wise
	@RequestMapping(value="/superadminprojectwise", method = RequestMethod.POST)
	public String projectwiseexcel(HttpServletRequest request,HttpServletResponse response){
        int projectid = Integer.parseInt(request.getParameter("ddproject"));
        superadminprogressservice.createpwexcel(projectid);
        return "redirect:getsuperadminexcels";
	}
	// to get all modules of a particular selected project by admin
	@RequestMapping(value="/getallmodulesofthisproject")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		try{
			PrintWriter pw=response.getWriter();
			pw.print(superadminprogressservice.getmodules(Integer.parseInt(request.getParameter("projectId"))));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	// to get all submodules of a particular selected module by admin
	@RequestMapping(value="/getallsubmodulesofthemodule")
	public void getthesubmodules(HttpServletRequest request,HttpServletResponse response){
		try{
			PrintWriter pw=response.getWriter();
			pw.print(superadminprogressservice.getsubmodules(Integer.parseInt(request.getParameter("moduleId"))));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	// to generate excel module wise
	@RequestMapping(value="/superadminmodulewise", method = RequestMethod.POST)
	public String modulewiseexcel(HttpServletRequest request,HttpServletResponse response){
        int projectid = Integer.parseInt(request.getParameter("ddmodule1"));
        int moduleid = Integer.parseInt(request.getParameter("ddmodule2"));
        superadminprogressservice.createmwexcel(projectid, moduleid);
        return "redirect:getsuperadminexcels";
	}
	// to generate excel user wise
	@RequestMapping(value="/superadminuserwise", method = RequestMethod.POST)
	public String userwiseexcel(HttpServletRequest request,HttpServletResponse response){
        int userid = Integer.parseInt(request.getParameter("dduser"));
        superadminprogressservice.createuwexcel(userid);
        return "redirect:getsuperadminexcels";
	}
	// to generate sub module wise
	@RequestMapping(value="/superadminsubmodulewise", method = RequestMethod.POST)
	public String submodulewiseexcel(HttpServletRequest request,HttpServletResponse response){
		int projectid = Integer.parseInt(request.getParameter("ddsubmodule1"));
		int moduleid = Integer.parseInt(request.getParameter("ddsubmodule2"));
		int submoduleid = Integer.parseInt(request.getParameter("ddsubmodule3"));
		superadminprogressservice.createsmwexcel(projectid, moduleid, submoduleid);
		return "redirect:getsuperadminexcels";
	}
	
	
}
