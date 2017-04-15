<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.Bill" %>
    <%@ page import="com.Product" %>
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

<script>


function validate_custname()
{
	
	var x= document.getElementById("customername").value;
	var letter = /^[a-zA-Z]+$/;


	if (x == ""||x == null)
	{
		document.getElementById("custnameChange").innerHTML="Please enter Customer name";
		return false;
	}

		
	else if(!x.match(letter))
	{
		document.getElementById("custnameChange").innerHTML="Please enter only alphabets";
		return false;

	}
	
	
	else
		{
		document.getElementById("custnameChange").innerHTML="";
		return true;
		}

}

function validate_phone()
{
	var y= document.getElementById("customername").value;
	var x=document.getElementById("cno").value;							
	var c=/^[0]?[789]\d{9}$/;
	
	if (y == ""|| y == null)
	{
		document.getElementById("custnameChange").innerHTML="Please enter Customer name";
		return false;
	}
	
	
	else if(isNaN(x))
		{
		document.getElementById("mobChange").innerHTML="Please enter a valid mobile number";
		return false;
		}
	else if(isNaN(x) || (x.length!=10))
	{
		document.getElementById("mobChange").innerHTML="Please enter a 10 digit mobile number";
		return false;
	}
	else if(!x.match(c)) 
	{
	document.getElementById("mobChange").innerHTML="Mobile number must start with 7 or 8 or 9";
		return false;
	}
    		else
    	{
		document.getElementById("mobChange").innerHTML="";
		return true;
		}
}

	

function validate_address()
{
	var x=document.getElementById("address").value;
	if (x == ""||x ==null){
		document.getElementById("addressChange").innerHTML="Please enter address";
		return false;
	}
	else if (x.length>20){
		document.getElementById("addressChange").innerHTML="Address can have a maximum of 40 characters";
		document.myForm.address.focus();
		return false;
	}
	else
    {
		document.getElementById("addressChange").innerHTML="";
		return true;
		}
	
}

function validate_sell()
{
	

	var x= document.getElementById("selldate").value;
	 var d =new Date();
     
     var res = x.split("-");

    var year=d.getFullYear()-res[0];
    
    var month=d.getMonth()-res[1]+1;

    var date=d.getDate()-res[2];

    var calculate=((year*364)+(month*30)+date)

	if (x == ""||x ==null){
		document.getElementById("sellChange").innerHTML="Please enter Date";
		return false;
	}
      
       
      else if(calculate!= 0){

     document.getElementById("sellChange").innerHTML="Date should be Current date ";
  	return false;

  

}
else{
	document.getElementById("sellChange").innerHTML="";
	return true;
}
}


function validate_reduction()
{
	var x=document.getElementById("reduction").value;
	
	if (x == ""||x ==null){
		document.getElementById("reduceChange").innerHTML="Please enter reduction";
		return false;
	}
	
	else if(isNaN(x))
		{
		document.getElementById("reduceChange").innerHTML="Please enter a valid number";
		return false;
		}

    		else{
		document.getElementById("reduceChange").innerHTML="";
		return true;
		}
}

function validate_product()
{
	var x=document.getElementById("products").value;
	
	if (x == ""||x ==null){
		document.getElementById("productChange").innerHTML="Please select 1 product";
		return false;
	}
	
	
    		else{
		document.getElementById("productChange").innerHTML="";
		return true;
		}
}


function validate_form()
{
	return (   
       validate_custname()
	&& validate_phone()
	&& validate_address()
	&& validate_reduction()
	&&  validate_sell()
	&& validate_product()
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


<% ArrayList<Product> prolist=(ArrayList<Product>) request.getAttribute("prolist");%>
<font color="black">

<form name="bill" method="post" action="Billmanager" >
<br><br>
<table  class="flat-table flat-table-1">
<tr>
   <td>Customer Name :</td>
   <td><input type="Textbox" name="cname" id="customername"   onkeyup="return validate_custname()"></td>
   <td> <span id="custnameChange" style="color:red"></span> </td>
<tr>
    <td>Mobile Number :</td>
    <td><input type="Textbox" name="cno" id="cno"  onkeyup="return validate_phone()"></td>
     <td> <span id="mobChange" style="color:red"></span> </td>
<tr/>
<tr>
     <td>Customer Address :</td> 
     <td><textarea name="address" id="address"   onkeyup="return validate_address()"></textarea></td>
     <td> <span id="addressChange" style="color:red"></span></td>
</tr>
<tr>
    <td>Selling date : </td>
    <td><input type="date" name="selldate" id="selldate"  onchange="return validate_sell()"></td>
       <td> <span id="sellChange" style="color:red"></span> </td>
</tr>
<tr>
     <td>Pay Type:</td>

     <td><select name="paytype" >
  <option value="cash">cash</option>
  <option value="cheque">cheque</option>
</select></td>

</tr>
<tr>
    <td>Product List:</td> 
<td><select name="products" id="products" multiple  onkeyup="return validate_product()" >

 

<%for(Product pro:prolist){%>

 
  
  <option value="<%=pro.getProductId() %>"><%=pro.getInventory().getProdModel().getModelName()%> (<%=pro.getProductId() %>)</option>
  <%} %>
  
  
 
  
</select></td>
<td> <span id="productChange" style="color:red"></span> </td>
</tr>


    <td>Reduction :</td>
    <td><input type="Textbox" name="reduction" id="reduction"   onkeyup="return validate_reduction()"></td>
     <td> <span id="reduceChange" style="color:red"></span> </td>
<tr/>
<tr>
	
      <input type="hidden" name="fun" value="addbill">
     <td>&nbsp;</td> 
      <td> <input type="reset" name="Reset" >
      <input type="submit" name="submit" onclick="return validate_form()"><td/></form>
     
</tr>
<tr>
<td colspan="3" style="text-align:center">
<form method=get action=ProductManagementController>
<input type=hidden name=fun value=getmenu>
<input type=submit value="Available Products">
</form>
</td>
</tr>
</table>
<script src="js/indexside.js"></script>
</body>
</html>
<%}  %>