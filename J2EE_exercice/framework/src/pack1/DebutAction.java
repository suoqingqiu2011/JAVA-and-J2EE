package pack1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DebutAction implements Action{
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		return "/vue/login.html";
	}
}