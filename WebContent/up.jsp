<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ProductManagementController" method="get">

ProductModel Id:<input type="text" name="pmid" id="pmid">


      <input type="hidden" name="fun" value="updateprod">
     <td>&nbsp;</td> 
     <td> <input type="submit" name="update" value="Update" >
</form>
</body>
</html>