package form;

import java.util.HashMap;
import org.apache.struts.action.ActionForm;

import entity.Produit;

@SuppressWarnings("serial")
public class HelloWorldForm extends ActionForm {
	private String nom;
	private String ref;
	private float prix;

	public void setNom(String nom){
		this.nom=nom;
	}
	public String getNom(){
		return this.nom;
	}
	public void setRef(String ref){
		this.ref=ref;
	}
	public String getRef(){
		return this.ref;
	}
	public void setPrix(float prix){
		this.prix=prix;
	}
	public float getPrix(){
		return this.prix;
	}
	
	/*private HashMap<String,Produit> FormValueMap = new HashMap<String,Produit>();
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
	}*/
}
