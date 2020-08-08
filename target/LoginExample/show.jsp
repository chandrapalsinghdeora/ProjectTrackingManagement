    <%@ page import="java.sql.*" %>  
    <%  
    String username=request.getParameter("q");  
    if(username==null||username.trim().equals("")){  
    out.print("<p>Please enter username!</p>");  
    }else{  
    try{  
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
    Connection con=DriverManager.getConnection("jdbc:sqlserver://192.168.1.30;" +
  			 "databaseName=IIAM;user=sa;password=admin123");  
    PreparedStatement ps=con.prepareStatement("select * from Users where username like '%"+username+"%'");  
    ResultSet rs=ps.executeQuery();  
      
    if(!rs.isBeforeFirst()) {      
     out.println("<p>No Record Found!</p>");   
    }else{  
    out.print("<table border='1' cellpadding='2' width='100%'>");  
    out.print("<tr><th>UserId</th><th>UserEnrollmentNumber</th><th>UserName</th><th>Email</th><th>Mobile</th><th>UserNickName</th><th>UserAddress</th></tr>");  
    while(rs.next()){  
    out.print("<tr><td>"+rs.getInt("userid")+"</td><td>"+rs.getString("userenrollmentnumber")+"</td><td>"+rs.getString("username")+"</td><td>"+rs.getString("email")+"</td><td>"+rs.getString("mobile")+"</td><td>"+rs.getString("usernickname")+"</td><td>"+rs.getString("useraddress")+"</td></tr>");  
    }  
    out.print("</table>");  
    }//end of else for rs.isBeforeFirst  
    con.close();  
    }catch(Exception e){out.print(e);}  
    }//end of else  
    %>  