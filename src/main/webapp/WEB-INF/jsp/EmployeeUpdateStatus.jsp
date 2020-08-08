
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
	
	<link href="http://cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.css" rel="stylesheet"/>
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

function GetAllSubModules(val){
	document.getElementById("submoduleId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Assigned SubModule<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++)
				for(var j=0;j<jsonData[i].length;j++){
					data+="<option value="+jsonData[i][j].submodid+">"+jsonData[i][j].submodname+"<oprion>"
			    }
			document.getElementById("submoduleId").innerHTML=data;
		}
	};
	xhttp.open("POST", "GetallSubModules?moduleId="+val.value, true);
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
			for(var i=0;i<jsonData.length;i++)
				for(var j=0;j<jsonData[i].length;j++){
					data+="<option value="+jsonData[i][j].modid+">"+jsonData[i][j].modname+"<oprion>"
			    }
			document.getElementById("moduleName").innerHTML=data;
		}
	};
	xhttp.open("POST", "GetallModules?projectName="+val.value, true);
	xhttp.send();
}

</script>
<script>
function GetAllTasks(val){
	document.getElementById("taskId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Assigned Task<oprion>";
			var jsonData=JSON.parse(this.responseText);
			 if(($(jsonData).length) == 0)
	          {
			     $("#taskId").prop("disabled", true);
	          }
			 else
				 {
			for(var i=0;i<jsonData.length;i++)
				{
				$("#taskId").prop("disabled", false);
					data+="<option value="+jsonData[i].taskid+">"+jsonData[i].taskname+"<oprion>"
			    }
				 }
				 
			document.getElementById("taskId").innerHTML=data;
		}
	};
	xhttp.open("POST", "GetallTasks?submoduleId="+val.value, true);
	xhttp.send();
}

</script>

</head>

 

  <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>

      <link rel="stylesheet" href="css/style.css">
      
      
<body>
 <div class="affix" style="width:100%;z-index:99;" >
		<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="#">Home</a></li>
						
						<li><a href="#">Update Status</a></li>
					</ul>
				</div>
	
			</div>
		</div> 
    </div>                   
<div class="container-fluid" style="padding-top:250px;min-height:738px;">
       
   <div class="container">

    <div class="panel panel-primary" style="width:600px;border:none;margin-left:306px;padding: 0px 0px 34px 0px;">
      <div class="panel-heading">Update Status</div>
      <div class="panel-body" style="padding: 18px 90px;">
        
        <form class="form-signin" action="addentrytotable" method="post">  

        
          Project<sup>*</sup>
	      <select class="form-control" name="projectName" id="projectN"  onchange="GetAllModules(this);" required >
							<option value="0">Select The Project</option>
							<c:forEach items="${teamlist1}" var="team1">
								<option value="${team1.projectName}">${team1.projectName}</option>
							</c:forEach>
          </select> <br>
          	
          
          Modules<sup>*</sup>
	      <select class="form-control" name="moduleId" id="moduleName" onchange="GetAllSubModules(this);"   required >
				<option value="0" selected>Assigned Module</option>			
          </select> <br>
          
          Sub Modules<sup>*</sup>
	      <select class="form-control" name="subModuleId" id="submoduleId" onchange="GetAllTasks(this);" required >
	      <option value="0" selected>Assigned SubModule</option>	
          </select> <br>
          
           Task<sup>*</sup>
	      <select class="form-control" name="taskId" id="taskId"  required >
	      <option value="0" selected>Assigned Task</option>	
          </select> <br>
          
          Progress<sup>*</sup>
	      <select class="form-control" name="isCompleted" id="isCompleted"  required >
	          <option value="" selected>choose progress</option>
	          <option value= 0 >Not Started</option>
	          <option value= 1 >Currently Working</option>
	          <option value= 2 >Completed</option>	
          </select> <br>
          
          
	      Hours<sup>*</sup>
			<div>
			  <input type="text" class="form-control" id="time" name="time" placeholder="HH:MM" disabled>
			</div> <br>
	      
	      
	      <textarea class="form-control" name="subModuleDescriptionIndividually" id="subModuleDescription" placeholder="Description" required disabled></textarea> <br><br>   
	      
	      
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button> 
      
         </form>
      </div>
    </div>

</div>

</div>
<jsp:include page="commonJsp/Footer.jsp" />
<script src="http://cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.js"></script>


<script>
var timepicker = new TimePicker('time', {
	  lang: 'en',
	  theme: 'dark'
	});
	timepicker.on('change', function(evt) {
	  
	  var value = (evt.hour || '00') + ':' + (evt.minute || '00');
	  evt.element.value = value;

	});
	
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	
 $(document).ready(function(){
	      $("#isCompleted").change(function(){
	    	  if($(this).val() == 0)
	          {
			     $("#time").prop("disabled", true);
				 $("#subModuleDescription").prop("disabled", true);
	          }
	    	  else
	    		  {
	    		     $("#time").prop("disabled", false);
					 $("#subModuleDescription").prop("disabled", false);
	    		  }
		    });
		});
</script>



</body>
</html>

 