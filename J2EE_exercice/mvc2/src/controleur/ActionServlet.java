package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Login;

/**
 * Servlet implementation class ActionServlet
 */
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getServletPath());
		String login=request.getParameter("login");
		String mdp=request.getParameter("mdp");
		
		
		if(request.getServletPath().equals("/debut.perform")){		
			Login log= new Login(login,mdp);
			HttpSession session=request.getSession();
			session.setAttribute("lelogin",log);
			if(log.Valide()){
				/*
				ArrayList<String> taches= new ArrayList<String>();
				session.setAttribute("lestaches",taches);
			
				this.getServletContext().getRequestDispatcher("/LoginSuccess.jsp").forward(request, response);*/
				this.getServletContext().getRequestDispatcher("/debut").forward(request, response);
			}
		}else if(request.getServletPath().equals("/add.perform")){
				/*String ur=request.getParameter("user");
				ArrayList<String> tachesUr= new ArrayList<String>();
				tachesUr=(ArrayList<String>)session.getAttribute("lestaches");
				tachesUr.add(ur);
				session.setAttribute("taches",tachesUr);
				this.getServletContext().getRequestDispatcher("/LoginSuccess.jsp").forward(request, response);*/
				
				this.getServletContext().getRequestDispatcher("/add").forward(request, response);
		}else if(request.getServletPath().equals("/tach.perform")){
			  /*this.getServletContext().getRequestDispatcher("/AfficheTaches.jsp").forward(request, response);*/
			    this.getServletContext().getRequestDispatcher("/affiche").forward(request, response);
				
		}else{
				this.getServletContext().getRequestDispatcher("/LoginFail.jsp").forward(request, response);
			
			
		}
	}

}
