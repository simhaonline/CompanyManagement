<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.ArrayList,com.Bill" %> 
   <%if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
    else{
     %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>

<meta charset="UTF-8">
    
    <script src="http://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>


 
    <link rel="stylesheet" href="css/styletable.css">
    <link rel="stylesheet" href="css/normalize.css">
<script src="js/prefixfreesearch.min.js"></script>
    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="css/styleside.css">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill</title>

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
<br><br><br>
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

<%
Bill b=(Bill)request.getAttribute("billobj");

%>
<br><br><br><br><br><br>
<table class="flat-table flat-table-1">
<tr>
<td>BillID</td>
<td>Selling Date</td>
<td>Selling Amount</td>
<td>Customer Name</td>
<td>Customer Number</td>
<td>Customer Address</td>
<td>Pay Type</td>
<td>Product List</td>

<tr>
<td><%=b.getBillId() %></td>
<td><%=b.getSellingDate() %></td>
<td><%=b.getSellingAmt() %></td>
<td><%=b.getCusName() %></td>
<td><%=b.getCustNo() %></td>
<td><%=b.getCustAddr() %></td>
<td><%=b.getPaytype() %></td>
<td><%=b.getProdlist() %></td>

</tr>

</table>
<script src="js/indexside.js"></script>
</body>
</html>
<%}  %>