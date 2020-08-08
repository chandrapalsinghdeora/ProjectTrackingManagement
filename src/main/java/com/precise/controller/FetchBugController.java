package com.precise.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.mail.SendMail;
import com.precise.mail.SendMailBug;
import com.precise.model.Bug;
import com.precise.model.SessionBean;
import com.precise.service.FetchBugService;
import com.precise.service.NewBugService;

@Controller
public class FetchBugController {

	@Autowired
	FetchBugService fetchbugservice;
	@Autowired
	NewBugService newbugservice;
	@Autowired
	SendMail sendMail;
	int userId;
	@RequestMapping(value="/btsendtodb",method = RequestMethod.GET)
	public ModelAndView getit(Bug bug,HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int uid=sessionBean.getUserId();
		List<Bug> list=fetchbugservice.gettheinformation(uid);
		List<Bug> teamlist1 =newbugservice.getAllQAProjects(uid);
		request.setAttribute("teamlist1", teamlist1);
		request.setAttribute("pendingapproval", list);
		return new ModelAndView("IndexQA");
	}	
	
	
	@RequestMapping(value="/btsendtopmdb",method = RequestMethod.GET)
	public ModelAndView getitpm(HttpServletRequest request,HttpServletResponse response){
		int prId=1;
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int uid=sessionBean.getUserId();
		
		List<Bug> list=fetchbugservice.gettheinformationpm(uid);
		List<Bug> teamlist1 =newbugservice.getAllPMProjects(prId);
		request.setAttribute("teamlist1", teamlist1);
		request.setAttribute("pendingapproval", list);
		return new ModelAndView("IndexPM");
	}	
	
	@RequestMapping(value="/btsendtobr",method = RequestMethod.GET)
	public ModelAndView report(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int uid=sessionBean.getUserId();
		List<Bug> list=fetchbugservice.getthereport(uid);
		request.setAttribute("pendingapproval", list);
		List<Bug> teamlist1 =newbugservice.getAllPMProjects(uid);
		request.setAttribute("teamlist1", teamlist1);
		List<Bug> teamlist2 =fetchbugservice.getAllAssign();
		request.setAttribute("teamlist2", teamlist2);
		return new ModelAndView("BugReport");
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/btsendexcel",method = RequestMethod.GET)
	/*@Scheduled(cron = "0 0-59 * * * *")*/
	@Scheduled(cron = "0 0 0-1 * * *")
	public ModelAndView excel(){
		fetchbugservice.generateExcel();
		 SendMailBug s=new SendMailBug();
	     String sp="csdeora0803@gmail.com";
	     s.sent(sp);
		return new ModelAndView("Welcome");

	 
	}



	
	@RequestMapping(value="/btsendtobrdate",method = RequestMethod.POST)
	public ModelAndView reportspecific (HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int uid=sessionBean.getUserId();
		String date1=request.getParameter("fromDate");
		String date2=request.getParameter("toDate");
		List<Bug> list=fetchbugservice.getthereportspecific(date1,date2,uid);
		request.setAttribute("pendingapproval", list);
		List<Bug> teamlist1 =newbugservice.getAllPMProjects(uid);
		request.setAttribute("teamlist1", teamlist1);
		List<Bug> teamlist2 =fetchbugservice.getAllAssign();
		request.setAttribute("teamlist2", teamlist2);
		return new ModelAndView("BugReport");
	}	
	@RequestMapping(value="/btsendtobrproject",method = RequestMethod.POST)
	public ModelAndView projectspecific (HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int uid=sessionBean.getUserId();
		String project=request.getParameter("projectId");
		List<Bug> list=fetchbugservice.getthereportproject(project);
		request.setAttribute("pendingapproval", list);
		List<Bug> teamlist1 =newbugservice.getAllPMProjects(uid);
		request.setAttribute("teamlist1", teamlist1);
		List<Bug> teamlist2 =fetchbugservice.getAllAssign();
		request.setAttribute("teamlist2", teamlist2);
		return new ModelAndView("BugReport");
	}
	
	@RequestMapping(value="/btsendtobrassign",method = RequestMethod.POST)
	public ModelAndView assignspecific (HttpServletRequest request,HttpServletResponse response){
		String assign=request.getParameter("userId");
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int uid=sessionBean.getUserId();
		List<Bug> list=fetchbugservice.getthereportassign(assign,uid);
		request.setAttribute("pendingapproval", list);
		List<Bug> teamlist2 =fetchbugservice.getAllAssign();
		request.setAttribute("teamlist2", teamlist2);
		List<Bug> teamlist1 =newbugservice.getAllPMProjects(uid);
		request.setAttribute("teamlist1", teamlist1);
		return new ModelAndView("BugReport");
	}
}
