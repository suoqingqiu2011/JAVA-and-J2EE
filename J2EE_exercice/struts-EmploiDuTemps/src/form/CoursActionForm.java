package form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class CoursActionForm extends ActionForm{
	private String etu;
	private String nomCours;

	public void setEtu(String etu){
		this.etu=etu;
	}
	public String getEtu(){
		return this.etu;
	}
	public void setNomCours(String nomCours){
		this.nomCours=nomCours;
	}
	public String getNomCours(){
		return this.nomCours;
	}
}
