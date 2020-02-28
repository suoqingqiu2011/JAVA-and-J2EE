package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import entity.Produit;

public class produitForm extends Action{
		public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
			HttpSession session=request.getSession();
			
			
			List<String> l=new ArrayList<String>();
			session.setAttribute("pri",l);
			
			System.out.println("Commencer d'ajouter des produits!");
			
			return mapping.findForward("debutt");
			/*System.out.println("suis ds la helloworld");
			return mapping.findForward("suite");*/
		}

}
