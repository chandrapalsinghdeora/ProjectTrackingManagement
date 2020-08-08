<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">

<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css"
        media="screen" />
        <link href="css/style_new.css" rel="stylesheet">

	<style>
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
.dropdown a:active{background-color:#006780;color:#fff;}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
     min-width:133px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a{
    color: black;
    padding: 12px 8px;
    text-decoration: none !important;
    display: block;
    border:none;
     
}

.dropdown-content a:hover {background-color:#006780 !important;color:#fff;}

.dropdown:hover .dropdown-content {
    display: block;
}
.login_focus:focus{background-color:#006780 !important;color:#fff;}
.nav > li > a:focus, .nav > li > a:hover{padding:10px !important;}
 .login_focus:hover{margin-top:5px;}
	 .login_focus:focus{margin-top:5px;}
	</style>
<c:if test="${empty sessionBean.userId}">
        <header>
		  <nav class="navbar  margin_bottom  nav_height header">
		  <div class="container-fluid ">
		    <div class="navbar-header">
		      <a  href="/ProjectTrackingManagement/"><img src="images/logo_pre.png"/></a>
		    </div>
		
		  </div>
		  </nav>

        </header>
</c:if>

<c:if test="${not empty sessionBean.userId}">

        <header>
  <nav class="navbar  margin_bottom  nav_height header">
  <div class="container-fluid ">
    <div class="navbar-header">
     <c:if test="${sessionBean.roleId==4}">
      <a href="superadminhome"><img src="images/logo_pre.png"/></a>
     </c:if>
      <c:if test="${sessionBean.roleId==1}">
      <a href="pmhome"><img src="images/logo_pre.png"/></a>
     </c:if>
      <c:if test="${sessionBean.roleId==2}">
      <a href="tlhome"><img src="images/logo_pre.png"/></a>
     </c:if>
      <c:if test="${sessionBean.roleId==3}">
      <a href="emphome"><img src="images/logo_pre.png"/></a>
     </c:if>
     <c:if test="${sessionBean.roleId==5}">
      <a href="qahome"><img src="images/logo_pre.png"/></a>
     </c:if>
     
    </div>
    <div class="collapse navbar-collapse padd" id="myNavbar">
      
      <ul class="nav navbar-nav navbar-right">
	    <!--<li><a href="#"><i class="fa fa-bell margin_icon" aria-hidden="true"></i></a></li>
        <li><a href="#"><i class="fa fa-user margin_icon" aria-hidden="true"></i>Hi User</a></li>-->
             <li class="dropdown">
	   <a href="#" class="dropdown-toggle login_focus" data-toggle="dropdown" >${sessionBean.name}<i class="fa fa-user" aria-hidden="true" style="margin:5px;"></i></a>
	   <ul class="dropdown-menu">
        
        <li>    <a target="" href="changedetails">Update Details <i class="fa fa-user" style="margin-left:10px; aria-hidden="true"></i></a></li>
          
       
        <li>    <a href="logout">Logout<i class="fa fa-sign-out" style="margin-left:10px; " aria-hidden="true"></i></a></li>
          
      </ul>
     </li>
      </ul>
    </div>
  </div>
</nav>
 
                <div class="bottom-nav-menu">
                       

                        <div class="navbar-wrapper">
                                <div class="container-fluid">
                                        <nav class="navbar ">
                                                <div class="container-fluid">
                                                 
                                                        <c:if test="${sessionBean.roleId==4}">
                                                                <div id="navbar" class="navbar-collapse collapse">
                                                                        <ul class="nav navbar-nav">
                                                                          
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle margin_list" data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Add Project <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">

                                                                                                <li><a href="saaddprojects">Add Projects</a></li>
                                                                                                <li><a href="saaddmodules">Add Modules</a></li>
                                                                                                <li><a href="saaddsubmodules">Add SubModules</a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Assign Project <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="saassignproject">Assign Project</a></li>

                                                                                        </ul>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">View Progress <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="superadmingetgraph">Graph Generation </a></li>
                                                                                                <li><a href="getsuperadminexcels">Excel Generation</a></li>
                                                                                        </ul></li>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Finished Project<span
                                                                                                class="caret"></span> </a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="safinishedproject">Finished Project</a></li>
                                                                                        </ul>
                                                                                       </li>
                                                
                                                                        </ul>

                                                                </div>
                                                        </c:if>

  
                                                                   <c:if test="${sessionBean.roleId==1}">
                                                                <div id="navbar" class="navbar-collapse collapse">
                                                                        <ul class="nav navbar-nav">
                                                                          
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle margin_list" data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Assigned Project<span
                                                                                                class="caret"> </a>
                                                                                        <ul class="dropdown-menu">

                                                                                                <li><a href="pmassigedproject">Assigned Project</a></li>
                                                                                               
                                                                                                
                                                                                        </ul></li>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Add modules and submodules <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="pmaddmodules1">Add Modules</a></li>
                                                                                                 <li><a href="pmaddsubmodules1">Add Sub Modules</a></li>

                                                                                        </ul>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Assign Module <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="assignmodule">Assign Module </a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">View Progress<span
                                                                                                class="caret"></span> </a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="getpmgraph">Graph Generation</a></li>
                                                                                                <li><a href="getdifferentexcels">Excel Generation</a></li>
                                                                                        </ul>
                                                                                       </li>
                                                
                                                 <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Approval Pending <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="adminpendingapproval">Approval Pending </a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                        
                                                                                         <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Role Pending <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="rolependingapproval">Role Pending </a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                        
                                                                                        <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Finished Project <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="pmfinishedprojects">Finished Project</a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                        
                                                                                                 <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Bug Report <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="btsendtopmdb">Bug Report</a></li>
                                                                                                
                                                                                        </ul></li>
                                                                        </ul>

                                                                </div>
                                                        </c:if>                                      
                                                       <c:if test="${sessionBean.roleId==2}">
                                                                <div id="navbar" class="navbar-collapse collapse">
                                                                        <ul class="nav navbar-nav">
                                                                          
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle margin_list" data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Module Assigned<span
                                                                                                class="caret"> </a>
                                                                                        <ul class="dropdown-menu">

                                                                                                <li><a href="assignedtlmodule">Module Assigned</a></li>
                                                                                               
                                                                                                
                                                                                        </ul></li>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Assigned Work <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="empassignedsubmod">Assigned Submodules</a></li>
                                                                                                 <li><a href="empassignedtask">Assigned Tasks</a></li>

                                                                                        </ul>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Add Submodules & Tasks <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="tladdsubmodule">Add Submodules </a></li>
                                                                                                <li><a href="tladdtask">Add Tasks </a></li>
                                                                                                <li><a href="taskdependency">Task Dependency </a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Assign<span
                                                                                                class="caret"></span> </a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="tlassignmodule">Assign Submodule</a></li>
                                                                                                <li><a href="tlassigntask">Assign Task</a></li>
                                                                                        </ul>
                                                                                       </li>
                                                
                                                 <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Update Status<span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="updatestatus">Update Status </a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                        
                                                                                         <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">View Progress <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="gettlgraph">Graph Generation</a></li>
                                                                                                <li><a href="getexcels">Excel Generation</a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                             <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Bug Report <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="btsendtodevdb">Report And Response</a></li>
                                                                                               
                                                                                                
                                                                                        </ul></li>
                                                                                        
                                                                                        
                                                                        </ul>

                                                                </div>
                                                        </c:if>
                                                                                 <c:if test="${sessionBean.roleId==3}">
                                                                <div id="navbar" class="navbar-collapse collapse">
                                                                        <ul class="nav navbar-nav">
                                                                          
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle margin_list" data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false"> Assigned Work<span
                                                                                                class="caret"> </a>
                                                                                        <ul class="dropdown-menu">

                                                                                                <li><a href="empassignedsubmod"> Assigned SubModule</a></li>
                                                                                               
                                                                                                <li><a href="empassignedtask"> Assigned Task</a></li>
                                                                                        </ul></li>

                                                                               
                                                
                                                                                      <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Update Status<span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="updatestatus">Update Status </a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                             <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Bug Report <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="btsendtodevdb">Report And Response</a></li>
                                                                                               
                                                                                                
                                                                                        </ul></li>
                                                                                        <!--  <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">View Progress <span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                            
                                                                                                <li><a href="excel">Excel Generation</a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                         -->
                                                                                        
                                                                        </ul>

                                                                </div>
                                                        </c:if>  
                                                         <c:if test="${sessionBean.roleId==5}">
                                                                <div id="navbar" class="navbar-collapse collapse">
                                                                        <ul class="nav navbar-nav">
                                                                          
                                                                                <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle margin_list" data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Bug Report<span
                                                                                                class="caret"> </a>
                                                                                        <ul class="dropdown-menu">

                                                                                                <li><a href="btsendtodb">Bug Report</a></li>
                                                                                               
                                                                                               
                                                                                        </ul></li>

                                                                                      <li class=" dropdown"><a href="#"
                                                                                        class="dropdown-toggle " data-toggle="dropdown" role="button"
                                                                                        aria-haspopup="true" aria-expanded="false">Bug Response<span
                                                                                                class="caret"></span></a>
                                                                                        <ul class="dropdown-menu">
                                                                                                <li><a href="btsendtoresdb">Bug Response </a></li>
                                                                                                
                                                                                        </ul></li>
                                                                                        
                                                                          
                                                                                        
                                                                                        
                                                                        </ul>

                                                                </div>
                                                        </c:if>  
                                                </div>
                                        </nav>
                                </div>
                        </div>
                </div>
        </header>
</c:if>
