package com.precise.controller;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Bug;
import com.precise.service.FetchBugService;
import com.precise.service.IssueService;


@Controller
public class IssueController {

	@Autowired
	IssueService issueservice;
	
	@Autowired
	FetchBugService fetchbugservice;
	int bugno;
	int bugno1; 
	
	@RequestMapping(value="/btapprove", method = RequestMethod.POST)
	public ModelAndView approverequest(HttpServletRequest request,HttpServletResponse response){

		bugno =  request.getParameter("bugNo")==null?0:Integer.parseInt(request.getParameter("bugNo"));
		List<Bug> list=issueservice.issueinformation(bugno);
		ModelAndView modelMapValue = new ModelAndView("Issue");
		System.out.println("ppppp"+bugno);
		modelMapValue.addObject("qa_name",list.get(0).getQAName());
		modelMapValue.addObject("name",list.get(0).getMonitor());
		modelMapValue.addObject("assign_to",list.get(0).getAssignTo());
		modelMapValue.addObject("modname",list.get(0).getModule());
		modelMapValue.addObject("subModulename",list.get(0).getSubModule());
		modelMapValue.addObject("round",list.get(0).getRound());
		modelMapValue.addObject("description",list.get(0).getDescription());
		modelMapValue.addObject("depends",list.get(0).getDepends());
		modelMapValue.addObject("date",list.get(0).getDate());
		return modelMapValue;
	}
	@RequestMapping(value="/downloadphotoUploadFile", method = RequestMethod.POST)
	public ModelAndView downloadrequest(HttpServletRequest request,HttpServletResponse response){
	List<Bug> emp = fetchbugservice.ifile(bugno);
	String mimeType=null;
	File f=null;
	try{
	f = new File(emp.get(0).getImage());
	mimeType = getMimeType(f.getCanonicalPath());
	} catch (Exception e) {
			List<Bug> list=issueservice.issueinformation(bugno);
			String message="no file attached";
			ModelAndView modelMapValue = new ModelAndView("Issue","message",message);
			System.out.println("ppppppppppp"+bugno);
			modelMapValue.addObject("qa_name",list.get(0).getQAName());
			modelMapValue.addObject("name",list.get(0).getMonitor());
			modelMapValue.addObject("assign_to",list.get(0).getAssignTo());
			modelMapValue.addObject("modname",list.get(0).getModule());
			modelMapValue.addObject("subModulename",list.get(0).getSubModule());
			modelMapValue.addObject("round",list.get(0).getRound());
			modelMapValue.addObject("description",list.get(0).getDescription());
			modelMapValue.addObject("depends",list.get(0).getDepends());
			modelMapValue.addObject("date",list.get(0).getDate());
			return modelMapValue;
	}
   if (mimeType == null) {        
       mimeType = "application/excel";
   }
           response.setContentType(mimeType);
           response.setHeader("Content-Disposition", "attachment;filename=\"" + f.getName()
                           + "\"");
           response.setContentLength((int) f.length());
           InputStream is = null;
			try {
				is = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
           ServletOutputStream outStream = null;
			try {
				outStream = response.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}  
           try {
				org.apache.commons.io.IOUtils.copy(is, outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
           try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
           try {
				outStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
           return new ModelAndView("Issue");
}	


public static String getMimeType(String fName) {
   fName = fName.toLowerCase();
   if (fName.endsWith(".jpg") || fName.endsWith(".jpeg") || fName.endsWith(".jpe")) return "image/jpeg";
   else if (fName.endsWith(".gif")) return "image/gif";
   else if (fName.endsWith(".pdf")) return "application/pdf";
   else if (fName.endsWith(".htm") || fName.endsWith(".html")  || fName.endsWith(".htmls") || fName.endsWith(".shtml")) return "text/html";
   else if (fName.endsWith(".avi")) return "video/x-msvideo";
   else if (fName.endsWith(".mov") || fName.endsWith(".qt")) return "video/quicktime";
   else if (fName.endsWith(".mpg") || fName.endsWith(".mpeg") || fName.endsWith(".mpe")) return "video/mpeg";
   else if (fName.endsWith(".zip")) return "application/zip";
   else if (fName.endsWith(".tiff") || fName.endsWith(".tif")) return "image/tiff";
   else if (fName.endsWith(".rtf")) return "application/rtf";
   else if (fName.endsWith(".mid") || fName.endsWith(".midi")) return "audio/x-midi";
   else if (fName.endsWith(".xl") || fName.endsWith(".xls") || fName.endsWith(".xlsx") || fName.endsWith(".xlv")
                   || fName.endsWith(".xla") || fName.endsWith(".xlb") || fName.endsWith(".xlt")
                   || fName.endsWith(".xlm") || fName.endsWith(".xlk")) return "application/excel";
   else if (fName.endsWith(".doc") || fName.endsWith(".docx") || fName.endsWith(".dot")) return "application/msword";
   else if (fName.endsWith(".png")) return "image/png";
   else if (fName.endsWith(".ppt") || fName.endsWith(".pptx") ) return "application/mspowerpoint";
   else if (fName.endsWith(".js")) return "application/javascript";
   else if (fName.endsWith(".xml")) return "text/xml";
   else if (fName.endsWith(".svg")) return "image/svg+xml";
   else if (fName.endsWith(".mp3")) return "audio/mp3";
   else if (fName.endsWith(".ogg")) return "audio/ogg";
   else return "text/plain";
}



@RequestMapping(value="/btdownload", method = RequestMethod.POST)
public String downloadfile(HttpServletRequest request,HttpServletResponse response){

    int bugno1 =  request.getParameter("bugNo")==null?0:Integer.parseInt(request.getParameter("bugNo"));
List<Bug> emp = fetchbugservice.ifileres(bugno1);
String mimeType=null;
File f=null;
try{
 f = new File(emp.get(0).getImage());
	mimeType = getDevMimeType(f.getCanonicalPath());
} catch (Exception e) {
	return "redirect:btsendtoresdb";
}
if (mimeType == null) {        
   mimeType = "application/octet-stream";
}
       response.setContentType(mimeType);
       response.setHeader("Content-Disposition", "attachment;filename=\"" + f.getName()
                       + "\"");
       response.setContentLength((int) f.length());
       InputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
       ServletOutputStream outStream = null;
		try {
			outStream = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}  
       try {
			org.apache.commons.io.IOUtils.copy(is, outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
       try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       try {
			outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
       return "redirect:btsendtoresdb";
}	


public static String getDevMimeType(String fName) {
fName = fName.toLowerCase();
if (fName.endsWith(".jpg") || fName.endsWith(".jpeg") || fName.endsWith(".jpe")) return "image/jpeg";
else if (fName.endsWith(".gif")) return "image/gif";
else if (fName.endsWith(".pdf")) return "application/pdf";
else if (fName.endsWith(".htm") || fName.endsWith(".html")  || fName.endsWith(".htmls") || fName.endsWith(".shtml")) return "text/html";
else if (fName.endsWith(".avi")) return "video/x-msvideo";
else if (fName.endsWith(".mov") || fName.endsWith(".qt")) return "video/quicktime";
else if (fName.endsWith(".mpg") || fName.endsWith(".mpeg") || fName.endsWith(".mpe")) return "video/mpeg";
else if (fName.endsWith(".zip")) return "application/zip";
else if (fName.endsWith(".tiff") || fName.endsWith(".tif")) return "image/tiff";
else if (fName.endsWith(".rtf")) return "application/rtf";
else if (fName.endsWith(".mid") || fName.endsWith(".midi")) return "audio/x-midi";
else if (fName.endsWith(".xl") || fName.endsWith(".xls") || fName.endsWith(".xlsx") || fName.endsWith(".xlv")
               || fName.endsWith(".xla") || fName.endsWith(".xlb") || fName.endsWith(".xlt")
               || fName.endsWith(".xlm") || fName.endsWith(".xlk")) return "application/excel";
else if (fName.endsWith(".doc") || fName.endsWith(".docx") || fName.endsWith(".dot")) return "application/msword";
else if (fName.endsWith(".png")) return "image/png";
else if (fName.endsWith(".ppt") || fName.endsWith(".pptx") ) return "application/mspowerpoint";
else if (fName.endsWith(".js")) return "application/javascript";
else if (fName.endsWith(".xml")) return "text/xml";
else if (fName.endsWith(".svg")) return "image/svg+xml";
else if (fName.endsWith(".mp3")) return "audio/mp3";
else if (fName.endsWith(".ogg")) return "audio/ogg";
else return "text/plain";
}
}
