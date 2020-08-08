package com.precise.controller;




import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.precise.model.Bug;
import com.precise.service.NewResponseService;

@Controller
public class NewResponseController {
	@Autowired
	NewResponseService newresponseservice;
	
	@RequestMapping(value="/btsendresponse",method = RequestMethod.POST)
	public String sendit(Bug emp,HttpServletRequest request,HttpServletResponse response){
		
		String fileName= null;
		fileName= doUploadImage(request,emp);
		String fname=fileName;
		emp.setFileName(fileName);
		newresponseservice.sendtheresponse(emp,fname);
		return "redirect:btsendtodevdb";
	}
	private String doUploadImage(HttpServletRequest request, Bug fileUploadForm) {
		String uploadRootPath = request.getServletContext().getInitParameter("saveUserFile");

		uploadRootPath = "D:\\file";

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
				uploadedFiles = serverFile.getCanonicalPath();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			uploadedFiles = null;
		}
		
		return uploadedFiles;
	}
	@RequestMapping(value="/btclose",method = RequestMethod.POST)
	public String closeit(Bug emp,HttpServletRequest request,HttpServletResponse response){
		int bugno =  request.getParameter("bugNo")==null?0:Integer.parseInt(request.getParameter("bugNo"));
		newresponseservice.closethebug(emp,bugno);
		return "redirect:btsendtoresdb";
	}	

}
