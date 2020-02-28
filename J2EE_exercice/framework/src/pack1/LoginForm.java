package pack1;

public class LoginForm {
	
	private String login;
	private String pwd;

	public String toString(){
		return this.login + this.pwd;
	}
	public void setlogin(String login){
		this.login=login;
	}
	public String getLogin(){
		return this.login;
	}
	public void setMdp(String pwd){
		this.pwd=pwd;
	}
	public String getMdp(){
		return this.login;
	}
	public LoginForm(String login,String pwd){
		super();
		this.login=login;
		this.pwd=pwd;
	}
}
