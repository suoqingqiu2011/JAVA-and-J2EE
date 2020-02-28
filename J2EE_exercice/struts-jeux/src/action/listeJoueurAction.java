package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.ListeActionForm;

public class listeJoueurAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ListeActionForm donnees=(ListeActionForm) form;
		System.out.println("suis ds la  "+ donnees.getPar());
		
		HttpSession session=request.getSession();
		
		System.out.println("Afficher la liste des joueurs!");
		
		session.setAttribute("stringPar",donnees.getPar());
		
		return mapping.findForward("afficheListe");
		
	}
}
