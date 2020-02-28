package pack1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action{

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String login=request.getParameter("login");
		String pwd=request.getParameter("pwd");
		LoginForm l=new LoginForm(login,pwd);
		
		ConnectionBaseDonnee bd=new ConnectionBaseDonnee();
		bd.setUser(login,pwd);
		
		//if(login!="" && pwd!=""){
		if(bd.VerifierUser()==true){
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("login",l);
			
			List<String> taches=new ArrayList<String>();	
			session.setAttribute("destaches",taches);
			
			return "/vue/welcome.jsp";
		}else{
			return "/vue/erreur.jsp";
		}
		
	}
}
