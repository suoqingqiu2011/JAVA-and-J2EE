package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.EquipeActionForm;

public class ajoutEquipeAction extends Action{
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		EquipeActionForm donnees=(EquipeActionForm) form;
		System.out.println("suis ds la  "+ donnees.getNomEquipe());
				
		HttpSession session=request.getSession();
		
		ArrayList<String> tachesEp=(ArrayList<String>)session.getAttribute("destachesEp");
		if(tachesEp==null){
			tachesEp=new ArrayList<String>();
		}
		try{
			if(valide(donnees.getNomEquipe().trim(),tachesEp,form,request,response)){	
				tachesEp.add(donnees.getNomEquipe());
			}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		session.setAttribute("tachesEp",tachesEp);
		
		ArrayList<String> tachesE=(ArrayList<String>)session.getAttribute("tachesEp");
		if(tachesE==null){
			tachesE=new ArrayList<String>();
		}
		
		for(String items: tachesE){
			System.out.println( items);
		}	
		System.out.println("--------------------------");
	    
		return mapping.findForward("ajoutEp");
	}
	
	public boolean valide(String a,ArrayList<String> tachesEp,ActionForm form,HttpServletRequest request,HttpServletResponse response){

			for(String items: tachesEp){
				if(items.equals(a)){
					System.out.println("l'¨¦quipe est repet¨¦. retaper s'il vous plait!");
					return false;
				}
				return true;
			}			
			return true;
		
	}
}
