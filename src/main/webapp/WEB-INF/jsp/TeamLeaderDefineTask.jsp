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
</script>
	<style>
	.left_margin{margin-left:0px;}
	.pull_right{float:right;}
    .pull_left{float:left;}</style>
	<body>
	<div class="affix" style="width:100%;" >
		<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="tlhome">Home</a></li>
						<li><a href="tlhome">Team Leader</a></li>
						<li><a href="#">Add Task</a></li>
					</ul>
				</div>
	
			</div>
		</div>
		</div>
	
	
	
	
			<div class="container-fluid" style="padding:250px 0px 100px 0px;min-height:738px;">
	
	<input type="button" class="btn btn-primary pull-right"
					data-toggle="modal" data-target="#addmodal2" value="Add Task" style="margin-right:135px;margin-top:0px;">
		  <div class="modal fade" id="addmodal2" role="dialog" >
    <div class="modal-dialog">
	 <div class="modal-content">
        <div class="modal-header" style="padding:15px 25px; background-color:#006780;color:white;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h4>Add Sub Module</h4>
        </div>
        <div class="modal-body" style="padding:20px 25px;" >
		<div class="container " style="width:515px;">
			<div class="space"></div>
		
	
			<form class="" id="savethetasks" method="post">
	      
				<div class="border">
				<div class=" container row">
					<div class="col-sm-1">Project</div>
					<div class="col-sm-4">
						<select class="form-control" name="projectId" id="projectId" onchange="getallmodules(this);" required>
							<option value="0">Select Project</option>
							<c:forEach items="${projectlist}" var="project">
								<option value="${project.projectId}">${project.projectName}</option>
							</c:forEach>
	
						</select>
					</div>
					</div>        </br>
					<div class=" container row">
					<div class="col-sm-1">Module</div>
					<div class="col-sm-4">
						<select class="form-control" name="moduleId" id="moduleId" onchange="getsubmodules(this);">
                            <option value="0" selected>Select Module</option>
						</select>
					</div>
					</div>
			         </br>
			   
					<div class=" container row">
					<div class="col-sm-1">SubModule</div>
					<div class="col-sm-4">
						<select class="form-control" name="subModuleId" id="subModuleId" onchange="gettasks(this);">
                            <option value="0" selected>Select SubModule</option>
						</select>
					</div>
                   </div>
			</div>
	
				<div class="panel panel-primary">
					<div class="panel-heading">SubModule Tasks</div>
	
					<div id="interviewbox">
						<div class="panel-body">
							<div class="form-group">
								<div class="col-sm-3">Task Name</div>
								<div class="col-sm-9">
     								<input rows="2" class="form-control" type="text" name="taskName" id="taskName">
								</div>
							</div>
							
							<div class="form-group ">
								<div class="col-sm-3 left_margin ">Task Description</div>
								<div class="col-sm-7">
									<textarea rows="4" col="10" style="resize:none;width:310px;" class="form-control" name="taskDescription"
										id="taskDescription"></textarea>
								</div>
							</div>
						</div>
					</div>
					
					<div id="interviewId"></div>
					
				</div>
			</form>
	
		<div class="pull_right">
				<button class="btn btn-primary " id="add-panel">Add Task</button>
				<button class="btn btn-primary" id="remove-panel">Remove Task</button>
				</div>
	
				<div class="space"></div>
			<div class="pull_left">
				<input type="button" value="Submit" onclick="saveit()" class="btn btn-primary" /> 
				<input type="button" value="Cancel"	class="btn btn-primary" />
			</div>
		</div>
	

	
	 <div id="projectsubmodules">
	         
	 </div>
	</div>
	
	        <div class="modal-footer" style="background-color:#006780;">
          <button type="submit" class="btn btn-default pull-left" data-dismiss="modal" style="margin:5px 0px;"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
          
 </div>
      </div>
      
    </div>
  </div> 
  
  
<!-- ---------------------------------------------------------------------------------------- -->  
  


>
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Update Task</h4>
						</div>
						
						<form action="tlupdatetask" method="post">
						     <input type="hidden" id="taskId" name="taskId">
