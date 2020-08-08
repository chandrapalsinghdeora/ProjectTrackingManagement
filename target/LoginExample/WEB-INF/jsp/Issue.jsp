
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>Project Tracking Management</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->

<link href="css/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.min.css">

   <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
  <link href="css/style_bug.css" rel="stylesheet" />
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>

<style>
.border_box{border:1px solid #000000;}
.padding_box{padding:3px 3px 3px 3px;}
.margin_left{margin-left:20px;}
.content_inner{min-height: 637px !important;height:100%; padding:50px 50px 50px 50px;}
.margin_panel{margin:20px 0px 20px 0px; }
.panel_content{min-height: 500px !important;height:100%;}
.panel-body{height:75px; word-wrap: break-word; white-space:inherit;}
.bug_header{margin-top:2px;}
</style>
</head>




<body >
<div class="affix" style="width:100%;z-index:99;" >
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="#">Home</a></li>
				<li><a href="#">Bug Report</a></li>
						<li><a href="#">Issues</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	  <nav class="navbar bug_header head_color">
  <div class="container-fluid">
  
    <div class="collapse navbar-collapse" id="myNavbar">
	 <ul class="nav navbar-nav navbar-left">
	    <li><h3 class="head_margin"><b>ISSUE</b></h3></li>

      </ul>
    </div>
  </div>
  
</nav>
	</div>
	
<div class="container-fluid" style="padding-top:250px;min-height:738px;">	


 
<div class="content_inner">    
 <div class="container">
 <div class="panel_content">
<div class="row margin_panel">
  <div class="col-lg-4">
  <div class="panel panel-default">
   
    <div class="panel-heading"><b>QA NAME</b></div>
    <div class="panel-body"><label>${qa_name}</label></div>
  </div>
  </div>
  
  
   <div class="col-lg-4">
  <div class="panel panel-default">
    <div class="panel-heading"><b>MONITOR BY</b></div>
    <div class="panel-body"><label>${name}</label></div>
  </div>
  </div>
  
   <div class="col-lg-4">
  <div class="panel panel-default">
  
    <div class="panel-heading"><b>ASSIGN TO</b></div>
    <div class="panel-body"><label>${assign_to}</label></div>
    
  </div>
  </div>
  </div>
  
  <div class="row margin_panel">
  <div class="col-lg-4">
  <div class="panel panel-default">
  <div class="panel-heading"><b>MODULE</b></div>
    <div class="panel-body"><label>${modname}</label></div>
  </div>
  </div>
  <div class="col-lg-4">
  <div class="panel panel-default">
    <div class="panel-heading"><b>SUB-MODULE</b></div>
    <div class="panel-body"><label>${subModulename}</label></div>
  </div>
  </div>
  
   <div class="col-lg-4">
  <div class="panel panel-default">
    <div class="panel-heading"><b>ROUND</b></div>
    <div class="panel-body"><label>${round}</label></div>
  </div>
  </div>
  </div>
  
  <div class="row margin_panel">
  <div class="col-lg-4">
  <div class="panel panel-default">
    <div class="panel-heading"><b>DESCRIPTION</b></div>
 
     <div class="panel-body" style="overflow-y:scroll;"><label>${description}</label></div>
  </div>
  </div>
  
   <div class="col-lg-4">
  <div class="panel panel-default">
    <div class="panel-heading"><b>DEPENDS</b></div>
    <div class="panel-body"><label>${depends}</label></div>
  </div>
  </div>
  
   <div class="col-lg-4">
  <div class="panel panel-default">
    <div class="panel-heading"><b>DATE</b></div>
    <div class="panel-body"><label>${date}</label></div>
  </div>
  </div>
  </div>
  </div>
   <div >
   <label class="float_left"><b>Download File</b></label>
   <form action ="downloadphotoUploadFile" METHOD="post">
   <button class="float_left margin_left" type="submit" id="download"   class="btn btn-primary" style="background: #006780;color:#fff;">Download</button>
   </form>
   <label style="color:red">${message}</label>
 <button onclick="goBack()" class="float_right" type="submit" id="goback" class="btn btn-primary" style="background: #006780;color:#fff;">Go Back</button>
   </div>
</div>
  </div>
 </div>
 
 	<jsp:include page="commonJsp/Footer.jsp" />
  <script>
function goBack() {
    window.history.back();
}
</script>
  
  
  
  
  

</body>
</html>