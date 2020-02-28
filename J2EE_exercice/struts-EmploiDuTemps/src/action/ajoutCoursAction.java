package action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import compare.EtuComparator;
import entity.Attribution;
import entity.Enseignant;
import form.CoursActionForm;
import form.EnseignActionForm;

public class ajoutCoursAction extends Action{
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		CoursActionForm donneesCours=(CoursActionForm) form;
		System.out.println("suis ds la  "+ donneesCours.getEtu()+" "+donneesCours.getNomCours());
		
		HttpSession session=request.getSession();
		
		Attribution en=new Attribution(donneesCours.getEtu(),donneesCours.getNomCours());
		
		ArrayList<Attribution> tachesCours=(ArrayList<Attribution>)session.getAttribute("destachesCours");
		if(tachesCours==null){
			tachesCours=new ArrayList<Attribution>();
		}
		try{
			tachesCours.add(en);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		EtuComparator ec=new EtuComparator();
		Collections.sort(tachesCours,ec);
		
		session.setAttribute("tachesCours",tachesCours);
		
		ArrayList<Attribution> tachesC=(ArrayList<Attribution>)session.getAttribute("tachesCours");
		if(tachesC == null){
			tachesC = new ArrayList<Attribution>();
		}
		for(Attribution items: tachesC){
			System.out.println( items.getEtu()+" " +items.getNomCours());	
		}
	    
		return mapping.findForward("ajoutCours");
	}

}
