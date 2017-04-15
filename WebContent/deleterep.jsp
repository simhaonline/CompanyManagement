<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
    else{
     %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Representative</title>
<%HttpSession sesssion=request.getSession();%>
<%@page import="java.util.ArrayList" %>



<meta charset="UTF-8">
    
    <script src="http://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>


 
    <link rel="stylesheet" href="css/styleform.css">
    <link rel="stylesheet" href="css/normalize.css">
<script src="js/prefixfreesearch.min.js"></script>
    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="css/styleside.css">


<style>

body {
    background: #ffffff url("images/1.png") no-repeat right top;
    background-attachment: fixed;
    
}
header {
  background-color: rgba(0,0,0,0.2);
  height: 35px;
  line-height: 35px;
  padding: 0 10px;
  color: white;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 2
}




</style>
</head>
<body>
<header>
  Hello <%if(session.getAttribute("name")!=null){%><%=session.getAttribute("name") %><%} %> (<%=session.getAttribute("role") %>)
 </header>
<script>
function validate_form()
{
	var oldemp=document.getElementById("oldemp").value;
	var newemp=document.getElementById("newemp").value;

   if(oldemp==newemp)
{
	   document.getElementById("new").innerHTML="Both Employee cant be same";
       return false;
     }
else
	{

return true;
}

}
</script>
<div class="box">
  
<nav role='navigation'>
  <ul>
    <li>
      <a href="javascript:history.go(-1)">
        <i class="fa fa-arrow-left fa-fw icon"></i>
        Back
      </a>
    </li>
    <li>
      <a href="home.jsp">
        <i class="fa fa-home fa-fw icon"></i>
        Home
      </a>
    </li>
    <li>
      <a href="UserManagementController?fun=logout">
        <i class="fa fa-sign-out fa-fw icon"></i>
        Log Out
      </a>
    </li>
   
  </ul>
</nav>   
</div>


<!-- date -->
<div class="date"> </div>



<br>
<br>
<br><br>
<br>
<br><br>
<br>
<br>
<table border="1" align="center" class="flat-table flat-table-1">
<form action="UserManagementController">
<%ArrayList<String> emplist=(ArrayList<String>)request.getAttribute("empList"); %>
<%HttpSession session1 =request.getSession() ;%>
<input type="hidden" name="empid"  value="<%= session1.getAttribute("username")%>"/>

<tr>
	<td>Employee Id</td>
	

	
    <td> <select name="oldemp" id="oldemp"required  >
<% for(String name:emplist){%>
    <option value="<%=name%>"><%=name%></option>
<%} %>
     </select></td> 

<tr>
	<td>Reassigning Employee Id</td>
   <td> <select name="newemp" id="newemp"  required >
<% for(String name:emplist){%>
    <option value="<%=name%>"><%=name%></option>
<%} %>
     </select></td> 
</tr>



<input type="hidden"name="fun"value="deleteemp"/>

<tr>
	
   <td><input type="submit"value="Delete" onclick="return validate_form()"/></td>
</tr>

</form>
<div id="new" style="color:red" align=center ></div>
</table>

<script src="js/indexside.js"></script>
</body>
</html>

<%}  %>