<!--for edit or new entry purpose --> 
						
							<div class="modal-body">
								
								<div class="row">
									<label class="col-md-4">Task Name</label>
									<div class="col-md-8">
										<input class="form-control"  id="taskNameIndividuallyl" placeholder="Task Name"
											type="text" name="taskNameIndividually" required>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-4">Task Description</label>
									<div class="col-md-8">
										 <textarea class="form-control" id="taskDescriptionIndividuallyl" name="taskDescriptionIndividually" style="resize:none;" placeholder="Description" required></textarea>
									</div>
								</div>
						
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default">Add</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
							</div>
						</form>
					</div>
				</div>
			</div>





  
<!-- ---------------------------------------------------------------------------------------- --> 
  <div class="container">
		<div class="row">
			<!-- panel preview -->
			
			<div class="container-fluid btn-container">

				
			</div>
			<div class="clearfix"></div>
						<table id="main-fourum" style="margin-bottom: -52px;"  class="table table-striped table-bordered">
				<thead>
					<tr>
						   <th>S.No</th>
						   <th>Project Name</th>
						   <th>Module Name</th>
						   <th>SubModule Name</th>
		                   <th>Task Name</th>
		                   <th>Task Description</th>
		                    <th>Action Edit</th>
		                     <th>Action Delete</th>
		          
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasklist}" var="task" varStatus="loop">
					<tr>
					<td>${loop.index+1 }</td>
					<td>${task.projectName }</td>
					<td>${task.moduleNameIndividually }</td>
					<td>${task.subModuleNameIndividually }</td>
					<td>${task.taskNameIndividually }</td>
					<td>${task.taskDescriptionIndividually }</td>
					<td><input type="button" class="btn btn-primary"
								data-taskname="${task.taskNameIndividually}" data-taskdescription="${task.taskDescriptionIndividually}"
								data-toggle="modal" data-id="${task.taskId}" data-target="#C-role-edit"
								id="edit" value="Edit"> 
								 <!-- Modal --></td>
							<td> <input type="button" onclick="deleteStudent(${task.taskId})" class="btn btn-primary "data-name="" data-description=""
								data-toggle="" data-id="" data-target=""
								id="delete" value="delete" style="margin:0px 0px;" ></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			 <form method="post" id="deleteStudentForm" >
          		<input type="hidden" name="deleteTaskId" id="deleteTaskId"/>
          	</form>
			<!-- / panel preview -->

		</div>
	</div>
	</div>
	<jsp:include page="commonJsp/Footer.jsp" />
  
  
	</body>
		<script>
$(document).on("click", "#edit", function () {
    var Id = $(this).data('id');
    $("#taskId").val(Id);
    $("#taskNameIndividuallyl").val($(this).data('taskname'));
    $("#taskDescriptionIndividuallyl").val($(this).data('taskdescription'));
    $("#C-role-edit").show();
});

function deleteStudent(id){
	$("#deleteTaskId").val(id);
	document.getElementById("deleteStudentForm").action = "deletetask";
	document.getElementById("deleteStudentForm").method = "post";
	document.getElementById("deleteStudentForm").submit();
}
</script>
	
	
	<script>
		
		function saveit(){
				document.getElementById("savethetasks").action = "savetasks";
				document.getElementById("savethetasks").method = "post";
				document.getElementById("savethetasks").submit();
		}
		 function updateit(){
				document.getElementById("updateindividualsubmodulel").action = "updateindividualsubmodule";
				document.getElementById("updateindividualsubmodulel").method = "post";
				document.getElementById("updateindividualsubmodulel").submit();
		    }
		 
	 	$(document).ready(function() {
		 //$('.chkpref').hide();
		    var x=1;  
	    	$("#add-panel").click(function() {
				var wrapper=$('#interviewId');
		  		var data1=$('#interviewbox').html();
		    
				if(x<11)
				{
				   var domElement = $(' <div class="panel panel-primary"><div class="panel-heading">Add Task ' +x+ '</div>'+data1+'</div></div>');
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