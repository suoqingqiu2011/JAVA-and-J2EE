<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="entity.Etudiant" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Bienvenue Cours
<form action="listCoursEtu.do" method="post">
<input type="submit"  value="listerEtu">
</form>
<form action="listCours.do" method="post">
<input type="submit"  value="listerCours">
</form>
<form action="ajoutCours.do" method="post">
	etu:<select name="etu"><%
			HashMap<String, Etudiant> tachesEtu = (HashMap<String, Etudiant>)session.getAttribute("tachesEtu");
			Set<String> keySet = tachesEtu.keySet();
			for(Iterator<String> it = keySet.iterator();it.hasNext();) {
					String s = it.next();
        %>
            <option name="etu" value="<%=tachesEtu.get(s).getNom()+" "+tachesEtu.get(s).getPrenom() %>"><%=tachesEtu.get(s).getNom()+" "+tachesEtu.get(s).getPrenom() %></option>
        <%} %>
         </select><br>
	nomCours:<select name="nomCours">
			 <option name="nomCours">info</option>
			 <option name="nomCours">maths</option>
			 <option name="nomCours">chimie</option>
			 <option name="nomCours">physique</option>
			 </select><br>

	<input type="submit"  value="ajouter">	
</form>
<form action="return.do" method="post">
	<input type="submit" value="return"> 
</form>
</body>
</html>