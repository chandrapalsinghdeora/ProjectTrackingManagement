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

<script type="text/javascript">

   function acceptrole(userId){

	    document.getElementById("accept"+userId).action="approverole";	
		document.getElementById("accept"+userId).method="post";
		document.getElementById("accept"+userId).submit();
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
						<li><a href="pmhome">Home</a></li>
						<li><a href="#">Project Manager</a></li>
							<li><a href="#">Role Pending</a></li>
					</ul>
				</div>
	
			</div>
		</div></div>

     
     
 <div class="container-fluid" style="padding:200px 0px 100px 0px;min-height:738px;">     
	<div class="container">
		<div class="row">
			<!-- panel preview -->
			<div class="space"></div>
			<div class="container-fluid btn-container">

				<div class="clearfix"></div>
					<table id="main-fourum" class="table table-striped table-bordered">
						<thead>
							<tr>
								   <th>S.No.</th>
				                   <th>Name</th>
				                   <th>Technology</th>
				                   <th>Role</th>
								   <th>Accept</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roleapproved}" var="approval" varStatus="loop">
								<tr>
								    <td>${loop.index+1}.</td>
									<td>${approval.name}</td>
									<td>${approval.empDesignation}</td>
								  
									<td>
								      <form id="accept${approval.userId}" method="post">
									  <select name="roleid">
									    <option value="0">Choose One</option>
									    <option value="2">Team Leader</option>
                                        <option value="3">Employee</option>
                                        <option value="5">Q.A.</option>
									  </select>
									  
									  <input type="hidden" value="${approval.userId}" name="userid"/>
							          </form>
									</td>
								  
			 	                    <td>
						           		<input type="button" class="btn btn-primary" value="accept" onclick="acceptrole( ${approval.userId} )" />
	                                </td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
		</div>
	</div>
</div></div>

	<jsp:include page="commonJsp/Footer.jsp" />
</body>



</html>