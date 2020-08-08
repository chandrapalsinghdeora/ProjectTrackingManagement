


<html>
<head>
<title>Project Tracking Management</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<style>
body {
  background: #eee !important;
}

.wrapper {
  margin-top: 80px;
  margin-bottom: 80px;
}

.form-signin {
  max-width: 380px;
  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.1);
  height:270px;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 30px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
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
  <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>

      <link rel="stylesheet" href="css/style.css">
      
      
<body>
  <jsp:include page="commonJsp/Header.jsp" />                      
<div class="container" style="padding-top:200px;min-height:738px;">                      

    <form class="form-signin" action="forgotemail" method="post">        
      <h2 class="form-signin-heading">Please Enter Email</h2>

 EmailId<sup>*</sup><input type="email" class="form-control" id="forgotemail" name="forgotemail" placeholder="EMAIL" required autofocus/><br>

 <label style="color:red">${msg}</label>
 
      <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">GetEmail</button> 
      </div>

     

    </form>
</div>
	<jsp:include page="commonJsp/Footer.jsp" />
</body>
</html>
            
