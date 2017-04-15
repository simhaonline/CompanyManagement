<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<style>

    body {
  width: 350px;
  margin: 50px auto 0;
  background: #3399FF;
  font-family: 'Noto Sans', sans-serif;
  position: relative;
}

h1, p {
  text-align: center;
  color: #fff;
  
  font-size: 1.7rem;
  
}
h1 {
  margin: 30px 0 0;
  text-shadow: 1px 1px 4px rgba(0,0,0,0.3);
}
p {
  margin: 5px 0 0;
  font-size: 0.9rem;
}
[type="text"] {
  margin-top: 15px;
  width: 95%;
  padding: 8px 10px;
  border: 1px solid #777;
  border-radius: 3px;
}
[type="password"] {
  margin-top: 15px;
  width: 95%;
  padding: 8px 10px;
  border: 1px solid #777;
  border-radius: 3px;
}
button {
  position: absolute;
  right: -55px;
  bottom: -1px;
  height: 40px;
  width: 40px;
  border-radius: 50%;
  border: 0;
  border: 1px solid #5D7687;
  background: #e0f3fa;
  background: #e4f5fc;
  background: -moz-linear-gradient(top, #e4f5fc 0%, #bfe8f9 50%, #9fd8ef 51%, #2ab0ed 100%);
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e4f5fc), color-stop(50%,#bfe8f9), color-stop(51%,#9fd8ef), color-stop(100%,#2ab0ed));
  background: -webkit-linear-gradient(top, #e4f5fc 0%,#bfe8f9 50%,#9fd8ef 51%,#2ab0ed 100%);
  background: -o-linear-gradient(top, #e4f5fc 0%,#bfe8f9 50%,#9fd8ef 51%,#2ab0ed 100%);
  box-shadow: 0 0 10px rgba(0,0,0,0.2);
  font-size: 1.8rem;
  color: #eee;
  text-shadow: -1px 1px 2px rgba(0,0,0,0.3);
}

</style>
<%String msg=(String)request.getAttribute("msg"); %>
   <%if(msg!=null){ %>
   <div align=center><font color=#00FF00 size=4px>
 <%=msg%>   
 </font></div>
 <%} %>
</head>
<body><br><br><br><br><br><br><br>
<h1>Employee Login</h1>
<p></p>
<form action="UserManagementController" method="post">
<input type="text" placeholder="Username" name="uname" required/>
<input type="password" placeholder="Password" name="pwd" value="" required/>
 <input type="hidden" name="fun" value="login"/>
<button onclick="submit">></button>
</form>
    
</body>
</html>