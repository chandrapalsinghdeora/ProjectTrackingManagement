


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
  height:335px;
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

 <script type="text/javascript">
       function validateit(){
    	   
    	   var password = document.getElementById("userPassword").value;
    	   var confirmpassword = document.getElementById("userConfirmPassword").value;
    	   if(password == confirmpassword)
    		   return true;
    	   else
    		   document.getElementById("message").innerHTML="Password do not match";
    		   return false;
       }
</script> 

  <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>

      <link rel="stylesheet" href="css/style.css">
      
      
<body>
   <jsp:include page="commonJsp/Header.jsp" />                      
<div class="container" style="padding-top:200px;min-height:738px;">                       

    <form class="form-signin" action="updateuserpassword" method="post" onsubmit="return validateit()">        
      <h2 class="form-signin-heading">Update Your Password</h2>
   
         <input type="hidden" value="${token}" name="token"/> 

		 New Password<sup>*</sup><input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="PASSWORD" required autofocus/>
		
		 Confirm Password<sup>*</sup><input type="password" class="form-control" id="userConfirmPassword" name="userConfirmPassword" placeholder="RE-ENTER PASSWORD" required autofocus/>
 
  		 <span id="message" style="color:red"></span>
 
      <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit" >Update Password</button> 
      </div>

    </form>
</div>
	<jsp:include page="commonJsp/Footer.jsp" />
</body>
</html>
            
