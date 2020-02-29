import org.junit.jupiter.api.Test;

public class Compte {
	
	private int solde;
	private int credit;
	private int debit;
	private int virement;
	public  Compte(int solde,int credit,int debit,int virement) {
		this.solde=solde;
		this.credit=credit;
		this.debit=debit;
		this.virement=virement;
	}
	
	@Test
	public void verifierInfo() {
		if(solde>0||credit>0||debit>0||virement>0) {
			verifier_sold() ;
		}
	}
	
	@Test
	public void verifier_sold() {
		if(solde>=0) {
			getSoldeVirement();
			getCredit();
			getDebit();
		}
	}
	
	@Test
	public int getSoldeVirement() {
		solde=solde-virement;
		return solde;
	}
	@Test
	public int getCredit() {
		return credit;
	}
	@Test
	public int getDebit() {
		return debit;
	}
}