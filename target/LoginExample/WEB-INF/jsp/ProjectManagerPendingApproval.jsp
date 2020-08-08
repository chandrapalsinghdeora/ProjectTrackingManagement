<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<%-- 
  <%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  if(session.getAttribute("sessionBean")==null)
      response.sendRedirect("/index.jsp");
  %>
 --%>
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
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #b5b5b5;
    text-align: left;
    padding: 12px !important;
}


</style>
<script type="text/javascript">

   function acceptapproval(userId){

	    document.getElementById("approve"+userId).action="approve";	
		document.getElementById("approve"+userId).method="post";
		document.getElementById("approve"+userId).submit();
   }
   
   function rejectapproval(userId){
	  
	    document.getElementById("reject"+userId).action="reject";	
		document.getElementById("reject"+userId).method="post";
		document.getElementById("reject"+userId).submit();
  }
 
</script>

</head>



<body>
<jsp:include page="commonJsp/Header.jsp" />
<div class="clearfix"></div>
<div class="breadcumb-wrapper">
            <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="pmhome">Home</a></li>
                                <li><a href="#">Project Manager</a></li>
                                        <li><a href="#">Approval Pending</a></li>
                    </ul>
                </div>
             
            </div>
        </div>   
         <div class="container-fluid" style="padding-top:30px;min-height:545px;">  
	
	
<div class="container">
 <div class="panel panel-default" style="margin-top:20px;margin-bottom:20px;" >
    <div class="panel-heading" style="background-color:#006780;color:white;font-size:19px;" > Approval Pending</div>
    <div class="panel-body"><table>
  <tr style="background-color:#ebebeb;">
    <th>User ID</th>
    <th>Name</th>
    <th>Technology</th>
    <th>Accept</th>
    <th>Reject</th>
    
  </tr>
<c:forEach items="${pendingapproval}" var="approval" varStatus="loop">
  <tr>
    <td>${approval.userId}</td>
    <td>${approval.name}</td>
   <td>${approval.empDesignation}</td>
   <td><form id="approve${approval.userId}" method="post">
							             	<input type="hidden" value="${approval.userId}" name="userid"/>
							          	</form>
						           		<input type="button" value="accept" onclick="acceptapproval(${approval.userId})"/></td>
						           		<td><form id="reject${approval.userId}" method="post">
							             	<input type="hidden" value="${approval.userId}" name="userid"/>
							          	</form>
						           		<input type="button" value="reject" onclick="rejectapproval(${approval.userId})"/>
	                              </td>
  </tr>
  
 
    </c:forEach>
</table>
   

</div>
  </div>
  </div>
	</div>
	
<jsp:include page="commonJsp/Footer.jsp" />
</body>



</html>