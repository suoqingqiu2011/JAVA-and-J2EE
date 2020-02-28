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

Etudiant:</br>
num etu.   Nom    Prenom
<pre>
<% 
	HashMap<String, Etudiant> tachesEtu = (HashMap<String, Etudiant>)session.getAttribute("tachesEtu");
	if(tachesEtu==null){
		tachesEtu = new HashMap<String, Etudiant>();
	}
	Set<String> keySet = tachesEtu.keySet();
	for(Iterator<String> it = keySet.iterator();it.hasNext();) {
			String s = it.next();
			out.println(s+"  "+tachesEtu.get(s).getNom()+"  "+tachesEtu.get(s).getPrenom());
	}
	/*
	Iterator it= tachesUr.entrySet().iterator();
	while(it.hasNext()){
		HashMap.Entry pairs =(HashMap.Entry)it.next();
		out.println(pairs.getKey()+" "+ tachesUr.get(pairs.getKey()).getNom()+" "+tachesUr.get(pairs.getKey()).getPrix());
	}*/
	
%>
</pre>

<form action="return.do" method="post">
	<input type="submit"  value="return">
</form>

</body>
</html>