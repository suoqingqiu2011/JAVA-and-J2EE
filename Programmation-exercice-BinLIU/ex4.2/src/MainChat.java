
public class MainChat {
	public static void main(String[] args) {
	Client c=new Client("bin"); 
	Client c1=new Client("bin1"); 
	Serveur s=new Serveur();
	
	c.seConnceter(s);
	c1.seConnceter(s);
	
	c.envoyer(s,"are you OK?");
	c1.envoyer(s,"i'm OK.");
	}
}