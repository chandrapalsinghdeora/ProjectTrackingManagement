<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
 <html>
  <head>
  <title>Project Tracking Management</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
      
     
      function drawChart() {
      var data=[];
      var Header= ['ModuleName', 'Module'];
      data.push(Header);

      <c:forEach items="${listrole}" var="list" varStatus="loop">
     var temp=[];
     temp.push("${list.moduleNameIndividually}");
     temp.push(parseInt('${list.percentage}'));
     data.push(temp);
	</c:forEach>
     var chartdata = new google.visualization.arrayToDataTable(data);
     var options = {
   	      title: 'Completion of Modules',
   	      legend: { textStyle: { fontSize: '15', color: '#355404' } },
   	      hAxis: { title: 'ModuleName', textStyle: { fontSize: '15', color: '#355404'}, slantedText: true, slantedTextAngle: '45'},
   	      vAxis: { title: '% Complete', viewWindow:{max:100, min:0}, textStyle: { fontSize: '15', color: '#355404' } },
   	      backgroundColor: '#cae89b',
   	      width: 800,
   	      height: 400,
   	      padding: 10,
   	      };
  
        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        chart.draw(chartdata, google.charts.Bar.convertOptions(options));
     
        }   
    
    </script>
    
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
  </head>
  <body >
  <div class="affix" style="width:100%;" >
		<jsp:include page="commonJsp/Header.jsp" />
		<div class="clearfix"></div>
		<div class="breadcumb-wrapper">
			<div class="clearfix">
				<div class="pull-left">
					<ul class="list-inline link-list">
						<li><a href="#">Home</a></li>
						<li><a href="#">Assign Project</a></li>
					</ul>
				</div>
	
			</div>
		</div> 
    </div>
  <div class="container-fluid" style="padding-top:250px;min-height:738px;background-color:#dceac6;">
    <div id="columnchart_material" style=" display: block; margin: 0 auto; width: 800px; height: 450px;"></div></div>
    <jsp:include page="commonJsp/Footer.jsp" />
  </body>
</html>
