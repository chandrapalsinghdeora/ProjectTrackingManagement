
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
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <link href="css/style_bug.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />
  <style>
  #message{color:#fff;}
#message:hover{color:#006780;}
#dtable thead,
#dtable thead,
#dtable thead,
#dtable thead {  background-color: #006780; color: #fff; }
.bug_header{border-bottom:1px solid gray;}
</style>
</head>
<script type="text/javascript">

   function acceptapproval(bugNo){

            document.getElementById("approve"+bugNo).action="btapprove";        
                document.getElementById("approve"+bugNo).method="post";
                document.getElementById("approve"+bugNo).submit();
   }
</script>
<script type="text/javascript">

   function editBug(bugNo){

            document.getElementById("editbug"+bugNo).action="editbug";        
                document.getElementById("editbug"+bugNo).method="post";
                document.getElementById("editbug"+bugNo).submit();
   }
</script>
<script type="text/javascript">
$(document).on( "click", '.edit_button',function(e) {
    var bugNo = $(this).data('bugNo');
    var projectName = $(this).data('projectName');
    var bugName = $(this).data('bugName');
    var bugType = $(this).data('bugType');

    $("#projectName").val(projectName);
    $("#bugName").val(bugName);
    $("#bugType").val(bugType);
    tinyMCE.get('#confirm-box1${loop.count}').setContent(approval);   
});

</script>






<body >
<div class="affix" style="width:100%;z-index:99;" >
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="pmhome">Home</a></li>
						<li><a href="pmhome">Project Manager</a></li>
					<li><a href="#">Bug Report</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	</div>
	
<div class="container-fluid" style="padding-top:250px;min-height:738px;">	

    

	     <a class="btn btn-primary" href="btsendtobr" style="background: #006780;color:#fff; float:right; margin-bottom:20px;">Bug Summary</a>


        


  
    <table id="dtable" class="table table-bordered display nowrap" >
    <thead>
      <tr>
        <th><center>Bug No</center></th>
        <th><center>Project</center></th>
		        <th><center>Module</center></th>
		 <th><center>Bug Tittle</center> </th>
		    <th><center>Bug Type</center></th>
		   <th><center>Bug Severity</center></th>
		     <th><center>Bug Status</center></th>
				  <th><center>Assign To</center></th>
				     <th><center>Date</center></th>
					<th><center>Issue</center></th>
      </tr>
    </thead>
    <tbody>
      	  <c:forEach items="${pendingapproval}" var="approval" varStatus="loop">  
	<tr>
	
	   
	      <td>${approval.bugNo}</td>
             <td>${approval.projectName}</td>
			       <td>${approval.module} </td>
			        <td>${approval.bugName} </td>
				      <td>${approval.bugType}</td>
				      <td>${approval.bugSeverity}</td>
                       <td>${approval.bugStatus}</td>
				          <td>${approval.assignTo}</td>
				          
				                  <td>${approval.date}</td>
				                  
				                  
		  <td>
	     <form id="approve${approval.bugNo}" method="POST">
          <input type="hidden" value="${approval.bugNo}" name="bugNo"/>
          </form>
      <input type="button" class="btn btn-primary"value="view issue" onclick="acceptapproval(${approval.bugNo})" style="background: #006780;color:#fff;"/>   
	  	
	  	
				            			 
		
		
	
 
  		            			    
				    
		</tr>	
</c:forEach>
    </tbody>
  </table>


</div>
  
  
 







 <script type="text/javascript" src="js/jquery.min.js"></script> 
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script> 
<script type="text/javascript" src="js/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/jszip.min.js"></script> 
<script type="text/javascript" src="js/pdfmake.min.js"></script> 
<script type="text/javascript" src="js/vfs_fonts.js"></script> 
<script type="text/javascript" src="js/buttons.html5.min.js"></script> 
<script type="text/javascript" src="js/buttons.print.min.js"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script> 
<script>
       
	  $(document).ready(function() {
    $('#dtable').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'excel', 'pdf', 'print'
        ]
	
    } );
} );
</script>
   

	<jsp:include page="commonJsp/Footer.jsp" />
</body>






</html>
