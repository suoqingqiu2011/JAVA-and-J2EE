package action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import entity.Joueur;
import form.JoueurActionForm;

public class ajoutJoueurAction extends Action{
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		JoueurActionForm donnees=(JoueurActionForm) form;
		System.out.println("suis ds la  "+ donnees.getNom()+" "+donnees.getPrenom()+" "+donnees.getPoste()+" "+donnees.getAge()+" "+donnees.getSalaire());
		
		HttpSession session=request.getSession();
		
		Joueur jr=new Joueur(donnees.getNom(),donnees.getPrenom(),donnees.getPoste(),donnees.getAge(),donnees.getSalaire());
		
		ArrayList<Joueur> tachesJr=(ArrayList<Joueur>)session.getAttribute("destachesJr");
		if(tachesJr==null){
			tachesJr=new ArrayList<Joueur>();
		}
		try{
			tachesJr.add(jr);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		session.setAttribute("tachesJr",tachesJr);
		
		ArrayList<Joueur> tachesJ =(ArrayList<Joueur>)session.getAttribute("tachesJr");
		if(tachesJ == null){
			tachesJ = new ArrayList<Joueur>();
		}
		
		for(Joueur items: tachesJ){
			System.out.println( items.getNom()+" "+items.getPrenom()+" "+items.getPoste()+" "+items.getAge()+" "+items.getSalaire());
			
		}	
		System.out.println("--------------------------");
	    
		return mapping.findForward("ajoutJr");
	}
}
