<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.ProductModel" %>
    <%@ page import="java.util.ArrayList" %>
     <%if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
    else{
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="UTF-8">
    
    <script src="http://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>


 
    <link rel="stylesheet" href="css/styleform.css">
    <link rel="stylesheet" href="css/normalize.css">
<script src="js/prefixfreesearch.min.js"></script>
    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="css/styleside.css">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Showing all models</title>

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


<% ArrayList<ProductModel> prolist=(ArrayList<ProductModel>) request.getAttribute("promodellist");%>


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
<form action="ProductManagementController" method="get">
<table class="flat-table flat-table-1">
<tr>
    <td>Product List:</td> 
<td><select name="products" required>
 
<%for(ProductModel pro: prolist){%>

 
  
  <option value="<%=pro.getModelid() %>"><%=pro.getModelid()%></option>
  <%} %>
</select></td>
</tr>

<tr>
<td>Quantity:</td>

<td>
<select name="quantity" required>
 
<%for(int i=1;i<101;i++){%>
	<option value="<%=i%>"><%=i%></option>
  <%} %>
</select>

</td></tr>
<tr><td>Store:</td>
<td>
<select name="store" required>
 
  <option value="S4">Chennai</option>
  <option value="S5">Banglore</option>
  <option value="S6">Trivandrum</option>
  <option value="S3">Kolkata</option>
  <option value="S2">Delhi</option>
  <option value="S1">Mumbai</option>



</tr>


<tr><td><input type="reset" value="Reset"></td><td>
<input type="hidden" name="fun" value="addproduct" required>
<input type="submit" value="Add" onclick="validate_form()"></td></tr>
</form>
<!-- <tr><td colspan="2"><form method=get action=ProductManagementController>
<input type=hidden name=fun value=getmenu>
<input type=submit value="Available Products">
</form>
</td>
</tr> -->
</table>

<script src="js/indexside.js"></script>
</body>
</html>
<%}  %>