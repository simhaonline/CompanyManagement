<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.ArrayList,com.WorkFlow" %>  
  <%if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
    else{
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="UTF-8">
    
    <script src="http://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>
 	    <link rel="stylesheet" href="css/styletable.css">
 	
    
    <link rel="stylesheet" href="css/normalize.css">

    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="css/styleside.css">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search WorkFlows</title>

<style>
body {
    background: #ffffff url("images/1.png") no-repeat right top;
    
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
<div class="date"> </div><%
WorkFlow w=(WorkFlow)request.getAttribute("obj");

%>
<br><br><br><br><br><div align="center">
<table class="flat-table flat-table-1">
<thead><tr>
<td>WorkFlow Id</td>
<td>Product Id</td>
<td>Representative Id</td>
<td>WorkFlow Status</td>
<td>Reduction Limit</td></tr>
</thead>
<tbody>
<tr>
<td><%=w.getWorkFlowId() %></td>
<td><%=w.getProductId() %></td>
<td><%=w.getRepresentativeId() %></td>
<td><%=w.getWorkFlowStatus() %></td>
<td><%=w.getReductionLimit() %></td>

</tr>
</tbody>
</table>
</div>
<script src="js/indexside.js"></script>
</body>
</html>
<%}  %>