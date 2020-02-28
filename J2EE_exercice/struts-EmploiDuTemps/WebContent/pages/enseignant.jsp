<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Bienvenue Enseignant
<form action="listEnseign.do" method="post">
<input type="submit"  value="lister">
</form>
<form action="ajoutEnseign.do" method="post">
	nomEnseign:<input type="text" name="nomEnseign"><br>
	codeEnseign:<input type="text" name="codeEnseign"><br>
	<input type="submit"  value="ajouter">	
</form>
<form action="return.do" method="post">
	<input type="submit" value="return"> 
</form>
</body>
</html>