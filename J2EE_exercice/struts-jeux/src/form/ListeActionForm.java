package form;

import org.apache.struts.action.ActionForm;

public class ListeActionForm extends ActionForm{
	private String par;

	public String toString(){
		return this.par ;
	}
	public void setPar(String par){
		this.par=par;
	}
	public String getPar(){
		return this.par;
	}
}
