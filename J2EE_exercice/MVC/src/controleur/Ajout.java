package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ajout
 */
@WebServlet("/AjoutServlet")
public class Ajout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Object String = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajout() {
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
		//doGet(request, response);
		String ur=request.getParameter("user");
		HttpSession session=request.getSession();
		ArrayList<String> tachesUr= new ArrayList<String>();
		tachesUr=(ArrayList<String>)session.getAttribute("lestaches");
		tachesUr.add(ur);
		session.setAttribute("taches",tachesUr);
		this.getServletContext().getRequestDispatcher("/LoginCorrect.jsp").forward(request, response);
	}

}
