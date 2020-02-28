package form;

import java.util.HashMap;
import org.apache.struts.action.ActionForm;

import entity.Produit;

@SuppressWarnings("serial")
public class HelloWorldForm extends ActionForm {

	private HashMap<String,Produit> FormValueMap = new HashMap<String,Produit>();
	public void setMap(HashMap<String,Produit> FormValueMap){
		this.FormValueMap = FormValueMap;
	}
	
	public HashMap<String, Produit> getFormValueMap(){
		return this.FormValueMap;
	}
	
	public void setFormValue(String ref,Produit p){
		FormValueMap.put(ref, p);
	}

	public Object getFormValue(String ref){
		return FormValueMap.get(ref);
	}
	
	public Object getFormValue1(String ref){
		return FormValueMap.get(ref);
	}
}
