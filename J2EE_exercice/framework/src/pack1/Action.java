package pack1;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Action {
	String perform(HttpServletRequest request,HttpServletResponse response);
}
