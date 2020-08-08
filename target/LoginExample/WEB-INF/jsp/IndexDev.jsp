
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
 <style>
 .content{min-height:635px !important;height:100%}
   #message{color:#fff;}
#message:hover{color:#006780;}

#dtable thead tr th{padding: 10px 2px;word-wrap:break-word;white-space:inherit;}
#dtable tbody tr td input{ background-color:#006780;color:#fff;  }
#dtable thead {  background-color: #006780; color: #fff; }
.bug_header{margin-bottom:10px;}
</style> 
</head>
<script type="text/javascript">

   function acceptapproval(bugNo){

            document.getElementById("approve"+bugNo).action="btapprove";        
                document.getElementById("approve"+bugNo).method="post";
                document.getElementById("approve"+bugNo).submit();
   }
</script>





<body >
  	<div class="affix" style="width:100%;z-index:99;" >
	<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="tlhome">Home</a></li>
					

							<li>Developer Bug Report</li>
					</ul>
				</div>
	
			</div>
		</div></div>

  <div class="container-fluid" style="padding:250px 0px 10px 0px;min-height:738px;">

       

  
  
  
  
  
    <table id="dtable" class="table table-bordered display nowrap" style="table-layout:fixed;margin-bottom:10px;">
    <thead>
          <tr>
        <th><center>Bug No</center></th>
    			     <th><center>Date</center></th>
        <th><center>Project</center></th>
		 <th><center>Bug Tittle</center> </th>
		    <th><center>Bug Type</center></th>
		        <th><center>Bug Severity</center></th>
		    <th><center>QA Bug Status</center></th>
				  <th><center>Assign By</center></th>
				     
				      <th><center>Dev's Bug Status</center></th>
					<th><center>Summary</center></th>
					 <th><center>Issue</center></th>
				<th><center>Response</center></th>
					
      </tr>
    </thead>
    <tbody>
      	  <c:forEach items="${pendingapproval}" var="approval" varStatus="loop">  
	<tr>
	
	   
	      <td>${approval.bugNo}</td>
           <td>${approval.date}</td>
             <td>${approval.projectName}</td>
			        <td>${approval.bugName} </td>
				      <td>${approval.bugType}</td>
				     <td>${approval.bugSeverity}</td>
				      <td>${approval.bugStatus}</td>
				          <td>${approval.name}</td>
				       		       <td>${approval.status}</td>
			                          <td>${approval.summary}</td>           
				                  
		  <td>
	     <form id="approve${approval.bugNo}" method="POST">
          <input type="hidden" value="${approval.bugNo}" name="bugNo"/>
          </form><center>
      <input type="button" class="btn btn-primary" value="View Issue" onclick="acceptapproval(${approval.bugNo})"/>   
	  </center>
</td>
	
          	<td><input type="button" class="btn btn-primary"
					data-bugno="${approval.bugNo}" 
					data-toggle="modal" data-id="${approval.bugNo}" data-target="#C-role-edit"
					id="edit" value="Update" > 
		</td>	    
				    
				    
		</tr>	
</c:forEach>
    </tbody>
  </table>

     				            
  <!-- Modal -->
  <!-- Modal -->
  <div class="modal fade" id="C-role-edit" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4>Create Response</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
        <form class="form-signin" action="btsendresponse" method="post" enctype="multipart/form-data">
         
           <div class="form-group">
              <!-- <label for="bugNo">Bug Number</label> -->
              <input type="hidden" class="form-control" id="bugNo"  name="bugNo">
            </div>        
		
			  <div class="form-group">
              <label>Status</label>

  <select id="status"  class="form-control" name="status">
  <option value="">--Select one--</option>
  <option value="Defered">Defered</option>
  <option value="Rejected">Rejected</option>
  <option value="Duplicate">Duplicate</option>
  <option value="Not A Bug">Not A Bug</option>
  <option value="Fixed">Fixed</option>
</select>
            </div>
			
				
			   <div class="form-group">
              <label for="summary">Summary</label> <br>
			<textarea name="summary" rows="4" cols="74" style="resize:none"></textarea>
			</div>

	  <div class="form-group">	
	  <label for="summary">Delivered</label> <br>	
          <input type="text" class="form-control" name="delievered" id="delievered" placeholder="MM/DD/YYYY" />
		</div> 	
			
			  <div class="form-group">
              <label for="file_upload">File Upload</label> <br>
			<input type="file" name="FileData" size="40" id="id" onchange="checkFileTypeForPhoto(id)" placeholder="choose file">
			</div>
		
			  <button class="float_left" type="submit" id="submit" class="btn btn-success btn-block">Submit</button>
			  
          </form>
       
		</div>
		<div class="clearfix"></div>
        <div class="modal-footer" style="padding:10px;">
          <button type="submit" class="btn btn-default pull-left" data-dismiss="modal"> Cancel</button>
        
        </div>
      </div>
      
    </div>
  </div> 
  </div>
  
  	<jsp:include page="commonJsp/Footer.jsp" />
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
	
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


  <script>
  $( function() {
    $( "#delievered" ).datepicker();

  } );
  </script>
   
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
  
 <script>
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
	
	
	 $("#message").click(function(){
        $("#myModal2").modal();
    });
});
</script>
<script>
function checkFileTypeForPhoto(id){
	 var fileName=document.getElementById(id).value;
	    var ext = $('#'+id).val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['jpeg','jpg','png']) == -1) {
	            alert("Only jpeg, jpg, png File are allowed to upload.")
	            document.getElementById(id).value="";
	    }else{
	    }
}
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
        buttons: [
           'excel', 'pdf', 'print'],
    order:'false'
    });
    });
</script>

<script type="text/javascript">

$(document).on("click", "#edit", function () {
    var bugno = $(this).data('id');
   
    $("#bugNo").val($(this).data('bugno'));
     $("#C-role-edit").show();
});

$(document).ready(function(){
    $("#status").change(function(){
  	  if($(this).val()== "Defered" || $(this).val()=="Rejected"||$(this).val()== "Duplicate" || $(this).val()=="Not A Bug")
        {
		     $("#delievered").prop("disabled", true);
        }
  	  else
  		  {
  		     $("#delievered").prop("disabled", false);
  		  }
	    });
	});
</script>



</body>
 
 





</html>