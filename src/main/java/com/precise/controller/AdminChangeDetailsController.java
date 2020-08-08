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
import com.precise.model.SessionBean;
import com.precise.service.AdminChangeDetailsService;

@Controller
public class AdminChangeDetailsController {

	@Autowired
	AdminChangeDetailsService adminchangedetails;
	
	@RequestMapping(value="/changedetails", method = RequestMethod.GET)
	public ModelAndView changedetails(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int userid=sessionBean.getUserId();
		List<Employee> detailslist = adminchangedetails.getadminpreviousdetails(userid);
		ModelAndView modelMapValue = new ModelAndView("register");
		modelMapValue.addObject("userId",detailslist.get(0).getUserId());
		modelMapValue.addObject("email",detailslist.get(0).getEmail());
		modelMapValue.addObject("userName",detailslist.get(0).getUserName());
		modelMapValue.addObject("password",detailslist.get(0).getUserPassword());
		modelMapValue.addObject("name",detailslist.get(0).getName());
		modelMapValue.addObject("empDesignation",detailslist.get(0).getEmpDesignation());
		return modelMapValue;
	}	
	
	@RequestMapping(value = "/updatedetails", method = RequestMethod.POST)
	public String updateDetails(Employee emp,HttpServletRequest request) {
		int userid =  request.getParameter("userid")==null?0:Integer.parseInt(request.getParameter("userid"));
		adminchangedetails.updateDetails(emp, userid);
		return "redirect:changedetails";
	}
	
}
