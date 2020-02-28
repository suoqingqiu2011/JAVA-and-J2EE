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

import entity.Enseignant;
import entity.Etudiant;
import form.EnseignActionForm;
import form.EtuActionForm;

public class ajoutEnseignAction  extends Action{
	//HashMap<String,Produit> newmap=new HashMap<String,Produit>();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		EnseignActionForm donneesEn=(EnseignActionForm) form;
		System.out.println("suis ds la  "+ donneesEn.getNomEnseign()+" "+donneesEn.getCodeEnseign());
		
		HttpSession session=request.getSession();
		
		Enseignant en=new Enseignant(donneesEn.getNomEnseign(),donneesEn.getCodeEnseign());
		
		ArrayList<Enseignant> tachesEn=(ArrayList<Enseignant>)session.getAttribute("destachesEnseign");
		if(tachesEn==null){
			tachesEn=new ArrayList<Enseignant>();
		}
		try{
			tachesEn.add(en);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		session.setAttribute("tachesEnseign",tachesEn);
		
		ArrayList<Enseignant> tachesEng=(ArrayList<Enseignant>)session.getAttribute("tachesEnseign");
		if(tachesEng == null){
			tachesEng = new ArrayList<Enseignant>();
		}
		for(Enseignant items: tachesEng){
			System.out.println( items.getNomEnseign()+" " +items.getCodeEnseign());	
		}
	    
		return mapping.findForward("ajoutEnseign");
	}
}
