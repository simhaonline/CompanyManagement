<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.ArrayList,com.Bill" %>  
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
<title>View Bills</title>

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

input[type='text']{
    
    color: #333;
    width: 50%;
    height: 38px;
   	margin-left: 25%;
    
    
    padding-left: 10px;
    padding-right: 10px;

    transition: box-shadow 320ms;

    box-shadow: 0px 0px 8px 10px rgba(0,0,0,0.1);
    
    border-radius: 3px;
    font-size: 18px;
    border: 0px;
}

input[type='text']:focus {

    outline: 0px;
    outline-offset: 0px;
    box-shadow: 0px 0px 1px 5px rgba(0,0,0,0.12);
}

input:-moz-placeholder {
    color: #cccccc;
}

.paging-nav {
  text-align: right;
  padding-top: 2px;
}

.paging-nav a {
  margin: auto 1px;
  text-decoration: none;
  display: inline-block;
  padding: 1px 7px;
  background: #91b9e6;
  color: white;
  border-radius: 3px;
}

.paging-nav .selected-page {
  background: #187ed5;
  font-weight: bold;
}

.paging-nav,
#tableData {
  width: 400px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
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
 <br><br><br>
 <div >
<input type='text' id='search-box' placeholder='Search' ></div>

<%
ArrayList<Bill> b=(ArrayList<Bill>)request.getAttribute("bill");

%>
<br><br><div align="center">
<table class="flat-table flat-table-1" id="tableData">
<thead><tr>
<td>BillID</td>
<td>Selling Date (yy-mm-dd)</td>
<td>Selling Amount</td>
<td>Customer Name</td>
<td>Customer Number</td>
<td>Customer Address</td>
<td>Pay Type</td>
<td>Product List</td>
</thead>
<tbody>
<tr>
<% for(Bill b1:b){%>
<td><%=b1.getBillId() %></td>
<td><%=b1.getSellingDate() %></td>
<td><%=b1.getSellingAmt() %></td>
<td><%=b1.getCusName() %></td>
<td><%=b1.getCustNo() %></td>
<td><%=b1.getCustAddr() %></td>
<td><%=b1.getPaytype() %></td>
<td><%=b1.getProdlist() %></td>

</tr>
<% 
}
%>
</tbody>
</table>
</div>
<script src='js/jquerysearch.min.js'></script>
        <script src="js/indexsearch.js"></script>
<script src="js/indexside.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/paging.js"></script> 
<script type="text/javascript">
            $(document).ready(function() {
                $('#tableData').paging({limit:10});
            });
        </script>

</body>
</html>

<%}  %>