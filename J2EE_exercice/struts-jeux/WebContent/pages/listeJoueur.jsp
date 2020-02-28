<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Creation des joueurs
<form action="afficheListe.do" method="post">
	par:<select name="par">
		 <option name="par">Tri par poste</option>
		 <option name="par">Tri par age croissant</option>
		 <option name="par">Tri par salaire decroissant</option>	 
		 </select><br>
	<input type="submit"  value="lister">	
</form>

<form action="return.do" method="post">
	<input type="submit" value="return"> 
</form>
</body>
</html>