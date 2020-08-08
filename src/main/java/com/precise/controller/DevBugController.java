package com.precise.controller;




import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Bug;
import com.precise.model.SessionBean;
import com.precise.service.DevBugService;


@Controller
public class DevBugController {

	@Autowired
	DevBugService devbugservice;
	
	@RequestMapping(value="/btsendtodevdb",method = RequestMethod.GET)
	public ModelAndView getit(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int id=sessionBean.getUserId();
		List<Bug> list= devbugservice.devgettheinformation(id);
		request.setAttribute("pendingapproval", list);
		return new ModelAndView("IndexDev");
		
		
	}	

}
