<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Joueur" %>
<%@ page import="entity.Gestion" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
<% 	
		
		ArrayList<Joueur> tachesJr= new ArrayList<Joueur>();
		session.setAttribute("destachesJr",tachesJr);
		
		ArrayList<String> tachesEp= new ArrayList<String>();
		session.setAttribute("destachesEp",tachesEp);
		
		ArrayList<Gestion> tachesGest= new ArrayList<Gestion>();
		session.setAttribute("destachesGest",tachesGest);
%>
</pre>			
<form action="index.do" method="post">
	<input type="submit" value="Entry">
</form>
</body>
</html>