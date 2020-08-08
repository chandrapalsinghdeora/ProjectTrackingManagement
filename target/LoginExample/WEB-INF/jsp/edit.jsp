 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>Project Tracking Management</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->

<link href="css/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.min.css">

  <link href="css/style_bug.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />
  <style>
  #message{color:#fff;}
#message:hover{color:#006780;}
#dtable thead,
#dtable thead,
#dtable thead,
#dtable thead {  background-color: #006780; color: #fff; }
.bug_header{border-bottom:1px solid gray;}
.Edit_header{background: #006780;
color: white !important;
text-align: center;
font-size: 30px;}

</style>
</head>
 
 
 
 <script>

function GetAllModules(val){
	document.getElementById("nbmoduleName").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Modules<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].modId+">"+jsonData[i].modname+"<oprion>";
			}
			document.getElementById("nbmoduleName").innerHTML=data;
		}
	};
	xhttp.open("POST", "btGetallModules?projectId="+val.value, true);
	xhttp.send();
}


</script>
<script>

function GetAllSubModules(val){
	document.getElementById("nbsubmoduleId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>SubModules<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].submodId+">"+jsonData[i].submodname+"<oprion>";
			}
			document.getElementById("nbsubmoduleId").innerHTML=data;
		}
	};
	xhttp.open("POST", "btGetallSubModules?moduleId="+val.value, true);
	xhttp.send();
}
</script>

<script>

