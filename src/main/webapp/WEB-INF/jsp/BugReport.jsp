
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

  <link href="css/style_bug.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />

<script type="text/javascript">

   function acceptapproval(bugNo){

            document.getElementById("approve"+bugNo).action="approve";        
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

<style>
.border_box{border:1px solid #000000;}
.padding_box{padding:3px 3px 3px 3px;}
.margin_left{margin-left:20px;}
 .bug_header{border-bottom:1px solid #d1d1d1;}
.table{margin-bottom:0px;}
.content_inner{min-height: 585px !important;height:100%;}
.top_table{min-width:400px;margin:10px 10px 10px 10px;}

.view_button button{background-color:#006780;color:#fff;}
.view_button button:hover{background-color:#0385a5;color:#fff;}

.icon_calender{position:absolute;right:10px;top:6px;}
#message{color:#fff;}
#message:hover{color:#006780;}

#dtable{table-layout:fixed;    width: 100%; }
#dtable thead tr th{padding: 10px 2px;word-wrap:break-word;white-space:inherit;}
#dtable tbody tr td input{ background-color:#006780;color:#fff;  }
#dtable tbody tr td input:hover{background-color:#0385a5;color:#fff; }
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
					<li><a href="#">Project Manager</a></li>
					<li><a href="#">Bug Report</a></li>
						<li><a href="#">Summary</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	</div>
	
<div class="container-fluid" style="padding-top:204px;min-height:738px;">	


  <nav class="navbar bug_header head_color">
  <div class="container-fluid">
  
    <div class="collapse navbar-collapse" id="myNavbar">
	 <ul class="nav navbar-nav navbar-left">
	    <li><h3 class="head_margin"><b>BUGS REPORT SUMMARY</b></h3></li>
      
      
      </ul>
        <ul class="nav navbar-nav navbar-right">
	    <!--  <li><a id="message"><i class="fa fa-envelope fa-lg" aria-hidden="true"></i></a></li>
      -->
      
      </ul>
     
     
      
   
    </div>
  </div>
  
</nav>





	     <div class="container-fluid">
	     <div class="row">
	     <div class="col-lg-6">
        <div class="top_table">
          <form action="btsendtobrdate" method="post">
       <table class="table table-bordered ">
       <thead>
            <tr>
            <td colspan="2"><b>Search by Date</b></td>
            </tr>       
       </thead>
	   <tbody>
	     
	        
	   
               <tr>
			      <td><label  control-label">Date From:</label></td>
          <td>  <div class="form-group">	
	     
          <input type="date" class="form-control" name="fromDate" id="date_from" placeholder="MM/DD/YYYY" />
		</div> 
				</td>
                 
               </tr>
			   
			   
               <tr>
		            <td><label  control-label">Date To:</label></td>
		
		           <td>
			 <div class="form-group">	
	     
          <input type="date" class="form-control" name="toDate" id="date_to" placeholder="MM/DD/YYYY" />
		</div> 
                   </td>
					
				
			   </tr>
			
		
				<tr><td colspan="2"><center class="view_button"> <button class="btn btn-primary" type="submit"> view </button>
					               <button  class="btn btn-primary" type="submit"> Reset</button>
                                 </center></td></tr>				 
								 
								 
        </tbody></table></form>
	
				
                   
        </div>
        </div>
        
         <div class="col-lg-6">
         <div class="top_table">
          <form action="btsendtobrproject" method="post">
       <table class="table table-bordered ">
	 
	     <thead>
            <tr>
            <td colspan="3"><b>Search by Project Name</b></td>
            </tr>       
       </thead>
	  <tbody>
	   
       
			   
			   
               <tr>
		            <td style="width:150px;"><label  control-label">Project Name:</label></td>
		
		           <td>
			 
	                     <select class="form-control" name="projectId" id="projectN"   required >
							<option value="0">Select The Project</option>
							<c:forEach items="${teamlist1}" var="team1">
								<option value="${team1.projectId}">${team1.projectName}</option>
							</c:forEach>
          </select>
			
                   </td>
					<td>
					<center class="view_button"> <button class="btn btn-primary" type="submit"> view </button>
					               <button  class="btn btn-primary" type="submit"> Reset</button>
                                 </center>
					
					</td>
				
			   </tr>
			
		
						 
								 
								 
        </tbody></table></form>
	
				
                   
        </div>
        
        
                 <div class="top_table">
          <form action="btsendtobrassign" method="post">
       <table class="table table-bordered ">
	 
	     <thead>
            <tr>
            <td colspan="3"><b>Search by Assign To</b></td>
            </tr>       
       </thead>
	  <tbody>
	   
       
			   
			   
               <tr>
		            <td style="width:150px;"><label  control-label">Assign To:</label></td>
		
		           <td>
			 
	                     <select class="form-control" name="userId" id="" required >
							<option value="0">Select Name</option>
							<c:forEach items="${teamlist2}" var="team1">
								<option value="${team1.userId}">${team1.assignTo}</option>
							</c:forEach>
          </select>
			
                   </td>
					<td>
					<center class="view_button"> <button class="btn btn-primary" type="submit"> view </button>
					               <button  class="btn btn-primary" type="submit"> Reset</button>
                                 </center>
					
					</td>
				
			   </tr>
			
		
						 
								 
								 
        </tbody></table></form>
	
				
                   
        </div>
         
         
         
         </div>
         </div>
        </div>
    <script type="text/javascript">
    	$('.form_date').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	</script>
   <div class="clearfix"></div>
<div class="content_inner">    
  <table id="dtable" class="table table-bordered display nowrap"  >
    <thead>
      <tr>
       <th>Bug No</th>
        <th>Project</th>
         <th>Module</th>
            <th>Sub Module</th>
            <th>Assign To</th>
            
               <th>Bug Name </th>
                    <th>Bug Type</th>
                    
                   <th >Description</th>
                   <th>Round</th>
                       <th>Date</th>
                        <th>Depends</th>
                    <th>QA's Bug Status</th>
                     <th>Dev's Bug Status</th>
                           <th>Summary</th>
       
      </tr>
    </thead>
	<tbody>
<c:forEach items="${pendingapproval}" var="approval" varStatus="loop">  
	<tr>
	      <td>${approval.bugNo}</td>
             <td>${approval.projectName}</td>
             	<td>${approval.module} </td>
                    <td>${approval.subModule} </td>
                    <td>${approval.name}</td>
                       <td>${approval.bugName} </td>
             	      <td>${approval.bugType}</td>
             	         <td>${approval.description}</td>
				          <td>${approval.round}</td>
				                <td>${approval.date}</td>
				                  <td>${approval.depends}</td>
             	      	      <td>${approval.bugStatus}</td>
             	      	      <td>${approval.status}</td>
				                  <td>${approval.summary}</td>
		</tr>	
</c:forEach>
     <tbody>
      <tr>
        <td role="gridcell"></td>
        <td role="gridcell"></td>
		 <td role="gridcell"></td>
        <td role="gridcell"></td>
        <td role="gridcell"></td>
		 <td role="gridcell"></td>
        <td role="gridcell"></td>
         <td role="gridcell"></td>
        <td role="gridcell"></td>
        <td role="gridcell"></td>
		 <td role="gridcell"></td>
        <td role="gridcell"></td>
       
        
      </tr>
     
     
    </tbody>
      </table>
  </div>
  
  </div>
 
	<jsp:include page="commonJsp/Footer.jsp" />
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
	
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


  <script>
  $( function() {
    $( "#date_from" ).datepicker();
    $( "#date_to" ).datepicker();
  } );
  </script>
 

  
 

  <!-- Modal -->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
          <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4>Message Box</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form role="form">
      
           
          
		  
			
			
			
		
			
				
			   <div class="form-group">
              <label>Inbox</label>
			<textarea name="inbox" rows="10" cols="67"></textarea>
			</div>
			  <div class="form-group">
              <label>New</label>
			<textarea name="inbox" rows="4" cols="67"></textarea>
			</div>
	          <div>
              <button class="float_left" type="submit" id="send" class="btn btn-success btn-block">Send</button>
			  
			  </div>
          </form>
       
		</div>
        <div class="modal-footer">
          <button type="submit" class="btn  btn-default pull-left" data-dismiss="modal"> Cancel</button>
        
        </div>
      </div>
  
      
    </div>
  </div> 





<script>
$(document).ready(function(){
	
	
	 $("#message").click(function(){
        $("#myModal2").modal();
    });
});
</script>

<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script> 
<script type="text/javascript" src="js/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/jszip.min.js"></script> 
<script type="text/javascript" src="js/pdfmake.min.js"></script> 
<script type="text/javascript" src="js/vfs_fonts.js"></script>
<script type="text/javascript" src="js/buttons.html5.min.js"></script> 
<script type="text/javascript" src="js/buttons.print.min.js"></script> 
<script>
       
$(document).ready(function() {
    $('#dtable').DataTable( {
        dom: 'Bfrtip',
     
        "paging":   false,
        "ordering": false,
        "info":     false,
        buttons: [
            'excel', 'pdf', 'print'
        ]
	
    } );
} );
</script>


</body>
 
 





</html>