package form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class EnseignActionForm	extends ActionForm {
	private String nomEnseign;
	private String codeEnseign;

	public void setNomEnseign(String nomEnseign){
		this.nomEnseign=nomEnseign;
	}
	public String getNomEnseign(){
		return this.nomEnseign;
	}
	public void setCodeEnseign(String codeEnseign){
		this.codeEnseign=codeEnseign;
	}
	public String getCodeEnseign(){
		return this.codeEnseign;
	}
}
