package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class affichRechercheAction extends Action{

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//HelloWorldForm donnees=(HelloWorldForm) form;
		
		HttpSession session=request.getSession();	
		session.setMaxInactiveInterval(600);	
		String ref=request.getParameter("refRecherche");
		session.setAttribute("refference",ref);
		
		System.out.println("suis ds la recherche ");
		return mapping.findForward("refRecherche");
		
	}
}
