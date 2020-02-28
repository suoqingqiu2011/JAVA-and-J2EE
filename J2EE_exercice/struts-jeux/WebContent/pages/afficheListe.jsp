<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.IdentityHashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="entity.Joueur" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Affiche de la liste<br>
<% 
	String par=(String)session.getAttribute("stringPar");
	
	ArrayList<Joueur> tachesJ =(ArrayList<Joueur>)session.getAttribute("tachesJr");
	if(tachesJ == null){
		tachesJ = new ArrayList<Joueur>();
	}
	System.out.println("par: "+par);
	out.println(par+":<br>");
	switch (par){
		case "Tri par poste":
			IdentityHashMap<String, Joueur> tach=new IdentityHashMap<String,Joueur>();
			for(Joueur items: tachesJ){
				tach.put(items.getPoste(),items);	
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
				out.print(tach.get(key).getNom()+" "+tach.get(key).getPrenom()+" ; ");
				tmp=key.toString().trim();
			}
			
			break;
		case "Tri par age croissant":
			Collections.sort(tachesJ, new Comparator<Joueur>() {
	            @Override
	            public int compare(Joueur s1, Joueur s2) {
	                  if(s1.getAge()-(s2.getAge())!=0){
	                       return s1.getAge()-(s2.getAge());
	                    }else{
	                       return  s1.getNom().compareTo(s2.getNom());
	                     }
	            }
	        });
	        
	        for (Joueur s : tachesJ) {
	            out.println(s.getNom()+" "+s.getPrenom() + "---" + s.getAge()+"<br>");
	        }
			break;
		case "Tri par salaire decroissant":
			Collections.sort(tachesJ, new Comparator<Joueur>() {
	            @Override
	            public int compare(Joueur s1, Joueur s2) {
	                  if(s2.getSalaire()-(s1.getSalaire())!=0){
	                       return (int)(s2.getSalaire()-(s1.getSalaire()));
	                    }else{
	                       return  s1.getNom().compareTo(s2.getNom());
	                     }
	            }
	        });
	        
	        for (Joueur s : tachesJ) {
	            out.println(s.getNom()+" "+s.getPrenom() + "---" + s.getSalaire()+"<br>");
	        }
			break;
	}
	
%>
<form action="return.do" method="post">
	<input type="submit" value="return"> 
</form>
</body>
</html>