<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Taches:</br>
<pre>
<% 
	List<String> tachesUr = (ArrayList<String>)session.getAttribute("taches");
	if(tachesUr==null){
		tachesUr = new ArrayList<String>();
	}
	if(tachesUr!=null){
		for(String items: tachesUr){
			out.println( items);	
		}
	}
%>
</pre>
	
</body>
</html>