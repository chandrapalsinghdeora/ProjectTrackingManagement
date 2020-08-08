package com.precise.model;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Bug {
	int bugNo;
	String image;
	String name;
	String bugSeverity;
	public String getBugSeverity() {
		return bugSeverity;
	}
	public void setBugSeverity(String bugSeverity) {
		this.bugSeverity = bugSeverity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	Date date;
	String delievered;
	public String getDelievered() {
		return delievered;
	}
	public void setDelievered(String delievered) {
		this.delievered = delievered;
	}
	String toDate;
	String QAName;
	public String getQAName() {
		return QAName;
	}
	public void setQAName(String qAName) {
		QAName = qAName;
	}
	String assignBy;
	String projectManager; 
	String status;
	String projectName;
	String bugName;
    String bugType;
    String module;
    String subModule;
    String bugStatus;
    String description;
    String round;
    String monitor;
	String assignTo;
    String depends;
    String summary;
    int projectId;
	String projectDescription;
	String time;
	int isCompleted;
    String fileName;
	String fname;
    MultipartFile fileData;
    int assign;
    public int getAssign() {
		return assign;
	}
	public void setAssign(int assign) {
		this.assign = assign;
	}
	int QAId;
	
	public int getQAId() {
		return QAId;
	}
	public void setQAId(int qAId) {
		QAId = qAId;
	}
	// when pm will assign project to the particular team leader
	int userId;
	String empDesignation;
	Date startDate;
	Date endDate;
	
	public int moduleId;
	public List<String> moduleName;
	public List<String> moduleDescription;
	
	public int subModuleId;
	public List<String> subModuleName;
	public List<String> subModuleDescription;
	
	// when modules are being shown to team leader
	public String moduleNameIndividually;
	public String moduleDescriptionIndividually;
	
	public String subModuleNameIndividually;
	public String subModuleDescriptionIndividually;
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public MultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage() {
		return image;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSubModule() {
		return subModule;
	}
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	public String getBugStatus() {
		return bugStatus;
	}
	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public String getDepends() {
		return depends;
	}
	public void setDepends(String depends) {
		this.depends = depends;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getBugName() {
		return bugName;
	}
	public void setBugName(String bugName) {
		this.bugName = bugName;
	}
	public String getBugType() {
		return bugType;
	}
	public void setBugType(String bugType) {
		this.bugType = bugType;
	}
	public int getBugNo() {
		return bugNo;
	}
	public void setBugNo(int bugNo) {
		this.bugNo = bugNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssignBy() {
		return assignBy;
	}
	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	 public int getProjectId() {
			return projectId;
		}
		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}
		public String getProjectDescription() {
			return projectDescription;
		}
		public void setProjectDescription(String projectDescription) {
			this.projectDescription = projectDescription;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public int getIsCompleted() {
			return isCompleted;
		}
		public void setIsCompleted(int isCompleted) {
			this.isCompleted = isCompleted;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getEmpDesignation() {
			return empDesignation;
		}
		public void setEmpDesignation(String empDesignation) {
			this.empDesignation = empDesignation;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public int getModuleId() {
			return moduleId;
		}
		public String getMonitor() {
			return monitor;
		}
		public void setMonitor(String monitor) {
			this.monitor = monitor;
		}
		public void setModuleId(int moduleId) {
			this.moduleId = moduleId;
		}
		public List<String> getModuleName() {
			return moduleName;
		}
		public void setModuleName(List<String> moduleName) {
			this.moduleName = moduleName;
		}
		public List<String> getModuleDescription() {
			return moduleDescription;
		}
		public void setModuleDescription(List<String> moduleDescription) {
			this.moduleDescription = moduleDescription;
		}
		public int getSubModuleId() {
			return subModuleId;
		}
		public void setSubModuleId(int subModuleId) {
			this.subModuleId = subModuleId;
		}
		public List<String> getSubModuleName() {
			return subModuleName;
		}
		public void setSubModuleName(List<String> subModuleName) {
			this.subModuleName = subModuleName;
		}
		public List<String> getSubModuleDescription() {
			return subModuleDescription;
		}
		public void setSubModuleDescription(List<String> subModuleDescription) {
			this.subModuleDescription = subModuleDescription;
		}
		public String getModuleNameIndividually() {
			return moduleNameIndividually;
		}
		public void setModuleNameIndividually(String moduleNameIndividually) {
			this.moduleNameIndividually = moduleNameIndividually;
		}
		public String getModuleDescriptionIndividually() {
			return moduleDescriptionIndividually;
		}
		public void setModuleDescriptionIndividually(String moduleDescriptionIndividually) {
			this.moduleDescriptionIndividually = moduleDescriptionIndividually;
		}
		public String getSubModuleNameIndividually() {
			return subModuleNameIndividually;
		}
		public void setSubModuleNameIndividually(String subModuleNameIndividually) {
			this.subModuleNameIndividually = subModuleNameIndividually;
		}
		public String getSubModuleDescriptionIndividually() {
			return subModuleDescriptionIndividually;
		}
		public void setSubModuleDescriptionIndividually(String subModuleDescriptionIndividually) {
			this.subModuleDescriptionIndividually = subModuleDescriptionIndividually;
		}
}
