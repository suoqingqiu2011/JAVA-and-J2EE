package mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean //ce bean est dor¨¦navant g¨¦r¨¦ par JSF

@RequestScoped
public class helloBean {
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
}
