<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Se connecter
<form name="form1" action="debut.perform" method="post">
	Login:<input type="text" id="login" class="noNull" name="login" size="1.5" placeholder="enter your nam" required/>
	Mot de passe:<input type="text" id="mdp" class="noNull" name="lmdp" size="1.5" placeholder="enter your psd" required/>
	<input type="submit" name="OK" value="Valider"/>
</form>
</body>
</html>