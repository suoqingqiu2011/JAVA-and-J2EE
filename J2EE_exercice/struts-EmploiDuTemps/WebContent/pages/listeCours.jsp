<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.IdentityHashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.sun.javafx.webkit.KeyCodeMap.Entry" %>
<%@ page import="entity.Attribution" %>
<%@ page import="compare.CoursComparator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Cours:</br>
Nom  Cours
<pre>
<% 

	ArrayList<Attribution> tachesCours=(ArrayList<Attribution>)session.getAttribute("tachesCours");
	if(tachesCours == null){
		tachesCours = new ArrayList<Attribution>();
	}
	/*CoursComparator cc=new CoursComparator();
	
	Collections.sort(tachesCours,cc);
	
	for(Attribution items: tachesCours){
		out.println( items.getEtu()+" " +items.getNomCours());	
	}	
	*/
	IdentityHashMap<String, String> tach=new IdentityHashMap<String,String>();
	for(Attribution items: tachesCours){
		tach.put(items.getNomCours(),items.getEtu());	
	}
	Set set= tach.keySet();
	Object[] arr = set.toArray();
	Arrays.sort(arr);
	String tmp="";
	for(Object key:arr){
		if(!tmp.equals(key.toString().trim())){
			out.println("\n---------------");
			out.println(key+": ");
		}
		out.print(tach.get(key)+" ; ");
		tmp=key.toString().trim();
	}
%>
</pre>

<form action="return.do" method="post">
	<input type="submit"  value="return">
</form>
</body>
</html>