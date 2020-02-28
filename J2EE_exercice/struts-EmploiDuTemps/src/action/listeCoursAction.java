package action;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class listeCoursAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//HelloWorldForm donnees=(HelloWorldForm) form;
		System.out.println("suis ds la list cours/etu");
	
		return mapping.findForward("listCours");
		
	}

}
