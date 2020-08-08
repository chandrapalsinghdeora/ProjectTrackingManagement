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
					<li><a href="#">Super Admin</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	</div>
<div class="container-fluid" style="padding-top:250px;min-height:738px;">	
<div class="container">
 <div class="panel panel-default" style="margin-top:20px;" >
    <div class="panel-heading" style="background-color:#006780;color:white;font-size:19px;" > List of Finished Projects</div>
    <div class="panel-body"><table>
  <tr style="background-color:#ebebeb;">
    <th>S.No.</th>
    <th>Finished Projects</th>
    
  </tr>

   <c:forEach items="${projects}" var="pro" varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							
							<td>${pro.projectName}</td>
   
  </tr>
  </c:forEach>
  <!-- <tr>
    <td>2</td>
    <td>Roland Mendel</td>
    
  </tr> -->
 
    
</table>
   

</div>
  </div>
  </div>
	</div>
	

	<jsp:include page="commonJsp/Footer.jsp" />
</body>





</html>