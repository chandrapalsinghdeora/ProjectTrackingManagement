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
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />
<style>
.border_box{border:1px solid #000000;}
.padding_box{padding:3px 3px 3px 3px;}
  #message{color:#fff;}
#message:hover{color:#006780;}

.bug_header{margin-bottom:10px;}
</style>
</head>

<script type="text/javascript">

   function closebug(bugNo){

            document.getElementById("close"+bugNo).action="btclose";        
                document.getElementById("close"+bugNo).method="post";
                document.getElementById("close"+bugNo).submit();
               
   }
</script>
<script type="text/javascript">

   function downloadfile(bugNo){

            document.getElementById("download"+bugNo).action="btdownload";        
                document.getElementById("download"+bugNo).method="post";
                document.getElementById("download"+bugNo).submit();
               
   }
</script>


<body >
 <div class="affix" style="width:100%;z-index:99" >
	<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="#">Home</a></li>
						<li><a href="#">QA</a></li>
							<li>Bug Response</li>
					</ul>
				</div>
	
			</div>
		</div></div>



<div class="container-fluid" style="padding:273px 0px 10px 0px;min-height:700px;">


 
       

  
    <table id="dtable" class="table table-bordered display nowrap">
    <thead>
         <tr>
      
        <th><center>Bug No</center></th>
        <th><center>Project</center></th>
		 <th><center>Bug Tittle</center> </th>
		  <th><center>Assign To</center></th>
				     <th><center>QA's Bug Status</center></th>
				     <th><center>Dev's Bug Status</center></th>
					<th><center>Summary</center></th>
					<th><center>Delievered</center></th>
					<th><center>Download File</center></th>
					<th><center>Close Bug</center></th>
					
      </tr>
    </thead>
    <tbody>
          <c:forEach items="${pendingapproval}" var="approval" varStatus="loop">  

	<tr>
	         
	         
	      <td>${approval.bugNo}</td>
             <td>${approval.projectName}</td>
			       <td>${approval.bugName} </td>
				          <td>${approval.name}</td>
				            <td>${approval.bugStatus}</td>
				            <td>${approval.status}</td>
				              <td>${approval.summary}</td>
				              <td>${approval.delievered} </td>
<td> 
  <form id="download${approval.bugNo}" method="POST">
 <input type="hidden" value="${approval.bugNo}" name="bugNo"/>
 <input type="button" class="btn btn-primary" value="Download" onclick="downloadfile(${approval.bugNo})" style="background: #006780;color:#fff;"/>  
  </form>
 </td>

<td>				               
 <form id="close${approval.bugNo}" method="POST">
<input type="hidden" value="${approval.bugNo}" name="bugNo"/>
 <input type="button" class="btn btn-primary" value="Close" onclick="closebug(${approval.bugNo})" style="background: #006780;color:#fff;"/> 
  </form> 
</td>
				            
				            
				
		</tr>	
</c:forEach>
        
    </tbody>
  </table>
  

   <!-- Modal -->
  <div class="modal fade " id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4>Bug Response</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
        <form class="form-signin" action="btsendresponse" method="post" >
         
           <div class="form-group">
              <label for="bug_no">Bug Number</label>
              <input type="text" class="form-control" id="bug_no" name="bugNo" placeholder="--enter bug number--">
            </div>
         
          
	
			
			
		
			  <div class="form-group">
              <label>Status</label>

  <select id="status"  class="form-control" name="status">
  <option value="">--Select one--</option>
  <option value="closed">Closed</option>
  <option value="new">New</option>
  <option value="resolved">Resolved</option>
  <option value="unresolved">Unresolved</option>
</select>
            </div>
			
				
			   <div class="form-group">
              <label for="summary">Summary</label> <br>
			<textarea name="summary" rows="4" cols="67"></textarea>
			</div>

	 <div class="form-group">		
	<fieldset>
            
            <div class="control-group">
                <label class="control-label">Delivered</label>
                <div class="controls input-append date form_datetime" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                    <input size="25" type="text" name ="delievered" value="" readonly>
                    <span class="add-on border_box padding_box"><i class="fa fa-times" aria-hidden="true"></i></span>
					<span class="add-on border_box padding_box"><i class="fa fa-calendar" aria-hidden="true"></i></span>
					
                </div>
				<input type="hidden" id="dtp_input1" value="" /><br/>
            </div>
			
			
        </fieldset>
		</div>	
			
			  <div class="form-group">
              <label for="file_upload">File Upload</label> <br>
			<input type="file" name="datafile" size="40" placeholder="choose file">
			</div>
			
			  
       
			  <button class="float_left" type="submit" id="submit" class="btn btn-success btn-block">Submit</button>
			  
			  
              
			  
          </form>
       
		</div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-default pull-left" data-dismiss="modal"> Cancel</button>
        
        </div>
      </div>
      
    </div>
  </div> 
  <script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
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
  </div>
	<jsp:include page="commonJsp/Footer.jsp" />



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
            'excel', 'pdf', 'print'
        ]
	order:'false';
    } );
} );
</script>
</body>
 
 





</html>