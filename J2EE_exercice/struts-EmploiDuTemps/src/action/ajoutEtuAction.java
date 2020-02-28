package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import entity.Etudiant;
import form.EtuActionForm;

public class ajoutEtuAction extends Action{
	//HashMap<String,Produit> newmap=new HashMap<String,Produit>();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		EtuActionForm donnees=(EtuActionForm) form;
		System.out.println("suis ds la  "+ donnees.getNomEtu()+" "+donnees.getPrenom()+" "+donnees.getNumEtu());
		
		HttpSession session=request.getSession();
		
		Etudiant etu=new Etudiant(donnees.getNomEtu(),donnees.getPrenom());
		
		HashMap<String, Etudiant> tachesEtu=(HashMap<String, Etudiant>)session.getAttribute("destachesEtu");
		if(tachesEtu==null){
			tachesEtu=new HashMap<String,Etudiant>();
		}
		try{
			tachesEtu.put(donnees.getNumEtu(),etu);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		session.setAttribute("tachesEtu",tachesEtu);
		
		HashMap<String, Etudiant> tachesU =(HashMap<String, Etudiant>)session.getAttribute("tachesEtu");
		if(tachesU == null){
			tachesU = new HashMap<String, Etudiant>();
		}
		System.out.println(tachesU.keySet());
	    System.out.println(tachesU.values());
	    
		return mapping.findForward("ajoutEtu");
	}

}
