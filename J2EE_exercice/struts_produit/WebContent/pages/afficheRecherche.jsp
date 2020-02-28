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
Ref.  Nom  Prix
<pre>
<% 
	//float reff=Float.parseFloat((String)session.getAttribute("refference"));
	String reff=(String)session.getAttribute("refference");
	HashMap<String, Produit> tachesUr = (HashMap<String, Produit>)session.getAttribute("taches");
	if(tachesUr==null){
		tachesUr = new HashMap<String, Produit>();
	}
	/*
	Iterator it= tachesUr.entrySet().iterator();
	while(it.hasNext()){
		HashMap.Entry pairs =(HashMap.Entry)it.next();
		if(((String)pairs.getKey()).equals(reff.trim())){
		out.println(pairs.getKey()+" "+pairs.getValue());
		}
	}
	*/
	Set<String> keySet = tachesUr.keySet();
	for(Iterator<String> it = keySet.iterator();it.hasNext();) {
		String s = it.next();
		if(s.equals(reff.trim())){
		out.println(s+"  "+tachesUr.get(s).getNom()+"  "+tachesUr.get(s).getPrix());
		}
	}
%>
</pre>
<form action="return.do" method="post">
	<input type="submit" formaction="return.do" value="return">
</form>
</body>
</html>