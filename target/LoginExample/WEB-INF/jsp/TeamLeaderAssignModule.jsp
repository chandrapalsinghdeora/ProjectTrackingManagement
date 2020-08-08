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
<title>Project Tracking Management System</title>




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
<style>
body {
  background: #eee !important;
}

sup {
    vertical-align: super;
    font-size: 1.0em;
    color: red;
}

.wrapper {
  margin-top: 80px;
  margin-bottom: 80px;
}

.form-signin {

  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 30px;
}
.form-signin .checkbox {
  font-weight: normal;
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

</style>
<script>
function getAllModules(val){
	document.getElementById("moduleId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data= "<option value="+"0"+" selected>"+"choose one module"+"<oprion>" ;
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].modId+">"+jsonData[i].name+"<oprion>";
			}
			document.getElementById("moduleId").innerHTML=data;
		}
	};
	xhttp.open("POST", "getAllTHEModules?projectName="+val.value, true);
	xhttp.send();
}
</script>
<script>

function GetAllSubModules(val){
	document.getElementById("submoduleId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Assigned SubModule<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].pkId+">"+jsonData[i].name+"<oprion>";
			}
			document.getElementById("submoduleId").innerHTML=data;
		}
	};
	xhttp.open("POST", "GetAllTheSUBModules?moduleId="+val.value, true);
	xhttp.send();
}
</script>

</head>

 


      
      
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
						<li><a href="#">Assign SubModule</a></li>
					</ul>
				</div>
	
			</div>
		</div> 
    </div>                   
<div class="container-fluid" style="padding-top:250px;min-height:738px;">
       
   <div class="container">

    <div class="panel panel-primary" style="width:600px;border:none;margin-left:306px;">
      <div class="panel-heading">Assign SubModule</div>
      <div class="panel-body" style="padding: 0px 90px;">
         <form class="form-horizontal form-signin" action="assigntoemp" method="post"> 
		 <div class="form-group">
		 <label class="control-label">Project <sup>*</sup></label>
		 	      <select class="form-control" name="projectName" id="projectName" onchange="getAllModules(this);" required >
							<option value="0">Select The Project</option>
							<c:forEach items="${teamlist2}" var="team2">
								<option value="${team2.projectName}">${team2.projectName}</option>
							</c:forEach>
                  </select> 
		 
		 
 </div>
<div class="form-group">
<label class="control-label">   Modules<sup>*</sup></label>
    <select class="form-control" name="moduleId" id="moduleId" onchange="GetAllSubModules(this);"required>
	         <option value="0" selected>choose one module</option>
        </select>

 </div>		 
          <div class="form-group">
          <label class="control-label"> Sub Modules<sup>*</sup></label>
	 	      <select class="form-control" name="subModuleId" id="submoduleId"  required >
	      <option value="0" selected>Assigned SubModule</option>	
          </select> 
	      </div> 
	      <div class="form-group">
	      <label class="control-label"> Employees<sup>*</sup></label>
	  	      <select class="form-control" name="userId" id="userId" required >
							<option value="0">Select Employees</option>
							<c:forEach items="${employees}" var="emp">
								<option value="${emp.userId}">${emp.name}</option>
							</c:forEach>
          </select>
	     </div>
        
          <div class="form-group">
	      <label class="control-label">StartDate<sup>*</sup></label>
	      <input type="text" class="form-control" id="startdate" name="startDate" placeholder="MM/DD/YYYY" />
	      </div>
	       <div class="form-group">
	     <label class="control-label"> EndDate<sup>*</sup></label>
	      <input type="text" class="form-control" id="enddate" name="endDate" placeholder="MM/DD/YYYY" />
</div>
	     
		      <div class="form-group">
		        <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top:20px;margin-bottom:40px;">Assign</button> 
		        </div>
    </form>
      
      
      </div>
    </div>

</div>

</div>
<jsp:include page="commonJsp/Footer.jsp" />
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
	
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


  <script type="text/javascript">

$('#startdate').datepicker({
	  minDate: 0,
	  beforeShow: function() {
	    $(this).datepicker('option', 'maxDate', $('#enddate').val());
	  }
	});
	$('#enddate').datepicker({
	  defaultDate: "+1w",
	  beforeShow: function() {
	    $(this).datepicker('option', 'minDate', $('#startdate').val());
	    if ($('#startdate').val() === '') $(this).datepicker('option', 'minDate', 0);
	  }
	});
</script>

</body>
</html>