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
<form action="ajoutJr.do" method="post">
	nom:<input type="text" name="nom"><br>
	prenom:<input type="text" name="prenom"><br>
	poste:<select name="poste">
		 <option name="poste">Attaquant</option>
		 <option name="poste">Defenseur</option>
		 <option name="poste">Gardien</option>	 
		 </select><br>
	age:<input type="text" name="age"><br>
	salaire:<input type="text" name="salaire"><br>
	<input type="submit"  value="ajouter">	
</form>

<form action="return.do" method="post">
	<input type="submit" value="return"> 
</form>
</body>
</html>