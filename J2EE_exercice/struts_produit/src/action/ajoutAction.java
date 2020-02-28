package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import entity.Produit;
import form.HelloWorldForm;

public class ajoutAction extends Action{
	//HashMap<String,Produit> newmap=new HashMap<String,Produit>();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		/*HelloWorldForm donnees=(HelloWorldForm) form;
		HashMap<String, Produit> formValues = donnees.getFormValueMap();		
		
		System.out.println("suis ds la helloworld "+formValues);
		return mapping.findForward("ajoutt");*/
		
		HttpSession session=request.getSession();	
		session.setMaxInactiveInterval(600);	
		String nom=request.getParameter("nom");
		String ref=request.getParameter("ref");
		String px=request.getParameter("prix");
		float prix=Float.parseFloat(px);
		
		List<String> tachesUu =(ArrayList<String>)session.getAttribute("pri");
		if(tachesUu == null){
			tachesUu = new ArrayList<String>();
		}
		tachesUu.add(px);
		float sum=0;
		float moyen=0;
		if(tachesUu!=null){
			for(int i=0;i<tachesUu.size();i++){
				//System.out.println( Float.parseFloat(tachesU.get(i)));	
				sum+=Float.parseFloat(tachesUu.get(i));
			}
			moyen=sum/(tachesUu.size());
		}
		session.setAttribute("myn",moyen);
		
		Produit pro=new Produit(nom,prix);
		
		HashMap<String, Produit> tachesAr=(HashMap<String, Produit>)session.getAttribute("destaches");
		if(tachesAr==null){
			tachesAr=new HashMap<String,Produit>();
		}
		try{
			tachesAr.put(ref,pro);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		session.setAttribute("taches",tachesAr);
		
		HashMap<String, Produit> tachesU =(HashMap<String, Produit>)session.getAttribute("taches");
		if(tachesU == null){
			tachesU = new HashMap<String, Produit>();
		}
		System.out.println(tachesU.keySet());
	    System.out.println(tachesU.values());
	    
		return mapping.findForward("ajoutt");
	}
}
