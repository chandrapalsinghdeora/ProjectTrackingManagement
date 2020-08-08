package com.precise.controller;




import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.precise.model.Bug;
import com.precise.model.SessionBean;
import  com.precise.service.NewBugService;

@Controller
public class NewBugController {

	@Autowired
	NewBugService newbugservice;
	
	@RequestMapping(value="/btsendtoor",method = RequestMethod.POST)
	public String sendit(Bug emp, HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int id=sessionBean.getUserId();
		String fileName= null;
		fileName= doUploadImage(request,emp);
		String fname=fileName;
		emp.setFileName(fileName);
	    newbugservice.sendtheinformation(emp,fname,id);
 		return "redirect:btsendtodb";
	 }

	private String doUploadImage(HttpServletRequest request, Bug fileUploadForm) {
		String uploadRootPath =  "D:\\file";
		File uploadRootDir = new File(uploadRootPath);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile fileData = fileUploadForm.getFileData();
		String uploadedFiles = "";
		String name = fileData.getOriginalFilename();

		if (name != null && name.length() > 0) {
			try {
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileData.getBytes());
				stream.close();
				System.out.println(
						"Write file: " + serverFile.getCanonicalPath() + " :: " + serverFile.getAbsolutePath());
				uploadedFiles = serverFile.getCanonicalPath();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			uploadedFiles = null;
		}
		
		return uploadedFiles;
	}
	
	@RequestMapping(value="/btGetallModules")
	public void getthemodules(HttpServletRequest request,HttpServletResponse response){
		System.out.println("NewBugController.getthemodules()");
		try{
			PrintWriter pw=response.getWriter();
			pw.print(newbugservice.getmodules(Integer.parseInt(request.getParameter("projectId"))));
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

		@RequestMapping(value="/btGetallSubModules")
		public void getthesubmodules(HttpServletRequest request,HttpServletResponse response){
			try{
				PrintWriter pw=response.getWriter();
				pw.print(newbugservice.getsubmodules(Integer.parseInt(request.getParameter("moduleId"))));
				pw.flush();
				pw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	}
		@RequestMapping(value="/btGetAssigned")
		public void getAssigned(HttpServletRequest request,HttpServletResponse response){
			try{
				PrintWriter pw=response.getWriter();
				pw.print(newbugservice.getassigned(Integer.parseInt(request.getParameter("submoduleId"))));
				pw.flush();
				pw.close();
			}catch(Exception e){
				e.printStackTrace();
			}

}
}