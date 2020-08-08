<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  <script src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
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

<head>
<title>Project Tracking Management</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<style>
.button {
  display: inline-block;
  padding: 15px 25px;
  font-size: 24px;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
}
.dropdown_margin_bottom{margin-bottom:15px;}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}


</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
function getallmodulesofthisprjct(val){
	document.getElementById("ddsubmodule2").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Choose Module<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].moduleid+">"+jsonData[i].modulename+"<oprion>";
			}
			document.getElementById("ddsubmodule2").innerHTML=data;
		}
	};
	xhttp.open("POST", "getallmodulesofthisproject?projectId="+val.value, true);
	xhttp.send();
}

function getallmodulesofthisproject(val){
	document.getElementById("ddmodule_2").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Choose Module<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].moduleid+">"+jsonData[i].modulename+"<oprion>";
			}
			document.getElementById("ddmodule_2").innerHTML=data;
		}
	};
	xhttp.open("POST", "getallmodulesofthisproject?projectId="+val.value, true);
	xhttp.send();
}

function getallsubmodulesofthismodule(val){
	document.getElementById("ddsubmodule3").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Choose SubModule<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].submoduleid+">"+jsonData[i].submodulename+"<oprion>";
			}
			document.getElementById("ddsubmodule3").innerHTML=data;
		}
	};
	xhttp.open("POST", "getallsubmodulesofthemodule?moduleId="+val.value, true);
	xhttp.send();
}
</script>

<script>

     $(document).ready(function(){
    	 
      
	  
	  $("#rbuser").click(function(){
	      if($(this).is(":checked"))
          {  
	    	 $("#generate_1").prop("disabled", true);
	    	 $("#generate_2").prop("disabled", true);
			 $("#generate3").prop("disabled", false);
		     $("#dd_project").prop("disabled", true);
			 $("#ddmodule_1").prop("disabled", true);
			 $("#ddmodule_2").prop("disabled", true);
			 $("#dduser").prop("disabled", false);	
			 $("#ddsubmodule1").prop("disabled", true);
			 $("#ddsubmodule2").prop("disabled", true);
			 $("#ddsubmodule3").prop("disabled", true);
			 $("#generate_4").prop("disabled", true);
		  }

	    });
	  
	  $("#rbsubmodule").click(function(){
	      if($(this).is(":checked"))
          {
	    	 $("#generate_1").prop("disabled", true);
	    	 $("#generate_2").prop("disabled", true);
			 $("#generate3").prop("disabled", true);
		     $("#dd_project").prop("disabled", true);
			 $("#ddmodule_1").prop("disabled", true);
			 $("#ddmodule_2").prop("disabled", true);
			 $("#dduser").prop("disabled", true);	
			 $("#ddsubmodule1").prop("disabled", false);
			 $("#ddsubmodule2").prop("disabled", false);
			 $("#ddsubmodule3").prop("disabled", false);
			 $("#generate_4").prop("disabled", false);
		  }

	    });
	  
	  
	   $("#rb_project").click(function(){
    	  if($(this).is(":checked"))
          {
	    	 $("#generate_1").prop("disabled", false);
	    	 $("#generate_2").prop("disabled", true);
			 $("#generate3").prop("disabled", true);
		     $("#dd_project").prop("disabled", false);
			 $("#ddmodule_1").prop("disabled", true);
			 $("#ddmodule_2").prop("disabled", true);
			 $("#dduser").prop("disabled", true);	
			 $("#ddsubmodule1").prop("disabled", true);
			 $("#ddsubmodule2").prop("disabled", true);
			 $("#ddsubmodule3").prop("disabled", true);
			 $("#generate_4").prop("disabled", true);
		  }


	    }); 
		
	  $("#rb_module").click(function(){
		  if($(this).is(":checked"))
          {
	    	 $("#generate_1").prop("disabled", true);
	    	 $("#generate_2").prop("disabled", false);
			 $("#generate3").prop("disabled", true);
		     $("#dd_project").prop("disabled", true);
			 $("#ddmodule_1").prop("disabled", false);
			 $("#ddmodule_2").prop("disabled", false);
			 $("#dduser").prop("disabled", true);	
			 $("#ddsubmodule1").prop("disabled", true);
			 $("#ddsubmodule2").prop("disabled", true);
			 $("#ddsubmodule3").prop("disabled", true);
			 $("#generate_4").prop("disabled", true);
		  }


	    });
	  
	});
</script>
</head>
<body>
<div class="affix" style="width:100%; z-index:99;" >
<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="superadminhome">Home</a></li>
					<li><a href="#">Excel Generation</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div></div>
