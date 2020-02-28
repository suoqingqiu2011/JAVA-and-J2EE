<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.IdentityHashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="entity.Joueur" %>
<%@ page import="entity.Gestion" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Affiche de la liste des joueurs par equipe<br>
<% 
	
	ArrayList<Gestion> tachesG =(ArrayList<Gestion>)session.getAttribute("tachesGest");
	if(tachesG == null){
		tachesG = new ArrayList<Gestion>();
	}
	
	IdentityHashMap<String, Gestion> tach=new IdentityHashMap<String,Gestion>();
	for(Gestion items: tachesG){
		tach.put(items.getEquipe(),items);	
	}
	Set set= tach.keySet();
	Object[] arr = set.toArray();
	Arrays.sort(arr);
	String tmp="";
	for(Object key:arr){
		if(!tmp.equals(key.toString().trim())){
			out.println("<br>\n---------------<br>");
			out.println(key+": <br>");
		}
		out.print(tach.get(key).getJoueur()+" ; ");
		tmp=key.toString().trim();
	}
%>	

<form action="return.do" method="post">
	<input type="submit" value="return"> 
</form>	
</body>
</html>