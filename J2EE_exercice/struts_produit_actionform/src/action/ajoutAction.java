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
		HelloWorldForm donnees=(HelloWorldForm) form;
		System.out.println("suis ds la helloworld "+ donnees.getNom()+" "+donnees.getRef()+" "+donnees.getPrix());
		
		HttpSession session=request.getSession();
		
		Produit pro=new Produit(donnees.getNom(),donnees.getPrix());
		
		HashMap<String, Produit> tachesAr=(HashMap<String, Produit>)session.getAttribute("destaches");
		if(tachesAr==null){
			tachesAr=new HashMap<String,Produit>();
		}
		try{
			tachesAr.put(donnees.getRef(),pro);
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
