<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
	<title>Project Tracking Management</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- Bootstrap -->
		<link href="css/bootstrap.css" rel="stylesheet">
		<!-- Custom -->
		<link href="css/custom.css" rel="stylesheet">
		<link
			href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
			rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="css/font-awesome.css">
		
		<!-- CSS STYLE-->
		<link rel="stylesheet" type="text/css" href="css/style.css"
			media="screen" />
		
		<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
		<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
			media="screen" />
	
	</head>
	<style>
	.left_margin{margin-left:0px;}</style>
	<body>
	<div class="affix" style="width:100%;" >
<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="#">Home</a></li>
								<li><a href="#">Team Leader</a></li>
						<li><a href="#">Task Dependency</a></li>
					</ul>
				</div>
	
			</div>
		</div>
	</div>
		<div class="container">
		<div class="container-fluid" style="padding-top:224px;min-height:738px;">
		<div class="panel panel-default" >
		    <div class="panel-heading" style="background-color:#006780;color:white;"> Task Dependency</div>
			<div class="space"></div>
			<div class="row"></div>
	
			<form class="form-horizontal" id="savethetasksde" method="post">
	           <input type="hidden" value="${project.projectId}" name="pid">
				<div class="border">
					<div class="col-sm-2">Project</div>
					<div class="col-sm-4">
						<select class="form-control" name="projectId" id="projectId" onchange="getallmodules(this);" required>
							<option value="0">Select Project</option>
							<c:forEach items="${projectlist}" var="project">
								<option value="${project.projectId}">${project.projectName}</option>
							</c:forEach>
	
						</select>
					</div>
					<br>
					<br>
					<br>
					<br>
					<div class="col-sm-2">Module</div>
					<div class="col-sm-4">
						<select class="form-control" name="moduleId" id="moduleId" onchange="getsubmodules(this);">
                            <option value="0" selected>Select Module</option>
						</select>
					</div>
					<br>
					<br>
					<br>
					<br>
					<div class="col-sm-2">SubModule</div>
					<div class="col-sm-4">
						<select class="form-control" name="subModuleId" id="subModuleId" onchange="gettasks(this);">
                            <option value="0" selected>Select SubModule</option>
						</select>
					</div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="col-sm-2">Tasks</div>
					<div class="col-sm-4">
						<select class="form-control" name="taskId" id="taskId" onchange="getothertasks(this);">
                            <option value="0" selected>Select Task</option>
						</select>
					</div>

					<div class="col-sm-2">Dependent On</div>
					<div class="col-sm-4">
						<select class="form-control" name="otherTaskId" id="otherTaskId" >
                            <option value="0" selected>Select other task</option>
						</select>
					</div>
				</div>
	
			<br>
                    <br>
                    <br>
                    <br>	
				
			</form>
	
			<div class="marg" style="margin-bottom:15px; ">
			<center>	<input type="button" value="Submit" onclick="saveit()" class="btn btn-primary" /> 
				<input type="button" value="Cancel"	class="btn btn-primary" /> </center>
			</div>
			<div class="space"></div>
		</div>
	</div></div>
			
<jsp:include page="commonJsp/Footer.jsp" />
	
	</body>
	
	<script>

		function getallmodules(val){
			document.getElementById("moduleId").innerHTML="";
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var data="<option value="+"0"+" selected>See Modules<oprion>";
					var jsonData=JSON.parse(this.responseText);
					for(var i=0;i<jsonData.length;i++){
						data+="<option value="+jsonData[i].moduleid+">"+jsonData[i].modulename+"<oprion>";
					}
					document.getElementById("moduleId").innerHTML=data;
				}
			};
			xhttp.open("POST", "assignedmoduletotl?projectId="+val.value, true);
			xhttp.send();
		}
	
		function getsubmodules(val){
			document.getElementById("subModuleId").innerHTML="";
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var data="<option value="+"0"+" selected>See SubModules<oprion>";
					var jsonData=JSON.parse(this.responseText);
					for(var i=0;i<jsonData.length;i++){
						data+="<option value="+jsonData[i].submoduleid+">"+jsonData[i].submodulename+"<oprion>";
					}
					document.getElementById("subModuleId").innerHTML=data;
				}
			};
			xhttp.open("POST", "submodofthismodule?moduleId="+val.value, true);
			xhttp.send();

		}
		
		function gettasks(val)
		{
			document.getElementById("taskId").innerHTML="";
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var data="<option value="+"0"+" selected>See Tasks<oprion>";
					var jsonData=JSON.parse(this.responseText);
					for(var i=0;i<jsonData.length;i++){
						data+="<option value="+jsonData[i].taskid+">"+jsonData[i].taskname+"<oprion>";
					}
					document.getElementById("taskId").innerHTML=data;
				}
			};
			xhttp.open("POST", "tasksofthissubmodule?submoduleId="+val.value, true);
			xhttp.send();
		}
		
		function getothertasks(val)
		{
			document.getElementById("otherTaskId").innerHTML="";
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var data="<option value="+"0"+" selected>See Tasks<oprion>";
					var jsonData=JSON.parse(this.responseText);
					for(var i=0;i<jsonData.length;i++){
						data+="<option value="+jsonData[i].othertaskid+">"+jsonData[i].othertaskname+"<oprion>";
					}
					document.getElementById("otherTaskId").innerHTML=data;
				}
			};
			xhttp.open("POST", "othertasksofthissubmodule?taskId="+val.value, true);
			xhttp.send();
		}
		
		function saveit(){
				document.getElementById("savethetasksde").action = "assigntaskdependency";
				document.getElementById("savethetasksde").method = "post";
				document.getElementById("savethetasksde").submit();
		}
	 

		</script>

</html>