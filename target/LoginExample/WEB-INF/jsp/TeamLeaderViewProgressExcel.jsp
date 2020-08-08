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

     $(document).ready(function(){
      $("#rbproject").click(function(){
	      if($(this).is(":checked"))
          {
		     $("#ddproject").prop("disabled",false);
		     $("#generate1").prop("disabled",false);
			 $("#ddmodule1").prop("disabled",true);
			 $("#ddmodule2").prop("disabled",true);
			 $("#generate2").prop("disabled",true);
			 $("#generate3").prop("disabled",true);
			 $("#dduser").prop("disabled",true);
			 $("#ddsubmodule1").prop("disabled",true);
			 $("#ddsubmodule2").prop("disabled",true);
			 $("#ddsubmodule3").prop("disabled",true);
			 $("#generate4").prop("disabled",true);
		  }

	    });
		
	  $("#rbmodule").click(function(){
	      if($(this).is(":checked"))
          {
		     $("#ddproject").prop("disabled", true);
		     $("#generate1").prop("disabled", true);
			 $("#ddmodule1").prop("disabled", false);
			 $("#ddmodule2").prop("disabled", false);
			 $("#generate2").prop("disabled", false);
			 $("#generate3").prop("disabled", true);
			 $("#dduser").prop("disabled", true);
			 $("#ddsubmodule1").prop("disabled", true);
			 $("#ddsubmodule2").prop("disabled", true);
			 $("#ddsubmodule3").prop("disabled", true);
			 $("#generate4").prop("disabled", true);
		  }

	    });
	  $("#rbsubmodule").click(function(){
	      if($(this).is(":checked"))
          {
		     $("#ddproject").prop("disabled", true);
		     $("#generate1").prop("disabled", true);
			 $("#ddmodule1").prop("disabled", true);
			 $("#ddmodule2").prop("disabled", true);
			 $("#generate2").prop("disabled", true);
			 $("#generate3").prop("disabled", true);
			 $("#dduser").prop("disabled", true);
			 $("#ddsubmodule1").prop("disabled", false);
			 $("#ddsubmodule2").prop("disabled", false);
			 $("#ddsubmodule3").prop("disabled", false);
			 $("#generate4").prop("disabled", false);
		  }

	    });
	  $("#rbuser").click(function(){
	      if($(this).is(":checked"))
          {
	    	 $("#generate1").prop("disabled", true);
	    	 $("#generate2").prop("disabled", true);
			 $("#generate3").prop("disabled", false);
		     $("#ddproject").prop("disabled", true);
			 $("#ddmodule1").prop("disabled", true);
			 $("#ddmodule2").prop("disabled", true);
			 $("#dduser").prop("disabled", false);
			 $("#ddsubmodule1").prop("disabled", true);
			 $("#ddsubmodule2").prop("disabled", true);
			 $("#ddsubmodule3").prop("disabled", true);
			 $("#generate4").prop("disabled", true);
		  }

	    });
	  
	});

</script>
<script>
function getAllTheModules(val){
	document.getElementById("ddmodule2").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data= "<option value="+"0"+" disabled selected>"+"choose one module"+"<oprion>" ;
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].modId+">"+jsonData[i].name+"<oprion>";
			}
			document.getElementById("ddmodule2").innerHTML=data;
		}
	};
	xhttp.open("POST", "getAlltheModule?projectName="+val.value, true);
	xhttp.send();
}
</script>
<script>
function getAllModules(val){
	document.getElementById("ddsubmodule2").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data= "<option value="+"0"+" disabled selected>"+"choose one module"+"<oprion>" ;
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].modId+">"+jsonData[i].name+"<oprion>";
			}
			document.getElementById("ddsubmodule2").innerHTML=data;
		}
	};
	xhttp.open("POST", "getAllModule?projectName="+val.value, true);
	xhttp.send();
}

