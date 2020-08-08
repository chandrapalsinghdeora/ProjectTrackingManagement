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

  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 30px;
}
.form-signin .checkbox {
  font-weight: normal;
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

 


      
      
<body>

		<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="superadminhome">Home</a></li>
						<li><a href="#">Assign Project</a></li>
					</ul>
				</div>
	
			</div>
		</div> 
    </div>                   
<div class="container-fluid" style="padding-top:80px;min-height:705px;">
       
   <div class="container">

    <div class="panel panel-primary" style="width:600px;border:none;margin-left:306px;">
      <div class="panel-heading">Assign Project</div>
      <div class="panel-body" style="padding: 0px 90px;">
         <form class="form-horizontal form-signin" action="assignprojecttopm" method="post">  
          <div class="form-group">
          <label class="control-label">Project<sup>*</sup></label>
	      <select class="form-control" name="projectId" id="projectId" required>
							<option value="0">Choose A Project</option>
							<c:forEach items="${projectlist}" var="pro">
								<option value="${pro.projectId}">${pro.projectName}</option>
							</c:forEach>
          </select> 	      
	      </div> 
	      <div class="form-group">
	      <label class="control-label">ProjectManager<sup>*</sup></label>
	      <select class="form-control" name="userId" id="userId" required>
							<option value="0">Choose A Manager</option>
							<c:forEach items="${pmlist}" var="pm">
								<option value="${pm.userId}">${pm.name}</option>
							</c:forEach>
          </select>
	     </div>
        
          <div class="form-group">
          
	       <label class="control-label">StartDate<sup>*</sup></label>
	         <input type="text" class="form-control" id="startdate" name="startDate" placeholder="MM/DD/YYYY" />
	      </div>
	      <div class="form-group">
	         <label class="control-label"> EndDate<sup>*</sup></label>
	         <input type="text" class="form-control" id="enddate" name="endDate" placeholder="MM/DD/YYYY" />
          </div>
	     
		      <div class="form-group">
		        <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top:20px;margin-bottom:40px;">Assign</button> 
		        </div>
    </form>
      
      
      </div>
    </div>

</div>

</div>
<jsp:include page="commonJsp/Footer.jsp" />
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
	
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">

$('#startdate').datepicker({
	  minDate: 0,
	  beforeShow: function() {
	    $(this).datepicker('option', 'maxDate', $('#enddate').val());
	  }
	});
	$('#enddate').datepicker({
	  defaultDate: "+1w",
	  beforeShow: function() {
	    $(this).datepicker('option', 'minDate', $('#startdate').val());
	    if ($('#startdate').val() === '') $(this).datepicker('option', 'minDate', 0);
	  }
	});
</script>

  
</body>
</html>