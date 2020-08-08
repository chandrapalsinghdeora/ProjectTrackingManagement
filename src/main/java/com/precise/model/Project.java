package com.precise.model;

import java.util.Date;
import java.util.List;

public class Project {
	
	int projectId;
	String projectName;
	String projectDescription;
	String time;
	int isCompleted;
	
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
	
	public int taskId;
	public List<String> taskName;
	public List<String> taskDescription;
	
	public int otherTaskId;
	
	// when modules are being shown to team leader
	public String moduleNameIndividually;
	public String moduleDescriptionIndividually;
	
	public String subModuleNameIndividually;
	public String subModuleDescriptionIndividually;
	
	
	public String taskNameIndividually;
	public String taskDescriptionIndividually;
	
	// for graph purpose
	int totaltask;
	int taskcompleted;
	public double percentage;
	
	
	public int getTotaltask() {
		return totaltask;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public void setTotaltask(int totaltask) {
		this.totaltask = totaltask;
	}
	public int getTaskcompleted() {
		return taskcompleted;
	}
	public void setTaskcompleted(int taskcompleted) {
		this.taskcompleted = taskcompleted;
	}
	public int getOtherTaskId() {
		return otherTaskId;
	}
	public void setOtherTaskId(int otherTaskID) {
		this.otherTaskId = otherTaskID;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public List<String> getTaskName() {
		return taskName;
	}
	public void setTaskName(List<String> taskName) {
		this.taskName = taskName;
	}
	public List<String> getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(List<String> taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getTaskNameIndividually() {
		return taskNameIndividually;
	}
	public void setTaskNameIndividually(String taskNameIndividually) {
		this.taskNameIndividually = taskNameIndividually;
	}
	public String getTaskDescriptionIndividually() {
		return taskDescriptionIndividually;
	}
	public void setTaskDescriptionIndividually(String taskDescriptionIndividually) {
		this.taskDescriptionIndividually = taskDescriptionIndividually;
	}
	public int getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(int isCompleted) {
		this.isCompleted = isCompleted;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
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
