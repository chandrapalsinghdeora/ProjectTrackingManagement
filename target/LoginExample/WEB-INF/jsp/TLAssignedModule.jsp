
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

</style>
<script>

function AllSubModules(val){
	var xhttp;
	if(val.value == ""){
		document.getElementById("assignedmodules").innerHTML="";
	    return;
	}
	
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("assignedmodules").innerHTML = this.responseText;
		}
	};
	
	//xhttp.open("GET", "show.jsp?q="+Str, true);             //  for the another jsp page accepting url of AJAX
	  xhttp.open("GET", "alltlassignedmodules?moduleId="+val.value, true);               //  for the controller accepting url of AJAX
	  xhttp.send();
}
</script>
<script>

function GetAllModules(val){
	document.getElementById("moduleName").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>See Assigned Module<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].modId+">"+jsonData[i].name+"<oprion>";
			}
			document.getElementById("moduleName").innerHTML=data;
		}
	};
	xhttp.open("POST", "getAllModules?projectName="+val.value, true);
	xhttp.send();
}

</script>
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
	  xhttp.open("GET", "getthemodules?projectId="+val.value, true);               //  for the controller accepting url of AJAX
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
					<li><a href="#">Assign Project</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div></div>                
	<div class="container-fluid" style="padding:250px 50px 100px 50px;min-height:738px;">
  		
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
					
		                  <th>Sr Name</th>
		                  <th>Project Name</th>
		                   <th>Module Name</th>
		                   <th>Module Description</th>
						   <th>Start Date</th>
						   <th>End Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamlist1 }" var="teamlist" varStatus="loop">
					<tr>
					<td>${loop.index+1 }</td>
					<td>${teamlist.projectName }</td>
					<td>${teamlist.moduleNameIndividually }</td>
					<td>${teamlist.moduleDescriptionIndividually }</td>
					<td>${teamlist.startDate }</td>
					<td>${teamlist.endDate }</td>
					</c:forEach>
				</tbody>
			</table>      

     </div>     
         
          

	<jsp:include page="commonJsp/Footer.jsp" />
</body>
</html>

 