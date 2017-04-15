<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.ProductModel" %>
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

    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="css/styleside.css">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Model Added</title>

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

<script>


function validate_specification()
{
	
	var x= document.getElementById("mspec").value;


	if (x == ""||x == null)
	{
		document.getElementById("specificationChange").innerHTML="Please enter Specification";
		return false;
	}

	else
		{
		document.getElementById("specificationChange").innerHTML="";
		return true;
		}

}


function validate_description()
{
	
	var x= document.getElementById("mdesc").value;
	if (x == ""||x == null)
	{
		document.getElementById("descriptionChange").innerHTML="Please enter Description";
		return false;
	}

	else
		{
		document.getElementById("descriptionChange").innerHTML="";
		return true;
		}

}

function validate_price()
{
	var x=document.getElementById("price").value;							
	if(isNaN(x))
		{
		document.getElementById("priceChange").innerHTML="Please enter a valid Price";
		return false;
		}
	
    		else{
		document.getElementById("priceChange").innerHTML="";
		return true;
		}
}


function validate_form()
{
	return (   
       validate_specification()
	&& validate_description()
	&& validate_price()
		)
}






</script>

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




<% ProductModel promodel=(ProductModel)request.getAttribute("prd");%>
<br><br><br>
<form action="ProductManagementController" method="get">
<table class="flat-table flat-table-1">
<tr><td>Model id: </td><td><input type="text" name="modelid" value="<%=promodel.getModelid() %>" readonly></td></tr>
<tr><td>Model name: </td><td><input type="text" name="modelname" value="<%=promodel.getModelName() %>" readonly></td></tr>
<tr><td>Model Specification: </td><td><input type="text" name="modelspecification" id="mspec" value="<%=promodel.getSpecification() %>" required onkeyup="return validate_specification()"></td>
 <td> <span id="specificationChange" style="color:red"></span> </td>
</tr>
<tr><td>Model Description: </td><td><input type="text" name="modeldescription" id="mdesc" value="<%=promodel.getDescription() %>"  required onkeyup="return validate_description()"></td>
<td> <span id="descriptionChange" style="color:red"></span> </td>
</tr>
<tr><td>Price: </td><td><input type="text" name="price" id="price" value="<%=promodel.getPrice() %>" required onkeyup="return validate_price()"></td>
<td> <span id="priceChange" style="color:red"></span> </td>
</tr>
<tr><td>Category:</td><td>
<select name="category" required readonly>
  <option selected="selected"><%=promodel.getCategory() %></option>
 
</select></td></tr>
<input type="hidden" name="fun" value="compdetails" >
<tr><td><input type="reset" value="Reset"></td><td><input type="submit" value="Update"></td></tr>
<br>
</table>
</form>
  <script src="js/indexside.js"></script>
</body>
</html>
<%}  %>