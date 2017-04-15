<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%if(session.getAttribute("username")==null){response.sendRedirect("login.jsp");}
    else{
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProductModel Deletion Details  </title>
</head>
<body>

<% boolean flag=(Boolean)request.getAttribute("flag");

if(flag)
{%>
<h1>ProductModels Deleted Successfully </h1>
<%} else
{%>
<h1>ProductModels Deletion UnSuccesssull </h1>
<%} %>

</body>
</html>
<%}%>