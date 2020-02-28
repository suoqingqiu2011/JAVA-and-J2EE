package pack1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectionBd
 */
public class ConnectionBd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection maConnection=null;
	Statement canal;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionBd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			maConnection=DriverManager.getConnection("jdbc:mysql://192.168.30.23:3306/framework","root", "");
			System.out.println("connecte");
			
			canal=maConnection.createStatement();
			System.out.println("Success to load jdbc driver");
			
			//String sqlStr="Insert * into user";
		}catch(Exception e){
			System.out.println("e");
			System.out.println("Failed to load jdbc driver");
		}
	}

}
