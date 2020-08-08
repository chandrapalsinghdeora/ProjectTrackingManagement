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
<title>Project Tracking Management</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
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
<body>
<div class="affix" style="width:100%;" >
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="superadminhome">Home</a></li>
					<li><a href="#">Add Project</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div></div>
	
	<div class="container-fluid" style="padding-top:200px;min-height:738px;">
	
	<div class="container">
		<div class="row">
			<!-- panel preview -->
			<div class="space"></div>
			<div class="btn-container" >

				<input type="button" class="btn btn-primary pull-right"
					data-toggle="modal" data-target="#C-role-edit" value="Add Project" style="margin-bottom:20px;" onclick="clearForm();" >

			</div>
			<div class="clearfix"></div>
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
						   <th>S.No</th>
		                  
		                   <th>Project Name</th>
		                   <th>Project Description</th>
						   <th>Action Edit</th>
						   <th>Action Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${projects}" var="pro" varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							<td>${pro.projectName}</td>
							<td>${pro.projectDescription}</td>
							<td><input type="button" class="btn btn-primary"
								data-name="${pro.projectName}" data-description="${pro.projectDescription}"
								data-toggle="modal" data-id="${pro.projectId}" data-target="#C-role-edit"
								id="edit" value="Edit"> 
								 <!-- Modal --></td>
							<td> <input type="button" onclick="deleteStudent(${pro.projectId})" class="btn btn-primary "data-name="" data-description=""
								data-toggle="" data-id="" data-target=""
								id="delete" value="delete" style="margin:0px 0px;" ></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			 <form method="post" id="deleteProjectForm" >
          		<input type="hidden" name="deleteProjectId" id="deleteProjectId"/>
          	</form>
			<!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Project</h4>
						</div>
						
						<form action="saaddprojectdetails" method="post">
						
<!--for edit or new entry purpose --> 
                         <input type="hidden"  name="projectId" id="projectId" value="0"/>
						
							<div class="modal-body">
								
								<div class="row">
									<label class="col-md-4">Project Name</label>
									<div class="col-md-8">
										<input class="form-control"  id="projectName" placeholder="Project Name"
											type="text" name="projectName" required>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-4">Project Description</label>
									<div class="col-md-8">
										 <textarea class="form-control" id="projectDescription" name="projectDescription" style="resize:none;" placeholder="Description" required></textarea>
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
		</div>
	</div></div>
	<jsp:include page="commonJsp/Footer.jsp" />
</body>
<script type="text/javascript">

$(document).on("click", "#edit", function () {
    var Id = $(this).data('id');
    $("#projectId").val(Id);
    $("#projectName").val($(this).data('name'));
    $("#projectDescription").val($(this).data('description'));
    $("#C-role-edit").show();
});

function deleteStudent(id){
	//deleteCluster
	$("#deleteProjectId").val(id);
	document.getElementById("deleteProjectForm").action = "deleteProject";
	document.getElementById("deleteProjectForm").method = "post";
	document.getElementById("deleteProjectForm").submit();
}

function clearForm(){
	document.getElementById("projectId").value = 0;
	document.getElementById("projectName").value = '';
	document.getElementById("projectDescription").value= '';
}
</script>





</html>