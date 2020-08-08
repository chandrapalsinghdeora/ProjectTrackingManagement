package com.precise.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Employee;
import com.precise.service.AdminPendingApprovalService;

@Controller
public class AdminPendingApprovalController {
	
	@Autowired
	AdminPendingApprovalService pendingapproval;

	@RequestMapping(value="/adminpendingapproval", method = RequestMethod.GET)
	public ModelAndView approveIt(HttpServletRequest request,HttpServletResponse response){
		List<Employee> list = pendingapproval.getAllPendingApproval();
		request.setAttribute("pendingapproval", list);
		return new ModelAndView("ProjectManagerPendingApproval");
	}	
	
	@RequestMapping(value="/approve", method = RequestMethod.POST)
	public String approverequest(HttpServletRequest request,HttpServletResponse response){
		int id =  request.getParameter("userid")==null?0:Integer.parseInt(request.getParameter("userid"));
		pendingapproval.approverequest(id);
		return "redirect:adminpendingapproval";
	}	
	
	@RequestMapping(value="/reject", method = RequestMethod.POST)
	public String rejectrequest(HttpServletRequest request,HttpServletResponse response){
		int userid =  request.getParameter("userid")==null?0:Integer.parseInt(request.getParameter("userid"));
		pendingapproval.rejectrequest(userid);
		return "redirect:adminpendingapproval";
	}
 
	@RequestMapping(value="/rolependingapproval", method = RequestMethod.GET)
	public ModelAndView giveitrole(HttpServletRequest request,HttpServletResponse response){
		List<Employee> list = pendingapproval.getAllApproved();
		request.setAttribute("roleapproved", list);
		return new ModelAndView("ProjectManagerPendingRole");
	}
	
	@RequestMapping(value="/approverole", method = RequestMethod.POST)
	public String approverole(HttpServletRequest request,HttpServletResponse response){
		int userid =  request.getParameter("userid")==null?0:Integer.parseInt(request.getParameter("userid"));
		int roleid =  request.getParameter("roleid")==null?0:Integer.parseInt(request.getParameter("roleid"));
		pendingapproval.approverole(userid, roleid);
		return "redirect:rolependingapproval";
	}	
	
}
