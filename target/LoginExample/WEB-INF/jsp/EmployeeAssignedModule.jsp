
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
<title>Project Tracking Management System</title>

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
	  xhttp.open("GET", "allassignedmodules?moduleId="+val.value, true);               //  for the controller accepting url of AJAX
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
				data+="<option value="+jsonData[i].pkId+">"+jsonData[i].name+"<oprion>";
			}
			document.getElementById("moduleName").innerHTML=data;
		}
	};
	xhttp.open("POST", "GetAllModules?projectName="+val.value, true);
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
                    </ul>
                </div>
             
            </div>
        </div>  
                       
<div class="wrapper">
        
        
          <div class="form-signin" >  
        
        
	      
          Project<sup>*</sup>
	      <select class="form-control" name="projectName" id="projectN"  onchange="GetAllModules(this);" required >
							<option value="0">Select The Project</option>
							<c:forEach items="${teamlist1}" var="team1">
								<option value="${team1.projectName}">${team1.projectName}</option>
							</c:forEach>
          </select> <br>
          	
          
          Modules<sup>*</sup>
	      <select class="form-control" name="moduleName" id="moduleName"  onchange="AllSubModules(this);" required >
				<option value="0" selected>See Assigned Module</option>			
          </select> <br>
          </div>	   
             
	      <div id="assignedmodules">
	         
	      </div>
          
	       
	      <span id="message" style="color:red"></span>
	      <br>
	      <br>
	      
	     
		      
          
         
          
    </form>
</div>

</body>
</html>

 