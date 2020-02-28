package serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet1
 */
@WebServlet("/suivant")
public class servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String nom=request.getParameter("nom");
		String age=request.getParameter("age");
		String ville=request.getParameter("ville");
		//int age_tmp= Integer.valueOf(age) + 1;
		PrintWriter out= response.getWriter();
		
		out.println("<html><body><p> Bonjour "+nom+" Vous avez "+age+"</br>");
		//out.println("dans 1 an vous aurez "+age_tmp+"</br>");
			
		if(ville.compareTo("Versaiiles")==0){
			out.println("Au revoir "+ville+"</br>");	
		}else{
			out.println("Au revoir ma belle ville "+ville+"</br>");
		}
		out.println("</p></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);	
		
	}

}
