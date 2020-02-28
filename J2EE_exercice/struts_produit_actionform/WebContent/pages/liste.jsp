<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="entity.Produit" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Produits:</br>
ref.   Nom    Prix
<pre>
<% 
	HashMap<String, Produit> tachesUr = (HashMap<String, Produit>)session.getAttribute("taches");
	if(tachesUr==null){
		tachesUr = new HashMap<String, Produit>();
	}
	Set<String> keySet = tachesUr.keySet();
	for(Iterator<String> it = keySet.iterator();it.hasNext();) {
			String s = it.next();
			out.println(s+"  "+tachesUr.get(s).getNom()+"  "+tachesUr.get(s).getPrix());
	}
	/*
	Iterator it= tachesUr.entrySet().iterator();
	while(it.hasNext()){
		HashMap.Entry pairs =(HashMap.Entry)it.next();
		out.println(pairs.getKey()+" "+ tachesUr.get(pairs.getKey()).getNom()+" "+tachesUr.get(pairs.getKey()).getPrix());
	}*/
	
%>
</pre>
<form action="prix.do" method="post">
	<input type="submit"  value="calculer prix_moyen">
</form>
<form action="return.do" method="post">
	<input type="submit"  value="return">
</form>
</form>
</body>
</html>