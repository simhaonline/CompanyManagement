<%@page import="com.Employee"%>
<%@page import="com.Store"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
    else{
     %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Detials </title>
<%Store st=(Store)request.getAttribute("st"); %>
<%Employee emp=(Employee)request.getAttribute("emp"); %>





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


<br><br><br><br>
<form >
<table border="1" align="center" class="flat-table flat-table-1">
<tr>
<tr>
	<td>Employee Id :</td>
	
   <td><input type="Textbox" name="empId" id="empId" value="<%=emp.getEmployeeId()%>" readonly ></td>
</tr>
  
<tr>
	<td>First Name :</td>
   <td><input type="Textbox" name="fname" id="fname" value="<%=emp.getFirstName() %>" readonly></td>
</tr>

   
<tr>
	<td>Last Name :</td>
   <td><input type="Textbox" name="lname" id="lname" value="<%=emp.getLastName()%>"readonly></td>
</tr>

   
<tr>
	<td>Gender :</td>
	
	<td>   <% if(emp.getGender().equals("male")){ %>
                   <input type="Radio" name="gender" value="male" checked="true"disabled>Male
                         <%}else{%>
                             <input type="Radio" name="gender" value="male"disabled>Male
                                  
                     <%} if(emp.getGender().equals("female")){%>
                   <input type=Radio name='gender' value='female' checked='true'disabled> Female
                     <%}else{%>
                   <input type=Radio name='gender' value='female'disabled> Female
                     <%}%>
	
	</td>
	
<tr>
	<td>DOB :</td>
    <td><input type="date" name="dob" id="dob" value="<%=emp.getDob()%>"readonly></td>
</tr>

<tr>
	<td>Mobile No :</td>
    <td><input type="Textbox" name="mobno" id="mobno"value="<%=emp.getMobNo()%>"readonly></td>
</tr>
    
<tr>
	<td>Address :</td> 
    <td><textarea name="address" id="address" readonly ><%=emp.getAddress()%></textarea></td>
</tr>

    
<tr>
	<td>Role :</td>
    <td> <select name="role">
     <option  selected="selected"value="<%=emp.getRole()%>"><%=emp.getRole() %></option> 
     </select></td> 
</tr>  
<%String role=(String)session.getAttribute("role"); %>
<% if(role.equalsIgnoreCase("Manager")) 
{%>
<tr>
	<td>Store</td>
    <td> <select name="store" required readonly>
     <option  selected="selected"><%=st.getStorename() %></option> 
  
     </select></td> 
</tr>  
<tr>
	<td>Region :</td>
    <td> <select name="region" required readonly>
     <option  selected="selected"><%=st.getRegionName() %></option> 
  
     </select></td> 
</tr>  
<%} 
else  if(role.equalsIgnoreCase("admin"))
	
{%>
	<%String role1=emp.getRole();
	if(role1.equalsIgnoreCase("manager")) {%>
	
<tr>
	<td>Store</td>
    <td> <select name="store" required readonly>
     <option  selected="selected"><%=st.getStorename() %></option> 
  
     </select></td> 
</tr>  
<tr>
	<td>Region :</td>
    <td> <select name="region" required readonly>
     <option  selected="selected"><%=st.getRegionName() %></option> 
  
     </select></td> 
</tr>  
<%} 
}%>
<%

    
%>



</table>
</form>

<script src="js/indexside.js"></script>
</body>
</html>
<%}  %>