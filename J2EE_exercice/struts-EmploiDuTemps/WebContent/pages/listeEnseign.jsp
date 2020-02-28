<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Enseignant" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Etudiant:</br>
Nom  Code_enseignant
<pre>
<% 
	ArrayList<Enseignant> tachesEn=(ArrayList<Enseignant>)session.getAttribute("tachesEnseign");
	if(tachesEn == null){
		tachesEn = new ArrayList<Enseignant>();
	}
	for(Enseignant items: tachesEn){
		out.println( items.getNomEnseign()+" " +items.getCodeEnseign());	
	}	
%>
</pre>

<form action="return.do" method="post">
	<input type="submit"  value="return">
</form>

</body>
</html>