<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="entity.Produit" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
<% 	
		HashMap<String, Produit> taches = new HashMap<String,Produit>();	
		session.setAttribute("destaches",taches); 
%>
</pre>			
<form action="debut.do" method="post">
	<input type="submit" value="Entry">
</form>
</body>
</html>