function GetAssignTo(val){
	document.getElementById("nbassignId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Assigned Employee<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].assignId+">"+jsonData[i].assignname+"<oprion>";
			}
			document.getElementById("nbassignId").innerHTML=data;
		}
	};
	xhttp.open("POST", "btGetAssigned?submoduleId="+val.value, true);
	xhttp.send();
}
</script>
 

       <div class="affix" style="width:100%;z-index:99" >
	<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="#">Home</a></li>
						<li><a href="btsendtodb">Bug Report By QA</a></li>
							<li>Edit</li>
					</ul>
				</div>
	
			</div>
		</div></div>
		
		<div class="container-fluid" style="padding:230px 0px 10px 0px;min-height:700px;">
         
     <div class="container">
     <div class="panel panel-default" style="margin-top:20px;" >
		    <div class="panel-heading" style="background-color:#006780;color:white;"> Bug Entry</div>
      <div class="panel-body"  margin-top:20px;">
      
      
       <form enctype="multipart/form-data" class="" id="approve"   >
          
          <div class=" row form-group">
          <div class="col-sm-3"></div>
          <div class="col-sm-2">
          <input type="hidden" name="bugNo" value="${bugnumber }">
          <label for="">Project <sup>*</sup> :</label></div>
          <div class="col-sm-4">
	      <select class="form-control" name="projectId" id="projectName"  onchange="GetAllModules(this);" required >
							<option value="" >Select The Project</option>
							<c:forEach items="${teamlist1}" var="team1">
								<option value="${team1.projectId}"
								${team1.projectId  == projectId ? 'selected' : ''}>
								${team1.projectName}</option>
							</c:forEach>
          </select></div>
          </div>	
          </br>
          
          
          <div class="row form-group">
           <div class="col-sm-3"></div>
          <div class="col-sm-2">
          <label for="">Modules<sup>*</sup> :</label></div>
          <div class="col-sm-4">
	      <select class="form-control" name="moduleId" id="nbmoduleName" onchange="GetAllSubModules(this);"   required >
				<option value="" selected>Assigned Module</option>
				<c:forEach items="${teamlist2}" var="team1">
								<option value="${team1.moduleId}"
								${team1.moduleId  == moduleid ? 'selected' : ''}>
								${team1.module}</option>
							</c:forEach>
						
          </select></div>
          </div>
          </br>
		  <div class="row form-group">
		   <div class="col-sm-3"></div>
		  <div class="col-sm-2">
         <label for=""> Sub Modules<sup>*</sup>:</label></div>
          <div class="col-sm-4">
	      <select class="form-control" name="subModuleId" id="nbsubmoduleId" onchange="GetAssignTo(this);" required >
	      <option value="" selected>Assigned SubModule</option>
	      <c:forEach items="${teamlist3}" var="team1">
								<option value="${team1.subModuleId}"
								${team1.subModuleId  == submoduleid ? 'selected' : ''}>
								${team1.subModule}</option>
							</c:forEach>	
          </select></div>
          </div>
            </br>
			 <div class="row form-group">
			  <div class="col-sm-3"></div>
			 <div class="col-sm-2">
			      <label for="">  Assigned To<sup>*</sup></label></div>
			        <div class="col-sm-4">
			           <select class="form-control" name="assignTo" id="nbassignId"  required >
			               <option value="0" selected>Assigned SubModule</option>
			               <c:forEach items="${teamlist4}" var="team1">
								<option value="${team1.userId}"
								${team1.userId  == assign ? 'selected' : ''}>
								${team1.name}</option>
							</c:forEach>		
			           </select></div>
             </div>
             </br>
	            <div class="row form-group">
	             <div class="col-sm-3"></div>
	            <div class="col-sm-2">
	              <label for="bug_name">Bug Name :</label></div>
	              <div class="col-sm-4">
	              <input type="text" class="form-control" id="bugName" name="bugName" value="${bugName}" placeholder="enter bug name">
	            </div></div>
          </br>
           
     		        	
         
         
          
       	  <div class="row form-group">
       	   <div class="col-sm-3"></div>
       	  <div class="col-sm-2">
           <label for=""> Bug Type :</label> </div>
                    <div class="col-sm-4">
			  <select class="form-control" Name="BugType" id="bugType" >
			  <option value="${bugType }">${bugType }</option>
			  <option value="Functionality Error">Functionality Error</option>
			  	  <option value="Communication Error">Communication Error</option>
			  	  	  <option value="Missing Command">Missing Command</option>
			  <option value="Syntactic Error">Syntactic Error</option>
			  <option value="Error Handling Error">Error Handling Error</option>
			<option value="Requirement">Requirement</option>
			<option value="Suggestions">Suggestions</option>
		      </select></div>
          </div></br>
           <div class="row form-group">
       	   <div class="col-sm-3"></div>
       	  <div class="col-sm-2">
           <label for=""> Bug Severity :</label> </div>
                    <div class="col-sm-4">
			  <select class="form-control" Name="BugSeverity" id="bugSeverity" >
			  <option value="${bugSeverity }">${bugSeverity }</option>
			  <option value="Critical">Critical</option>
			  <option value="Major">Major</option>
			  <option value="Medium">Medium</option>
			  <option value="low">Low</option>
			  </select>
		      </select></div>
          </div></br>
            <div class="row form-group">
             <div class="col-sm-3"></div>
            <div class="col-sm-2">
              <label for="">Bug Status:</label> </div>
                      <div class="col-sm-4">
			  <select class="form-control"  name="bugStatus" id="bugStatus" >
			  <option value="${bugStatus }">${bugStatus }</option>
			    <option value="New">New</option>
			  <option value="Open">Open</option>
			  <option value="Reopen">Reopen</option>
			  <option value="Close">Close</option>>
			  </select></div>
            </div>
          </br>
		  
          				

		   <div class="row form-group">
		    <div class="col-sm-3"></div>
		   <div class="col-sm-2">
	            <label for="bug discription">Bug Description</label> </div>
	                    <div class="col-sm-4">
		<textarea name="description" placeholder="description" rows="4" cols="49" style="resize:none;">${description}</textarea></div>
		</div>
		   </br>   
		      
		      <div class="row form-group">
		       <div class="col-sm-3"></div>
		      <div class="col-sm-2">
             <label for=""> Round<sup>*</sup></label></div>
                      <div class="col-sm-4">
	      <select class="form-control"  name="round" id="round" >
	       <option value="${round }">${round }</option>
			  <option value="1">1</option>
			  <option value="2">2</option>
			  <option value="3">3</option>
			  </select></div>
            </div>
				</br>
			
			<div class="row form-group">
			 <div class="col-sm-3"></div>
			<div class="col-sm-2">
              <label for="file_upload">File Upload</label></div>
                      <div class="col-sm-4">
			<input type="file" name="FileData"  id="id" size="40" onchange="checkFileTypeForPhoto(id)" placeholder="choose file">
			</div></div>
			</br>
			
			  <div class="row form-group">
			   <div class="col-sm-3"></div>
			  <div class="col-sm-2">
              <label>Depends</label></div>
              <div class="col-sm-4">
			 <input type="text" class="form-control" id="depends" name="depends" value="${depends}" placeholder="enter dependency">
			</div></div>
			</br>
          <div class="col-sm-3"></div>
               <div class="col-sm-3"><button onclick= "existing()" type="submit"  id="submit" class="btn  btn-block" style="background-color: #006780; color: #fff;">Submit As Existing Bug</button></div>
               <div class="col-sm-3"><button onclick= "bug()" type="submit"  id="submit" class="btn  btn-block" style="background-color: #006780; color: #fff;">Submit As New Bug</button></div>
			</form> 
    </div></div>
         </div>
			 
		</div>	
       
<jsp:include page="commonJsp/Footer.jsp" />
     

  <script>
function goBack() {
    window.history.back();
}
</script>
<script type="text/javascript">

   function existing(){

            document.getElementById("approve").action="btsendtoedit";        
                document.getElementById("approve").method="post";
                document.getElementById("approve").submit();
   }
</script>
<script type="text/javascript">

   function bug(){

            document.getElementById("approve").action="btsendtoor";        
                document.getElementById("approve").method="post";
                document.getElementById("approve").submit();
   }
</script>
<script>
function checkFileTypeForPhoto(id){
	 var fileName=document.getElementById(id).value;
	    var ext = $('#'+id).val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['jpeg','jpg','png']) == -1) {
	            alert("Only jpeg, jpg, png File are allowed to upload.")
	            document.getElementById(id).value="";
	    }else{
	            
	    }
}
</script>

  </html>