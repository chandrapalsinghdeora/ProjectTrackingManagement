
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





</head>


      
<body>
 <div class="affix" style="width:100%;" >
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="#">Home</a></li>
		
					<li><a href="#">Assign Submodule</a></li>
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
		                   <th>SubModule Name</th>
		                   <th>SubModule Description</th>
						   <th>Start Date</th>
						   <th>End Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${projectlist }" var="project" varStatus="loop">
					<tr>
					<td>${loop.index+1 }</td>
					<td>${project.projectName }</td>
					<td>${project.moduleNameIndividually }</td>
					<td>${project.subModuleNameIndividually }</td>
					<td>${project.subModuleDescriptionIndividually }</td>
					<td>${project.startDate }</td>
					<td>${project.endDate }</td>
					</tr>
					
					</c:forEach>
				</tbody>
			</table>      

     </div>     
         
          

	<jsp:include page="commonJsp/Footer.jsp" />


</body>
</html>

 