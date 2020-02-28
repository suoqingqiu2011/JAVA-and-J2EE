package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.HelloWorldForm;

public class HelloWorldAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		HelloWorldForm donnees=(HelloWorldForm) form;
		System.out.println("suis ds la helloworld "+donnees.getUsername()+" "+donnees.getPassword());
		return mapping.findForward("suite");
		/*System.out.println("suis ds la helloworld");
		return mapping.findForward("suite");*/
	}
}
