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
	<style>
	.left_margin{margin-left:0px;}
	.wrapper {
  margin-top: 80px;
  margin-bottom: 80px;
}
.btn {
margin-top: 37px;
}
.form-signin {
  max-width: 380px;
  padding: 55px 35px;
  margin: 0 auto;
  background-color: #fff;
 height:370px;
}
textarea.form-control {
    height: auto;
    }
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 30px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 20px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

.button_width{margin-left:80px;}
.modal-footer p{color:#fff;}
.modal-footer p a{color:#fff;}
.pull_right{float:right;}
.pull_left{float:left;}
.breadcumb-wrapper{ background-color:#006780 !important;}
	</style>
	<script>

	function getmodules(val){
		document.getElementById("moduleId").innerHTML="";
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var data="<option value="+"0"+" selected>See Module<oprion>";
				var jsonData=JSON.parse(this.responseText);
				for(var i=0;i<jsonData.length;i++){
					data+="<option value="+jsonData[i].modId+">"+jsonData[i].modName+"<oprion>";
				}
				document.getElementById("moduleId").innerHTML=data;
			}
		};
		xhttp.open("POST", "sagetallmodules?projectId="+val.value, true);
		xhttp.send();
	}
</script>
	<body>
	<div class="affix" style="width:100%;" >
		<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="superadminhome">Home</a></li>
						<li><a href="#">Sub Module</a></li>
					</ul>
				</div>
	
			</div>
		</div>
		</div>
		<div class="container-fluid" style="padding:200px 0px 100px 0px;min-height:738px;">
	
	<input type="button" class="btn btn-primary pull-right"
					data-toggle="modal" data-target="#addmodal2" value="Add Submodule" style="margin-right:126px;">
 <div class="modal fade" id="addmodal2" role="dialog" >
    <div class="modal-dialog">
	 <div class="modal-content">
        <div class="modal-header" style="padding:15px 25px; background-color:#006780;color:white;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h4>Add Sub Module</h4>
        </div>
        <div class="modal-body" style="padding:20px 25px;height:600px;overflow-y:scroll;" >
          <div class="container" style="width:515px;">
		<div class="row"></div>
	<form class="" id="savethemodules" method="post" >
				<div class="border">
					<div class="col-sm-2" style="margin-top:5px;"><b>Project</b></div>
					<div class="col-sm-4">
						<select class="form-control" name="projectId" id="projectId" onchange="getmodules(this);">
							<option value="0">select</option>
							<c:forEach items="${projectlist}" var="project">
								<option value="${project.projectId}">${project.projectName}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-sm-2" style="margin-top:5px;"><b>Module</b></div>
					<div class="col-sm-4">
						<select class="form-control" name="moduleId" id="moduleId" >
							<option value="0" selected>See Module</option>	
						</select>
					</div>

				</div>
	

	<div class="panel panel-primary" >
					<div class="panel-heading">Project Sub Modules</div>
	<div id="interviewbox">
						<div class="panel-body">
							<div class="form-group">
								<div class="col-sm-3">Sub Module Name:</div>
								<div class="col-sm-9">
     								<input rows="2" class="form-control" type="text" name="subModuleName" id="modulename">
								</div>
							</div>
							</br>
		<div class="form-group ">
								<div class="col-sm-3 ">Sub Module Description:</div>
								<div class="col-sm-9">
									<textarea rows="4" col="10" style="resize:none;width:330px;" class="form-control" name="subModuleDescription"
										id="moduledescription"></textarea>
								</div>
							</div>
						</div>
					</div>
					</div>
				<div id="interviewId"></div>
			</form>
	<div class="pull_right">
				<button class="btn btn-primary " id="add-panel">Add Sub Module</button>
				<button class="btn btn-primary" id="remove-panel">Remove  Sub Module</button>
				</div>
	<div class="space"></div>
			<div class="pull_left">
				<input type="button" value="Submit" onclick="saveit()" class="btn btn-primary" /> 
				<input type="button" value="Cancel"	class="btn btn-primary" />
			</div>
	</div>
			
  </div>
        <div class="modal-footer" style="background-color:#006780;">
          <button type="submit" class="btn btn-default pull-left" data-dismiss="modal" style="margin:5px 0px;"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
          
 </div>
      </div>
      
    </div>
  </div> 
  
  
  <!-- ----------------------------------------------------------------- -->
  
  
  			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Update SubModule</h4>
						</div>
						
						<form id="updateindividualsubmodulel" method="post">
						
<!--for edit or new entry purpose --> 
                         <input type="hidden"  name="subModuleId" id="subModuleId" value="0"/>
						
							<div class="modal-body">
								
								<div class="row">
									<label class="col-md-4">SubModule Name</label>
									<div class="col-md-8">
										<input class="form-control"  id="subModuleNameIndividuallyl" placeholder="SubModule Name"
											type="text" name="subModuleNameIndividually" required>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-4">Project Description</label>
									<div class="col-md-8">
										 <textarea class="form-control" id="subModuleDescriptionIndividuallyl" name="subModuleDescriptionIndividually" style="resize:none;" placeholder="Description" required></textarea>
									</div>
								</div>
						
							</div>
							<div class="modal-footer">
								<button type="submit" onclick="updateit()" class="btn btn-default">Update</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
							</div>
						</form>
					</div>
				</div>
			</div>
  
  
  <!-- ------------------------------------------------------------------ -->

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
		                   <th>Sub Module Name</th>
		                   <th>Sub Module Description</th>
		                   <th>Action Edit</th>
		                   <th>Action Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${submodulelist}" var="submod" varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							<td>${submod.projectName}</td>
							<td>${submod.moduleNameIndividually}</td>
							<td>${submod.subModuleNameIndividually}</td>
							<td>${submod.subModuleDescriptionIndividually}</td>
							<td><input type="button" class="btn btn-primary"
								data-submodname="${submod.subModuleNameIndividually}" data-submoddescription="${submod.subModuleDescriptionIndividually}"
								data-toggle="modal" data-id="${submod.subModuleId}" data-target="#C-role-edit"
								id="edit" value="Edit" style="margin:0px 0px;"></td>
							<td><input type="button" onclick="deleteStudent(${submod.subModuleId})" class="btn btn-primary "data-name="" data-description=""
								data-toggle="" data-id="" data-target=""
								id="delete" value="delete" style="margin:0px 0px;" >
						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<form method="post" id="deleteSubModuleForm" >
          		<input type="hidden" name="deleteSubModuleId" id="deleteSubModuleId"/>
          	</form>
			<!-- / panel preview -->

		</div>
	</div>
	</div>
	<jsp:include page="commonJsp/Footer.jsp" />
	
	

	

		<script>
$(document).on("click", "#edit", function () {
    var Id = $(this).data('id');
    $("#subModuleId").val(Id);
    $("#subModuleNameIndividuallyl").val($(this).data('submodname'));
    $("#subModuleDescriptionIndividuallyl").val($(this).data('submoddescription'));
    $("#C-role-edit").show();
});

function deleteStudent(id){
	$("#deleteSubModuleId").val(id);
	document.getElementById("deleteSubModuleForm").action = "deletesubmodule";
	document.getElementById("deleteSubModuleForm").method = "post";
	document.getElementById("deleteSubModuleForm").submit();
}
</script>
	

	 
	</body>


	<script>

	    function saveit(){
			document.getElementById("savethemodules").action = "sasavesubmodules";
			document.getElementById("savethemodules").method = "post";
			document.getElementById("savethemodules").submit();
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
		    
				if(x<50)
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