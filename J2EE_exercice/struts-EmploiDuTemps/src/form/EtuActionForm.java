package form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class EtuActionForm extends ActionForm{
	private String nomEtu;
	private String prenom;
	private String numEtu;

	
	public void setNomEtu(String nomEtu){
		this.nomEtu=nomEtu;
	}
	public String getNomEtu(){
		return this.nomEtu;
	}
	public void setPrenom(String prenom){
		this.prenom=prenom;
	}
	public String getPrenom(){
		return this.prenom;
	}
	public void setNumEtu(String numEtu){
		this.numEtu=numEtu;
	}
	public String getNumEtu(){
		return this.numEtu;
	}
	
}
