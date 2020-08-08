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
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#startdate" ).datepicker();
    $( "#enddate" ).datepicker();
  } );
  </script>


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
  max-width: 380px;
  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.1);
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

</style>

<script>

function getModules(val){
	var xhttp;
	if(val.value == ""){
		document.getElementById("modules").innerHTML="";
	    return;
	}
	
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("modules").innerHTML = this.responseText;
		}
	};
	
	//xhttp.open("GET", "show.jsp?q="+Str, true);             //  for the another jsp page accepting url of AJAX
	  xhttp.open("GET", "getmodules?projectId="+val.value, true);               //  for the controller accepting url of AJAX
	  xhttp.send();
}
</script>

</head>

 

  <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>

      <link rel="stylesheet" href="css/style.css">
      
      
<body>
 
<div class="breadcumb-wrapper">
            <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="#">Home</a></li>
                         <li><a href="#">Assigned Project</a></li>
                    </ul>
                </div>
             
            </div>
        </div>  
                       
<div class="wrapper">
        
            <div class="form-signin" >  

          Project<sup>*</sup>
	      <select class="form-control" name="projectId" id="projectId" onchange="getModules(this);" required>
							<option value="0">click to see</option>
							<c:forEach items="${assignedprojects}" var="assigned">
								<option value="${assigned.projectId}">${assigned.projectName}</option>
							</c:forEach>
          </select> <br>	     
          </div> 
          
          <div id="modules">  </div>
<%-- 	      
	      Designation<sup>*</sup>
	      <select class="form-control" name="empDesignation" id="empDesignation" onchange="getTeamLeaderValues(this);" required>
							<option value="0">Select Your Designation</option>
							<c:forEach items="${teamlist1}" var="team1">
								<option value="${team1.empDesignation}">${team1.empDesignation}</option>
							</c:forEach>
          </select> <br>
	     

	     
	       Team Leader<sup>*</sup>
	      <select class="form-control" name="userId" id="userId" required>
	      
          </select> <br>
        
          
	      StartDate<sup>*</sup>
	      <input type="text" class="form-control" id="startdate" name="startDate" placeholder="MM/DD/YYYY" /><br>
	      EndDate<sup>*</sup>
	      <input type="text" class="form-control" id="enddate" name="endDate" placeholder="MM/DD/YYYY" /><br>
 --%>
	      <br>
	      <br>


</div>

</body>
</html>