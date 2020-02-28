package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.GestionActionForm;
import entity.Gestion;

public class gestionEquipeAction extends Action{
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		GestionActionForm donnees=(GestionActionForm) form;
		System.out.println("suis ds la  "+ donnees.getEquipe()+" "+donnees.getJoueur());
				
		HttpSession session=request.getSession();
		Gestion gest=new Gestion(donnees.getEquipe(),donnees.getJoueur());
		
		ArrayList<Gestion> tachesGest=(ArrayList<Gestion>)session.getAttribute("destachesGest");
		if(tachesGest==null){
			tachesGest=new ArrayList<Gestion>();
		}
		try{
			String b=donnees.getEquipe()+donnees.getJoueur();
				if(valide(b.trim(),tachesGest,form,request,response)){	
					tachesGest.add(gest);
				}
			
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		session.setAttribute("tachesGest",tachesGest);
		
		ArrayList<Gestion> tachesG=(ArrayList<Gestion>)session.getAttribute("tachesGest");
		if(tachesG==null){
			tachesG=new ArrayList<Gestion>();
		}
		
		for(Gestion items: tachesG){
			System.out.println( items.getEquipe()+" "+items.getJoueur());
		}	
		System.out.println("--------------------------");
	    
		return mapping.findForward("gestionEp");
	}
	
	public boolean valide(String b,ArrayList<Gestion> tachesGest,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		for(Gestion items: tachesGest){
			if((items.getEquipe()+items.getJoueur()).equals(b)){
				System.out.println("Personne a ижtиж dидjид dans l'equipe. refaitez pour l'ajouter s'il vous plait!");
				return false;
			}
			return true;
		}			
		return true;
	
	}
}
