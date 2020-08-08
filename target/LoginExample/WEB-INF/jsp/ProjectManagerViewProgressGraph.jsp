<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>


<head>
<title>Project Tracking Management</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<style>
.button {
  display: inline-block;
  padding: 15px 25px;
  font-size: 24px;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>

function getallmodulesofthisproject(val){
	document.getElementById("ddmodule2").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>Choose Module<oprion>";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].moduleid+">"+jsonData[i].modulename+"<oprion>";
			}
			document.getElementById("ddmodule2").innerHTML=data;
		}
	};
	xhttp.open("POST", "getallmodulesofthispro?projectId="+val.value, true);
	xhttp.send();
}


</script>

<script>

     $(document).ready(function(){
      $("#rbproject").click(function(){
	      if($(this).is(":checked"))
          {
		     $("#ddproject").prop("disabled", false);
		     $("#generate1").prop("disabled", false);
			 $("#ddmodule1").prop("disabled", true);
			 $("#ddmodule2").prop("disabled", true);
			 $("#generate2").prop("disabled", true);
			 $("#generate3").prop("disabled", true);
			 $("#dduser").prop("disabled", true);
			 $("#ddsubmodule1").prop("disabled", true);
			 $("#ddsubmodule2").prop("disabled", true);
			 $("#ddsubmodule3").prop("disabled", true);
			 $("#generate4").prop("disabled", true);
		  }

	    });
		
	  $("#rbmodule").click(function(){
	      if($(this).is(":checked"))
          {
		     $("#ddproject").prop("disabled", true);
		     $("#generate1").prop("disabled", true);
			 $("#ddmodule1").prop("disabled", false);
			 $("#ddmodule2").prop("disabled", false);
			 $("#generate2").prop("disabled", false);
			 $("#generate3").prop("disabled", true);
			 $("#dduser").prop("disabled", true);
			 $("#ddsubmodule1").prop("disabled", true);
			 $("#ddsubmodule2").prop("disabled", true);
			 $("#ddsubmodule3").prop("disabled", true);
			 $("#generate4").prop("disabled", true);
		  }

	    });
	  
	  $("#rbuser").click(function(){
	      if($(this).is(":checked"))
          {
	    	 $("#generate1").prop("disabled", true);
	    	 $("#generate2").prop("disabled", true);
			 $("#generate3").prop("disabled", false);
		     $("#ddproject").prop("disabled", true);
			 $("#ddmodule1").prop("disabled", true);
			 $("#ddmodule2").prop("disabled", true);
			 $("#dduser").prop("disabled", false);	
			 $("#ddsubmodule1").prop("disabled", true);
			 $("#ddsubmodule2").prop("disabled", true);
			 $("#ddsubmodule3").prop("disabled", true);
			 $("#generate4").prop("disabled", true);
		  }

	    });
	  
	  $("#rbsubmodule").click(function(){
	      if($(this).is(":checked"))
          {
	    	 $("#generate1").prop("disabled", true);
	    	 $("#generate2").prop("disabled", true);
			 $("#generate3").prop("disabled", true);
		     $("#ddproject").prop("disabled", true);
			 $("#ddmodule1").prop("disabled", true);
			 $("#ddmodule2").prop("disabled", true);
			 $("#dduser").prop("disabled", true);	
			 $("#ddsubmodule1").prop("disabled", false);
			 $("#ddsubmodule2").prop("disabled", false);
			 $("#ddsubmodule3").prop("disabled", false);
			 $("#generate4").prop("disabled", false);
		  }

	    });
	  
	});

</script>
</head>

<body>


<body>
<div class="affix" style="width:100%; z-index:99;" >
<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="pmhome">Home</a></li>
					<li><a href="#">Graph Generation</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div></div>
<div class="container-fluid" style="padding-top:250px;min-height:738px;">
<div class="container">

 <div class="panel panel-default" style="margin-top:50px;">
	      <div class="panel-heading" style="background-color:#006780;color:white;"> Graph Generation</div>
<div class="row" style="padding:17px 0px 17px 0px;">

    <div class="col-md-2">&nbsp;</div>
	<div class="col-md-3">
	   <div class="panel panel-default" style="height:217px;" >
	      <div class="panel-heading" style="background-color:#006780;color:white;"> 
	        <input type="radio" name="rbproject" id="rbproject"> Project Wise</div>
	      <div class="panel-body">
	      <form action="superadmingraphprojectwise" method="post">
	      <center>
	        <select class="dropdown_margin_bottom" id="ddproject" name="projectId" disabled>
			     <option value="" selected>choose project</option>
		         <c:forEach items="${projects}" var="pro">
		            <option value="${pro.projectId}">${pro.projectName}</option>
		         </c:forEach>
	        </select>
	      </center>
	      <center>
	        <button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:70px;" id="generate1" disabled>Generate</button>
	      </center>
	      </form>
	     </div>
	    </div>
	  </div>
	  
	  
     <div class="col-md-1">&nbsp;</div>
      <div class="col-md-3">
         <div class="panel panel-default" style="height:217px;">
            <div class="panel-heading" style="background-color:#006780;color:white;">
               <input type="radio" name="rbproject" id="rbmodule"> Module Wise</div>
                  <div class="panel-body">
                    <form action="superadmingraphmodulewise" method="post">
                   <center>
                     <select class="dropdown_margin_bottom" id="ddmodule1" name="projectId" disabled onchange="getallmodulesofthisproject(this)">
						 <option value="" selected>choose project</option>
				         <c:forEach items="${projects}" var="pro">
				            <option value="${pro.projectId}">${pro.projectName}</option>
				         </c:forEach>
                     </select>
                     </center>
                     <br>
					 <center>
					  <select class="dropdown_margin_bottom" id="ddmodule2" name="moduleId" disabled>
					    <option value="0" selected>choose module</option>
                      </select>
                     </center>
                    <center>
                      <button type="submit" class="btn btn-default" style="background-color:#006780;color:white;margin-top:35px;" id="generate2" disabled>Generate</button>
                    </center>
                    </form>
                  </div>
			  </div>
			  </div>
			</div>
			</div>
			</div></div>
				<jsp:include page="commonJsp/Footer.jsp" />
</body>



</body>
</html>