</script>
<script>
function GetAllsubModules(val){
	document.getElementById("ddsubmodule3").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data= "<option value="+"0"+" disabled selected>"+"choose one submodule"+"<oprion>" ;
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].tlsubmodid+">"+jsonData[i].tlsubmodname+"<oprion>";
			}
			document.getElementById("ddsubmodule3").innerHTML=data;
		}
	};
	xhttp.open("POST", "getAllthesubModule?moduleId="+val.value, true);
	xhttp.send();
}
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
					<li><a href="tlhome">Home</a></li>
					<li><a href="tlhome">Team Leader</a></li>
					<li>Excel Generation</li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	</div>
	
<div class="container-fluid" style="padding-top:250px;min-height:738px;">

<div class="container">
 <div class="panel panel-default" style="margin-top:20px;min-width:600px;" >
		    <div class="panel-heading" style="background-color:#006780;color:white;"> Excel Generation</div>
		     <div class="panel-body">
 <div class="row" style="padding:65px;">
 
		<%--  <div class="col-md-3">
		  <div class="panel panel-default" style="height:217px;" >
		    <div class="panel-heading" style="background-color:#006780;color:white;"> 
		        <input type="radio" name="rbproject" id="rbproject"> Project Wise</div>
		      <div class="panel-body">
		       <form action="generate" method="post">
		         <center>
		           <select class="dropdown_margin_bottom" id="ddproject" name="ddproject" disabled>
					    <option value="" selected>choose project</option>
		                <c:forEach items="${teamlist2}" var="pro">
		                   <option value="${pro.projectId}">${pro.projectName}</option>
		                </c:forEach>
		           </select>
		         </center>
		         <center>
		           <button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:70px;" id="generate1" disabled>Generate</button>
		         </center>
		       </form>
		      </div>
		   </div>
		  </div> --%>
 <div class="col-md-1"></div>
		<div class="col-md-3">
			<div class="panel panel-default" style="height:217px;">
				<div class="panel-heading" style="background-color:#006780;color:white;">
					<input type="radio" name="rbproject" id="rbmodule"> Module Wise</div>
				<div class="panel-body">
				<form action="moduleexcel" method="post">
					<center>
						 <select class="dropdown_margin_bottom" id="ddmodule1" name="projectId"  onchange="getAllTheModules(this)" disabled>
						  <option value="" selected>choose project</option>
					         <c:forEach items="${teamlist2}" var="pro">
					            <option value="${pro.projectId}">${pro.projectName}</option>
					         </c:forEach>
				         </select>
			        </center>
			        <center>
				         <select class="dropdown_margin_bottom" id="ddmodule2" name="moduleId" disabled>
						  <option value="0" selected>choose module</option>
				         </select>
			        </center>
			        <center>
			         	<button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:35px;" id="generate2" disabled>Generate</button>
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
		         <form action="userexcel" method="post">
		         <center> 
		           <select class="dropdown_margin_bottom" id="dduser" name="userId" disabled >
					     <option value="" selected>choose Employee</option>
				         <c:forEach items="${teamlist1}" var="user">
				            <option value="${user.userId}">${user.userName}</option>
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
                   <form action="submoduleexcel" method="post">
                    <center> 
                       <select class="dropdown_margin_bottom" id="ddsubmodule1" name="projectId" disabled onchange="getAllModules(this);" style="width:147px;">
						 <option value="" selected>choose project</option>
				         <c:forEach items="${teamlist2}" var="pro">
				            <option value="${pro.projectId}">${pro.projectName}</option>
				         </c:forEach>
                    </select>
                    </center>
                    <center>
                       <select class="dropdown_margin_bottom" id="ddsubmodule2" name="moduleId" disabled onchange="GetAllsubModules(this);" style="width:147px;">
						  <option value="0" selected>choose module</option>
                       </select>
                    </center>
                    <center>
                       <select class="dropdown_margin_bottom" id="ddsubmodule3" name="subModuleId" disabled>
						  <option value="0" selected>choose submodule</option>
                        </select>
                    </center>
                    <center>
                      <button type="submit" class="btn btn-default" style="background-color:#006780;color:white;" id="generate4" disabled>Generate</button>
                    </center>
                    </form>
                </div>
		  </div>
		  </div>
		   <div class="col-md-1"></div>
		  </div>
		  
		  </div>
		  </div>
</div>
</div>
<jsp:include page="commonJsp/Footer.jsp" />
</body>
</html>
