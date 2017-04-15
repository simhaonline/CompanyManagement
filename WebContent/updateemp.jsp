<%@page import="com.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%Employee emp=(Employee)request.getAttribute("emp"); %>
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


function validate_firstname()
{
	
	var x= document.getElementById("firstname").value;
	var letter = /^[a-zA-Z]+$/;
	


	if (x == ""||x == null)
	{
		document.getElementById("firstnameChange").innerHTML="Please enter  Firstname";
		return false;
	}

		
	else if(!x.match(letter))
	{
		document.getElementById("firstnameChange").innerHTML="Please enter only alphabets";
		return false;

	}
	
	
	else
		{
		document.getElementById("firstnameChange").innerHTML="";
		return true;
		}

}

function validate_lastname()
{
	
	var y= document.getElementById("firstname").value;
	var x= document.getElementById("lastname").value;
	var letter = /^[a-zA-Z]+$/;
 	

	if (y == ""|| y == null)
	{
		document.getElementById("firstnameChange").innerHTML="Please enter First name";
		return false;
	}
	else if (x == ""||x == null)
	{
		document.getElementById("lastnameChange").innerHTML="Please enter Last name";
		return false;
	}

		
	else if(!x.match(letter))
	{
		document.getElementById("lastnameChange").innerHTML="Please enter only alphabets";
		return false;

	}
	
	
	else
		{
		document.getElementById("lastnameChange").innerHTML="";
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
	else if (x.length<20){
		document.getElementById("addressChange").innerHTML="Address must be min 20 characters long";
		document.myForm.address.focus();
		return false;
	}
	else
    {
		document.getElementById("addressChange").innerHTML="";
		return true;
		}
	
}


function validate_phone()
{
	var x=document.getElementById("phoneno").value;							
	var c=/^[0]?[789]\d{9}$/;
	if(isNaN(x))
		{
		document.getElementById("phoneChange").innerHTML="Please enter a valid mobile number";
		return false;
		}
	else if(isNaN(x) || (x.length!=10)){
		document.getElementById("phoneChange").innerHTML="Please enter a 10 digit mobile number";
		return false;
	}
	else if(!x.match(c)) {
	document.getElementById("phoneChange").innerHTML="Mobile number must start with 7 or 8 or 9";
		return false;
	}
    		else{
		document.getElementById("phoneChange").innerHTML="";
		return true;
		}
}



function validate_password()
{
	var x=document.getElementById("password").value;
	var decimal=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
	if(x.length<8)
		{
		document.getElementById("passwordChange").innerHTML="Password should contain atleast 8 characters";
		return false;
		}
	else if(x.length>15)
	{
	document.getElementById("passwordChange").innerHTML="Password should be between 8-15 characters";
	return false;
	}
	else if((x.length<8)||(x.search(decimal)))
		{
		document.getElementById("passwordChange").innerHTML="Password should contain one uppercase,one lowercase, one number and one special character";
		return false;
		}
	else
		{
		document.getElementById("passwordChange").innerHTML="";
		return true;
		}
}

function validate_form()
{
	return (   
       validate_firstname()
	&& validate_lastname()
	&& validate_gender()
	&& validate_phone()
	&& validate_address()
	&& validate_password()
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



<!--- info panel -->



<br><br><br>


<font color=black>
<form name="update" method="post" action="UserManagementController" >

<table class="flat-table flat-table-1" >

<tr>
	<td>Employee Id :</td>
	
   <td><input type="Textbox" name="empId" id="empId" value="<%=emp.getEmployeeId()%>" readonly ></td>
</tr>
  
<tr>
	<td>First Name :</td>
   <td><input type="Textbox" name="fname" id="firstname" value="<%=emp.getFirstName() %>" readonly onkeyup="return validate_firstname()"></td>
	<td> <span id="firstnameChange" style="color:red"></span> </td>
</tr>

   
<tr>
	<td>Last Name :</td>
   <td><input type="Textbox" name="lname" id="lastname" value="<%=emp.getLastName()%>" readonly onkeyup="return validate_lastname()"></td>
<td> <span id="lastnameChange" style="color:red"></span> </td>
</tr>

   
<tr>
	<td>Gender :</td>
	
	<td>   <% if(emp.getGender().equals("male")){ %>
                   <input type="Radio" name="gender" value="male" checked="true"readonly>Male
                         <%}else{%>
                             <input type="Radio" name="gender" value="male"readonly>Male
                                  
                     <%} if(emp.getGender().equals("female")){%>
                   <input type=Radio name='gender' value='female' checked='true'readonly> Female
                     <%}else{%>
                   <input type=Radio name='gender' value='female'readonly> Female
                     <%}%>
	
	</td>
	
<tr>
	<td>DOB :</td>
    <td><input type="date" name="dob" id="dob" value="<%=emp.getDob()%>"readonly ></td>
</tr>

<tr>
	<td>Mobile No :</td>
    <td><input type="Textbox" name="mobno" id="phoneno"value="<%=emp.getMobNo()%>" onkeyup="return validate_phone()"></td>
       <td> <span id="phoneChange" style="color:red"></span> </td>
</tr>
    
<tr>
	<td>Address :</td> 
    <td><textarea name="address" id="address" value="" onkeyup="return validate_address()"><%=emp.getAddress()%></textarea></td>
      <td> <span id="addressChange" style="color:red"></span></td>
</tr>

    
<tr>
	<td>Role :</td>
    <td> <select name="role" required readonly>
     <option  selected="selected"><%=emp.getRole() %></option> 
  
     </select></td> 
</tr>  
<tr>
	<td>Password :</td>
    <td><input type="password" name="pwd" id="password"value="<%= emp.getPassword()%>" onkeyup="return validate_password()"></td>
    <td> <span id="passwordChange" style="color:red"></span></td>
</tr> 


<tr>
	 <input type="hidden" name="fun" value="updatedemp">
	<td>&nbsp;</td>	
	<td><input type="submit" value="Update" onclick="validate_form()"></td>
</tr>

</table>
</form>
</font>
<script src="js/indexside.js"></script>
</body>
</html>
<%}  %>