<div class="container-fluid" style="padding-top:250px;min-height:738px;">
<div class="container">
<div class="panel panel-default" style="margin-top:50px;" >
<div class="panel-heading" style="background-color:#006780;color:white;"> Excel Generation</div>
<div class="row" style="padding:65px;">
 <div class="col-md-3">
<div class="panel panel-default" style="height:217px;" >
		    <div class="panel-heading" style="background-color:#006780;color:white;"> 
		        <input type="radio" name="rbproject" id="rb_project"> Project Wise</div>
		      <div class="panel-body">
		       <form action="superadminprojectwise" method="post">
		         <center>
		           <select class="dropdown_margin_bottom" id="dd_project" name="ddproject" disabled>
					    <option value="" selected>choose project</option>
		                <c:forEach items="${projects}" var="pro">
		                   <option value="${pro.projectId}">${pro.projectName}</option>
		                </c:forEach>
		           </select>
		         </center>
		         <center>
		           <button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:70px;" id="generate_1" disabled>Generate</button>
		         </center>
		       </form>
		      </div>
		   </div>
		  </div> 
 <div class="col-md-3">
			<div class="panel panel-default" style="height:217px;">
				<div class="panel-heading" style="background-color:#006780;color:white;">
					<input type="radio" name="rbproject" id="rb_module"> Module Wise</div>
				<div class="panel-body">
				<form action="superadminmodulewise" method="post">
					<center>
						 <select class="dropdown_margin_bottom" id="ddmodule_1" name="ddmodule1" disabled onchange="getallmodulesofthisproject(this)">
						  <option value="" selected>choose project</option>
					         <c:forEach items="${projects}" var="pro">
					            <option value="${pro.projectId}">${pro.projectName}</option>
					         </c:forEach>
				         </select>
			        </center>
			        <center>
				         <select class="dropdown_margin_bottom" id="ddmodule_2" name="ddmodule2" disabled style="width: 156px;">
						  <option value="0" selected>choose module</option>
				         </select>
			        </center>
			        <center>
			         	<button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:35px;" id="generate_2" disabled>Generate</button>
			        </center>
		        </form>
		       </div>
		  </div>
	  </div>
	  <div class="col-md-3">
		  <div class="panel panel-default" style="height:217px;">
		     <div class="panel-heading" style="background-color:#006780;color:white;">
		        <input type="radio" name="rbproject" id="rbuser"> User Wise</div>
		     <div class="panel-body">
		         <form action="superadminuserwise" method="post">
		         <center> 
		           <select class="dropdown_margin_bottom" id="dduser" name="dduser" disabled >
					     <option value="" selected>choose Employee</option>
				         <c:forEach items="${allusers}" var="user">
				            <option value="${user.userId}">${user.name}</option>
				         </c:forEach>
		           </select>
		         </center>
		         <center> 
		           <button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:70px;" id="generate3" disabled>Generate</button>
		         </center>
		         </form>
		    </div>
		 </div>
	    </div>
	    <div class="col-md-3">
       <div class="panel panel-default" style="height:217px;">
           <div class="panel-heading" style="background-color:#006780;color:white;">
              <input type="radio" name="rbproject" id="rbsubmodule"> Sub Module Wise</div>
                 <div class="panel-body">
                 <form action="superadminsubmodulewise" method="post">
                    <center> 
                       <select class="dropdown_margin_bottom" id="ddsubmodule1" name="ddsubmodule1" disabled onchange="getallmodulesofthisprjct(this)" style="width:147px;">
						 <option value="" selected>choose project</option>
				         <c:forEach items="${projects}" var="pro">
				            <option value="${pro.projectId}">${pro.projectName}</option>
				         </c:forEach>
                    </select>
                    </center>
                    <center>
                       <select class="dropdown_margin_bottom" id="ddsubmodule2" name="ddsubmodule2" disabled onchange="getallsubmodulesofthismodule(this)" style="width:147px;">
						  <option value="0" selected>choose module</option>
                       </select>
                       
                    </center>
                    <center>
                       <select class="dropdown_margin_bottom" id="ddsubmodule3" name="ddsubmodule3" disabled>
						  <option value="0" selected>choose submodule</option>
                        </select>
                    </center>
                    <center>
                      <button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:0px;" id="generate_4" disabled>Generate</button>
                    </center>
                    </form>
                </div>
		  </div>
		  </div>
		  </div>
		  </div></div>
		  </div>
		  	<jsp:include page="commonJsp/Footer.jsp" />
</body>
</html>
