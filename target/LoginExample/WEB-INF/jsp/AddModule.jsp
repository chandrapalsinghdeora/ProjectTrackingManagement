<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
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
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="#">Home</a></li>
					</ul>
				</div>
	
			</div>
		</div>
	
		<div class="container">
			<div class="space"></div>
			<div class="row"></div>
	
			<form class="form-horizontal" id="savethemodules" method="post">
	           <input type="hidden" value="${project.projectId}" name="pid">
				<div class="border">
					<div class="col-sm-2">Project</div>
					<div class="col-sm-3">
						<select class="form-control" name="projectId" id="projectId">
							<option value="0">select</option>
							<c:forEach items="${projectlist}" var="project">
								<option value="${project.projectId}">${project.projectName}</option>
							</c:forEach>
	
						</select>
					</div>

				</div>
	
				<div class="panel panel-primary">
					<div class="panel-heading">Project Modules</div>
	
					<div id="interviewbox">
						<div class="panel-body">
							<div class="form-group">
								<div class="col-sm-3">Module Name</div>
								<div class="col-sm-9">
     								<input rows="2" class="form-control" type="text" name="moduleName" id="modulename">
								</div>
							</div>
							
							<div class="form-group ">
								<div class="col-sm-3 left_margin ">Module Description</div>
								<div class="col-sm-7">
									<textarea rows="2" style="width: 652px; height: 69px;" class="form-control" name="moduleDescription"
										id="moduledescription"></textarea>
								</div>
							</div>
						</div>
					</div>
					
					<div id="interviewId"></div>
					
				</div>
			</form>
	
			<div class="col-sm-offset-7 col-sm-5 text-center">
				<button class="btn btn-primary" id="add-panel">Add Module</button>
				<button class="btn btn-primary" id="remove-panel">Remove Module</button>
			</div>
	
			<div class="marg">
				<input type="button" value="Submit" onclick="saveit()" class="btn btn-primary" /> 
				<input type="button" value="Cancel"	class="btn btn-primary" />
			</div>
			<div class="space"></div>
		</div>
	
		<script src="assets/js/jquery-2.1.4.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
	
	</body>
	
	<script>

		function saveit(){
				document.getElementById("savethemodules").action = "savemodules";
				document.getElementById("savethemodules").method = "post";
				document.getElementById("savethemodules").submit();
		}
	 
	 	$(document).ready(function() {
		 //$('.chkpref').hide();
		    var x=1;  
	    	$("#add-panel").click(function() {
				var wrapper=$('#interviewId');
		  		var data1=$('#interviewbox').html();
		    
				if(x<11)
				{
				   var domElement = $(' <div class="panel panel-primary"><div class="panel-heading">Add Modules ' +x+ '</div>'+data1+'</div></div>');
					$(wrapper).append(domElement);
					x++;	
				}
	    	});
	
			 $("#remove-panel").click(function() {
				 var main = document.getElementById("interviewId");
				 x--;	
				 if (main.children.length > 0) {           
					 main.lastChild.remove();  
			 	}
						
		    });
		    
		});

		</script>

</html>