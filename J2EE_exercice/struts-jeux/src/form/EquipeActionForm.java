package form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class EquipeActionForm extends ActionForm{
	private String nomEquipe;

	public String toString(){
		return this.nomEquipe;
	}
	public void setNomEquipe(String nomEquipe){
		this.nomEquipe=nomEquipe;
	}
	public String getNomEquipe(){
		return this.nomEquipe;
	}
	
}
