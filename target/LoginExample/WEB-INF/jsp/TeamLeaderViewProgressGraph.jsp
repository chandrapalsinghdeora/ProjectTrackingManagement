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

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<script>
function GetAllModules(val){
	document.getElementById("moduleId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>See Assigned Module<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].modId+">"+jsonData[i].name+"<oprion>";
			}
			document.getElementById("moduleId").innerHTML=data;
		}
	};
	xhttp.open("POST", "getAlltheModule?projectName="+val.value, true);
	xhttp.send();
}
</script>



</head>

<body>


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
					<li><a href="#">Graph Generation</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div></div>
<div class="container-fluid" style="padding-top:250px;min-height:738px;">
<div class="container">

 


	
	  
	  
     <div class="col-md-5">&nbsp;</div>
      <div class="col-md-3">
         <div class="panel panel-default" style="height:217px;margin-top:50px;">
            <div class="panel-heading" style="background-color:#006780;color:white;">
                Module Wise</div>
                  <div class="panel-body">
                    <form action="superadmingraphmodulewise" method="post">
                   <center>
                     <select class="dropdown_margin_bottom" id="ddmodule1" name="projectId" onchange="GetAllModules(this)">
						 <option value="" selected >choose project</option>
				         <c:forEach items="${teamlist2}" var="pro">
				            <option value="${pro.projectId}">${pro.projectName}</option>
				         </c:forEach>
                     </select>
                     </center>
					 <center>
					  <select class="dropdown_margin_bottom" id="moduleId" name="moduleId" style="margin-top:15px;">
					    <option value="0" selected>choose module</option>
                      </select>
                     </center>
                    <center>
                      <button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:35px;" id="generate2">Generate</button>
                    </center>
                    </form>
                  </div>
			  </div>
			  </div>
			
			</div></div>
				<jsp:include page="commonJsp/Footer.jsp" />
</body>



</body>
</html>
