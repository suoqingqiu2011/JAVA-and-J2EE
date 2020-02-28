package pack1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaisieAction implements Action{
	
	
	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();	
		session.setMaxInactiveInterval(600);	
		String al=request.getParameter("article");
		
		List<String> tachesAr=(ArrayList<String>)session.getAttribute("destaches");
		if(tachesAr==null){
			tachesAr=new ArrayList<String>();
		}
		tachesAr.add(al);
		
		ConnectionBaseDonnee bd=new ConnectionBaseDonnee();
		bd.setTaches(al);
		bd.InsertTaches();
		
		session.setAttribute("taches",tachesAr);
		
		List<String> tachesU =(ArrayList<String>)session.getAttribute("taches");
		if(tachesU == null){
			tachesU = new ArrayList<String>();
		}
		for(String items: tachesU){
			System.out.print( "\n"+items);	
		}
		
		return "/vue/welcome.jsp";	
		
	}

}
