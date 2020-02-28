package mb;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean //ce bean est dor¨¦navant g¨¦r¨¦ par JSF

@RequestScoped
public class produitBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private float prix;
	private String description;
	public ArrayList<String> tache;
	
	public HttpSession session;
	
	@SuppressWarnings("unchecked")
	public void getSession(){

		if(session==null){
			session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);	
		}
		tache=(ArrayList<String>)session.getAttribute("destaches");
		if(tache==null){
			tache = new ArrayList<String>();
		}
		
		tache.add(this.name);
		
		session.setAttribute("destaches",tache); 	
	}
	public void setList(){
		if(tache==null){
			tache = new ArrayList<String>();
		}
	}
	public ArrayList<String> getList(){
		return tache;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public float getPrix(){
		return prix;
	}
	
	public void setPrix(float prix){
		this.prix=prix;
	}
	
	public String getDescription(){
		this.getSession();
		return description;
		
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
}
