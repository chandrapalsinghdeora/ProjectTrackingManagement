<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>create response</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->

<link href="css/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet" />

<link rel="stylesheet" href="css/bootstrap.min.css">


  <link href="css/style_new.css" rel="stylesheet" />


  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>


<script>
$(document).ready(function(){

        $("#Add_module").load("saaddmodules");
		$("#Add_project").load("saaddprojects");
		$("#Add_submodule").load("saaddsubmodules");
		$("#Assign_project").load("saassignproject");
		$("#graph_gen").load("superadmingetgraph");
		$("#excel_gen").load("getsuperadminexcels");
		$("#finished_project").load("");
	
   
});
</script>

<style>
.border_box{border:1px solid #000000;}
.padding_box{padding:3px 3px 3px 3px;}
.margin_left{margin-left:20px;}
.bug_header{border-bottom:1px solid #d1d1d1;}
.table{margin-bottom:0px;}
.content_inner{min-height:635px !important;height:100%;}


.view_button input{background-color:#006780;color:#fff;}
.view_button input:hover{background-color:#0385a5;color:#fff;}

.icon_calender{position:absolute;right:10px;top:6px;}

#dtable{table-layout:fixed;    width: 100%; }
#dtable thead {  background-color: #006780; color: #fff; }
#dtable thead tr th{padding: 10px 2px;word-wrap:break-word;white-space:inherit;}
#dtable tbody tr td{word-wrap: break-word; white-space:inherit;}


.navbar{margin-bottom:0px;}


.left_inner_seciton{
   position:fixed;
	width:237px;
	
    margin-right:0px;
  
	margin-left:0px;

    background-color:#E9E9E9;
    height:100%;
	}
	
	 .left_inner_seciton ul{padding:0px 0px 0px 0px;}

	.left_inner_seciton li{
	list-style:none;

	height:50px;
    color: #000000;
    padding-top:3px 2px 3px 2px;
    width:100%;
    text-decoration: none;
	background-color:#FFFFFF;
	margin:2px 2px 2px 0px ;
   
	}
.left_inner_seciton li a{display:block;color:#000000;margin-top:0px; width:100%;}

 .left_inner_seciton li a:hover{
 
    height:50px;
    color:#fff;
    background-color:#006780 !important;
}

.navbar-right li{margin-left:2px;}
.header_top_padding{padding-top:54px !important;}


.dropbtn {
    background-color: white;
    color: black;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none !important;
    display: block;
    border:none;
}

.dropdown-content a:hover {background-color: #f1f1f1;color:#fff;}

.dropdown:hover .dropdown-content {
    display: block;
}
.nav li a:hover{background-color:#006780 !important;}

</style>
</head>

<body>
  
  <nav class="navbar  margin_bottom  nav_height header affix">
  <div class="container-fluid ">
    <div class="navbar-header">
      <a  href="#"><img src="images/logo_pre.png"/></a>
    </div>
    <div class="collapse navbar-collapse padd" id="myNavbar">
      
      <ul class="nav navbar-nav navbar-right">
	    <!--<li><a href="#"><i class="fa fa-bell margin_icon" aria-hidden="true"></i></a></li>
        <li><a href="#"><i class="fa fa-user margin_icon" aria-hidden="true"></i>Hi User</a></li>-->
             <li class="dropdown">
	   <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionBean.name}<i class="fa fa-user" aria-hidden="true" style="margin:5px;"></i></a>
	   <div class="dropdown-content">
        
            <a target="" href="changedetails">Update Details <i class="fa fa-user" style="margin-left:10px; aria-hidden="true"></i></a>
          
       
            <a href="logout">Logout<i class="fa fa-sign-out" style="margin-left:10px; " aria-hidden="true"></i></a>
          
      </div>
     </li>
      </ul>
    </div>
  </div>
</nav>





   <div class="container-fluid header_top_padding" style="min-height:742px;">
	<div class="row">
		<div  class="col col-sm-2 " style="padding:0px 0px; ">
		<div class="left_inner_seciton">
    	    <ul class="nav nav-tabs nav-stacked text-center" role="tablist">
			    
				 <li role="presentation" >
    
        <div class="dropdown " style="margin-top:12px;">
                   
					 <button class="dropbtn">Add Project And Assign Modules</button>
					  <div class="dropdown-content">
					  <a  href="#Add_project" aria-controls="Add_project" role="tab" data-toggle="tab">Add Project</a>
					    <a  href="#Add_module" aria-controls="Add_module" role="tab" data-toggle="tab">Add Modules</a>
					    <a  href="#Add_submodule" aria-controls="Add_submodule" role="tab" data-toggle="tab">Add SubModules</a>
                      </div>
        </div>
</li> 
 <li role="presentation" ><a href="#Assign_project" aria-controls="Assign_project" role="tab" data-toggle="tab">Assign Project</a></li>
                
                <li role="presentation">
                <div class="dropdown" style="margin-top:12px;">
                <button class="dropbtn">View Progress</button>
					  <div class="dropdown-content">
					    <a  href="#graph_gen" aria-controls="graph_gen" role="tab" data-toggle="tab">Graph Generation</a>
					    <a  href="#excel_gen" aria-controls="excel_gen" role="tab" data-toggle="tab">Excel Generation</a>
					  </div>
                </div>  

				</li>

				<li role="presentation"><a href="#finished_project" aria-controls="finished_project" role="tab" data-toggle="tab">Finished Project</a></li>

			</ul>
			</div>
		</div>
        <div class="col col-sm-10">
        <div class="row tab-content">

<div role="tabpanel" class="tab-pane fade " id="Assign_project">                  


 </div>
 

<div role="tabpanel" class="tab-pane fade" id="Add_submodule">


  </div>
<div role="tabpanel" class="tab-pane fade" id="Add_module">


  </div>
<div role="tabpanel" class="tab-pane fade" id="Add_project">


  </div>
  <div role="tabpanel" class="tab-pane fade" id="graph_gen">


  </div>
  <div role="tabpanel" class="tab-pane fade" id="excel_gen">


  </div>

  <div role="tabpanel" class="tab-pane fade" id="finished_project">

  </div>
  </div>
			
		</div>
	</div>
</div>







	 
        


  <div class="footer_last">
  <div class="container">
    <center>
      Copyright © Precise Automation and Robotics. All Rights Reserved.
    </center>
  </div>
</div>







</body>
 
 





</html>