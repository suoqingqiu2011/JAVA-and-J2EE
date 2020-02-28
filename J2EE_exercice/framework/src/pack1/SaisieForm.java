package pack1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaisieForm implements Action{
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		return "/vue/AfficheTaches.jsp";
	}
}
