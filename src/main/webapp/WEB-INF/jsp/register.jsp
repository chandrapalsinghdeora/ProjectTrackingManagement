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
  max-width: 380px;
  padding: 15px 35px 30px 35px;
 margin-bottom:50px;
  background-color: #fff;
 margin-left: 30px;
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
</head>

 <script type="text/javascript">
       function validateit(){
    	   
    	   var password = document.getElementById("userPassword").value;
    	   var confirmpassword = document.getElementById("userConfirmPassword").value;
    	   if(password == confirmpassword)
    		   return true;
    	   else
    		   document.getElementById("message").innerHTML="Password do not match";
    		   return false;
       }
</script> 

  <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>

      <link rel="stylesheet" href="css/style.css">
      
      
<body>
 
<jsp:include page="commonJsp/Header.jsp"/>
                       
<div class="" style="padding-top:200px;padding-bottom:100px;">

        <div class="panel panel-primary" style="width:476px;border:none;margin-left:465px;">
      <div class="panel-heading"><b>Register</b></div>
      <div class="panel-body" >
        <c:if test="${empty sessionBean.roleId}">
          <form class="form-horizontal form-signin" action="sendtodb" method="post" onsubmit="return validateit()">  
        </c:if>
        <c:if test="${not empty sessionBean.roleId}">
          <form class="form-horizontal form-signin" action="updatedetails" method="post">
          <input type="hidden" value='${userId}' name="userid">
        </c:if>  
        
          <c:if test="${empty sessionBean.roleId}">
	      <h2 class="form-signin-heading">Please Register</h2>
	      </c:if>
	      
       	      
	      
<%-- 	      <c:if test="${empty sessionBean.roleId}">
		      <select class="form-control" name="role" id="role" required>
								<option value="0">Select Role</option>
								<option value="TL">Team Leader</option>
								<option value="EMP">Employee</option>
								
	          </select> <br>
          </c:if>          
 --%>
<%--           <c:if test="${not empty sessionBean.roleId}">
		      <select class="form-control" name="role" id="role" disabled> 
								<option value="${empDesignation}">${empDesignation}</option>
	          </select> <br>
          </c:if>  --%>
          
          <c:if test="${empty sessionBean.roleId}">
	      <b>Name</b><sup>*</sup><input type="text" class="form-control" id="name" name="name" placeholder="NAME" required autofocus/><br>
	      <b>UserName</b><sup>*</sup><input type="text" class="form-control" id="userName" name="userName" placeholder="USERNAME" required autofocus/><br>
	      <b>EmailId</b><sup>*</sup><input type="email" class="form-control" id="email" name="email" placeholder="EMAIL" required autofocus/><br>
          </c:if>
          
          <c:if test="${not empty sessionBean.roleId}">
	      <b>Name</b><input type="text" class="form-control" id="name" name="name" value="${name}" placeholder="NAME" autofocus/><br>
	      <b>UserName</b><input type="text" class="form-control" id="userName" value="${userName}" name="userName" placeholder="USERNAME" autofocus/><br>
	      <b>EmailId</b><input type="email" class="form-control" id="email" value="${email}" name="email" placeholder="EMAIL" autofocus/><br>
          </c:if>
          
          	      
	      <c:if test="${empty sessionBean.roleId}">
	        <b>Field</b><sup>*</sup>
	      <select class="form-control" name="empDesignation" id="empDesignation" required>
							<option value="0">Select Your Designation</option>
							<c:forEach items="${teamlist}" var="team">
								<option value="${team.empDesignation}">${team.empDesignation}</option>
							</c:forEach>
          </select> <br>
	      </c:if>
	      
	      <c:if test="${empty sessionBean.roleId}">
	        <b>Password</b><sup>*</sup><input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="PASSWORD" required autofocus/>
	      </c:if>
	      
	      <c:if test="${not empty sessionBean.roleId}">         
	        <b>Password</b><input type="text" class="form-control" id="userPassword" value="${password}" name="userPassword" placeholder="PASSWORD" autofocus/>
	      </c:if>
	      
	      <c:if test="${empty sessionBean.roleId}">
	      <b>Confirm Password</b><sup>*</sup><input type="password" class="form-control" id="userConfirmPassword" name="userConfirmPassword" placeholder="RE-ENTER PASSWORD" required autofocus/>
	      </c:if>
	     
	      <span id="message" style="color:red"></span>
	      <br>
	      <br>
	      
	      <c:if test="${empty sessionBean.roleId}">
		      
		        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button> 
	        
          </c:if>
          <c:if test="${not empty sessionBean.roleId}">
              
		        <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button> 
	         
          </c:if>
          
    </form>
    </div></div>
</div>
	<jsp:include page="commonJsp/Footer.jsp" />
</body>
</html>