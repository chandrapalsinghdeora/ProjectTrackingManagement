package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Bug;
import com.precise.model.SessionBean;
import com.precise.service.EditBugService;
import com.precise.service.FetchBugService;
import com.precise.service.NewBugService;

@Controller
public class EditBugController {
	int bugno;

	@Autowired
	EditBugService editbugservice;
	@Autowired
	NewBugService newbugservice;
	@Autowired
	FetchBugService fetchbugservice;
	
	
	@RequestMapping(value="/bteditbug", method = RequestMethod.POST)
	public ModelAndView changedetails(HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
        	
		int uid=sessionBean.getUserId();
	 bugno =  request.getParameter("bugNo")==null?0:Integer.parseInt(request.getParameter("bugNo"));
	 
		List<Bug> teamlist =newbugservice.getAllQAProjects(uid);
	     
		
		List<Bug> detailslist = editbugservice.getbugdetails(bugno);
		request.setAttribute("teamlist1",teamlist);
		ModelAndView modelMapValue = new ModelAndView("edit");
		modelMapValue.addObject("projectId",detailslist.get(0).getProjectId());
		List<Bug> detailslist1 = editbugservice.getthemodules(detailslist.get(0).getProjectId());
		request.setAttribute("teamlist2",detailslist1);
		modelMapValue.addObject("bugnumber",bugno);
		modelMapValue.addObject("bugName",detailslist.get(0).getBugName());
		modelMapValue.addObject("bugType",detailslist.get(0).getBugType());
		modelMapValue.addObject("bugSeverity",detailslist.get(0).getBugSeverity());
		modelMapValue.addObject("moduleid",detailslist.get(0).getModuleId());
		List<Bug> detailslist3 = editbugservice.getthesubmodules(detailslist.get(0).getModuleId());
		request.setAttribute("teamlist3",detailslist3);
		modelMapValue.addObject("submoduleid",detailslist.get(0).getSubModuleId());
		List<Bug> detailslist4 = editbugservice.gettheassigned(detailslist.get(0).getSubModuleId());
		request.setAttribute("teamlist4",detailslist4);
		modelMapValue.addObject("bugStatus",detailslist.get(0).getBugStatus());
		modelMapValue.addObject("description",detailslist.get(0).getDescription());
		modelMapValue.addObject("round",detailslist.get(0).getRound());
		modelMapValue.addObject("assign",detailslist.get(0).getAssign());
		modelMapValue.addObject("depends",detailslist.get(0).getDepends());
		return modelMapValue;
	}	
	@RequestMapping(value="/btsendtodb1",method = RequestMethod.GET)
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
	
	    @RequestMapping(value="/btsendtoedit", method = RequestMethod.POST)
		public String updateit(Bug emp,HttpServletRequest request,HttpServletResponse response){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int id=sessionBean.getUserId();
		String fileName= null;
		fileName= doUploadImage(request,emp);
		String fname=fileName;
		emp.setFileName(fileName);
			editbugservice.updatetheinformation(emp,fname,bugno,id);
	 		return "redirect:btsendtodb1";
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
		
	    @RequestMapping(value="/downloadphotoUploadFile",method={RequestMethod.GET})
		public void downloadDocument(HttpServletResponse response,HttpServletRequest request) throws IOException{
			byte[] bytes=editbugservice.getImageByteData(request.getParameter("bugNo"));
			OutputStream oImage = response.getOutputStream();
			String fileName = (request.getParameter("image") == null) ? "document" : request.getParameter("image");
			response.setHeader("Content-Disposition", "filename=" + fileName);
			if(fileName.contains(".docx")){
				response.setContentType("application/docx");
			}else if(fileName.contains(".pdf")){
				response.setContentType("application/pdf");
			}else if(fileName.contains(".png")||fileName.contains(".PNG")){
				response.setContentType("image/png");
			}else if(fileName.contains("jpeg")){
				response.setContentType("image/jpeg");
			}else if(fileName.contains("jpg")){
				response.setContentType("image/jpg");
			}
			oImage.write(bytes);
		}
}

