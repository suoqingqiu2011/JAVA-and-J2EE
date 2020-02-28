<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entity.Login" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Bonjour 
<% 
	Login l=(Login)session.getAttribute("lelogin");
	out.println(l.getLogin());
%>
	<form name="form1" action="add.perform" method="post">
	User:<input type="text" id="user" class="noNull" name="user" size="1.5" required/>
	<input type="submit" name="OK" value="Add"/></br>
	
	</form>
	<form name="form1" action="tach.perform" method="post">
	<input type="submit" name="OK" value="Afficher la liste des users"/>
	</form>
</body>
</html>