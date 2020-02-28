package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import entity.Gestion;
import form.GestionActionForm;

public class gestionSupprimerEquipeAction extends Action{
	static int i;
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		GestionActionForm donnees=(GestionActionForm) form;
		System.out.println("toto ds la  "+ donnees.getEquipe()+" "+donnees.getJoueur());
				
		HttpSession session=request.getSession();
		//Gestion gest=new Gestion(donnees.getEquipe(),donnees.getJoueur());
		
		ArrayList<Gestion> tachesGest=(ArrayList<Gestion>)session.getAttribute("destachesGest");
		if(tachesGest==null){
			tachesGest=new ArrayList<Gestion>();
		}
		try{
			
			String b=donnees.getEquipe()+donnees.getJoueur();
			
			if(valide(b.trim(),tachesGest,form,request,response)){	System.out.println("i: "+i);
				tachesGest.remove(tachesGest.get(i));
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
	    
		return mapping.findForward("gestionSupprimerEp");
	}
	
	public boolean valide(String b,ArrayList<Gestion> tachesGest,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			for(i=0;i<tachesGest.size();i++){ 
			if(b.equals((tachesGest.get(i).getEquipe()+tachesGest.get(i).getJoueur()).trim())){
				//System.out.println("here1 : "+tachesGest.get(i).getEquipe()+tachesGest.get(i).getJoueur());
				return true;		
			}else if(!b.equals((tachesGest.get(i).getEquipe()+tachesGest.get(i).getJoueur()).trim())){
				//System.out.println("here2 : "+tachesGest.get(i).getEquipe()+tachesGest.get(i).getJoueur());
				//System.out.println("i:: "+i);
				continue;
			}else{
				//System.out.println("Personne n'est pas dans l'equipe. refaitez pour le supprimer s'il vous plait!");		
				return false;
			}
		}			
			System.out.println("Personne n'est pas dans l'equipe. refaitez pour le supprimer s'il vous plait!");		
		return false;
		/*for(Gestion items: tachesGest){
			if(!(items.getEquipe()+items.getJoueur()).equals(b)){
				System.out.println("Personne n'est pas dans l'equipe. refaitez pour le supprimer s'il vous plait!");
				return false;
			}else
				return true;
		}			
		return true;*/
	
	}
}
