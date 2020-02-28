<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Etudiant" %>
<%@ page import="entity.Enseignant" %>
<%@ page import="entity.Attribution" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
<% 	
		/*HashMap<String, Etudiant> tachesEtu = new HashMap<String,Etudiant>();	
		session.setAttribute("destachesEtu",tachesEtu); 
		
		ArrayList<Enseignant> tachesEnseign= new ArrayList<Enseignant>();
		session.setAttribute("destachesEnseign",tachesEnseign);
		
		ArrayList<Attribution> tachesCours= new ArrayList<Attribution>();
		session.setAttribute("destachesEnseign",tachesCours);
		
		HashMap<String, String> tachesEnseign = new HashMap<String,String>();	
		session.setAttribute("destachesEnseign",tachesEnseign);
		
		HashMap<String, String> tachesCours = new HashMap<String,String>();	
		session.setAttribute("destachesEnseign",tachesCours);*/
		
%>
</pre>			
<form action="debutEtu.do" method="post">
	<input type="submit" value="EntryEtudiant">
</form>
<form action="debutEnseign.do" method="post">
	<input type="submit" value="EntryEnseignant">
</form>
<form action="debutCours.do" method="post">
	<input type="submit" value="EntryAttribution">
</form>
</body>
</html>