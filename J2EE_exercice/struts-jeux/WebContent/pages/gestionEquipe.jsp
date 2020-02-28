<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Joueur" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script language="JavaScript"> 
function act1() 
{ 
document.testForm.action="gestionSupprimerEp.do"; //testForm为form表单的name
document.testForm.submit();
}

</script>
</head>
<body>
<form name="testForm" action="gestionEp.do" method="post">
	equipe:<select name="equipe"><%
			ArrayList<String> tachesE =(ArrayList<String>)session.getAttribute("tachesEp");
			if(tachesE == null){
				tachesE = new ArrayList<String>();
			}
			for(String ep: tachesE){
        %>
            <option name="equipe" value="<%=ep %>"><%=ep %></option>
        <%} %>
         </select><br>
         
	joueur:<select name="joueur"><%
			ArrayList<Joueur> tachesJ =(ArrayList<Joueur>)session.getAttribute("tachesJr");
			if(tachesJ == null){
				tachesJ = new ArrayList<Joueur>();
			}
			for(Joueur jr: tachesJ){
        %>
            <option name="joueur" value="<%=jr.getNom()+" "+jr.getPrenom() %>"><%=jr.getNom()+" "+jr.getPrenom() %></option>
        <%} %>
         </select><br>
	
	
	
	<button type="submit"  value="ajouter">ajouter</button>
	<button type="submit" onClick="act1();" value="supprimer">Supprimer</button>
	
</form>
<form action="return.do" method="post">
	<input type="submit" value="return"> 
</form>

</body>
</html>