<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Joueur" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
<% 	
%>
</pre>			
<form action="debutJr.do" method="post">
	<input type="submit" value="Creation des joueurs">
</form>
<form action="debutLister.do" method="post">
	<input type="submit" value="Lister des joueurs">
</form>

<form action="debutEp.do" method="post">
	<input type="submit" value="Creation Equipe">
</form>
<form action="debutGestionEp.do" method="post">
	<input type="submit" value="Gestion Equipe">
</form>
<form action="debutListerEp.do" method="post">
	<input type="submit" value="Lister Equipe">
</form>

</body>
</html>