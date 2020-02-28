package entity;

public class Login {
	private String login;
	private String mdp;

	public String toString(){
		return this.login + this.mdp;
	}
	public void setlogin(String login){
		this.login=login;
	}
	public String getLogin(){
		return this.login;
	}
	public void setMdp(String mdp){
		this.mdp=mdp;
	}
	public String getMdp(){
		return this.login;
	}
	public Login(String login,String mdp){
		super();
		this.login=login;
		this.mdp=mdp;
	}
	public boolean Valide(){
		if(login.equals("bin")){
			return true;
		}
		return false;
	}
}
