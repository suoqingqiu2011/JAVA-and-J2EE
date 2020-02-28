<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="pack1.LoginForm" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Bienvenue 
<% 
	LoginForm login=(LoginForm)session.getAttribute("login");
	
	out.println(login.getLogin());
	//session.setAttribute("login",login.getLogin());
%>
	<form name="form1" action="saisie.perform" method="post">
	Article:<input type="text" id="article" class="noNull" name="article" size="1.5" required>
	<input type="submit" name="OK" value="Add">
	<br>	
	</form>

	<form name="form1" action="tach.perform" method="post">
	<input type="submit" name="OK" value="Afficher la liste des users"/>
	</form>
	
</body>
</html>