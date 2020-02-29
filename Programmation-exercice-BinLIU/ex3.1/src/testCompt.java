import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCompt {

	Compte cpt=new Compte(10000,200,900,2000);
	
	@Test
	public void testVerifieInfo() {	
		cpt.verifierInfo();
	}
	
	@Test
	public void testVerifieSolde() {	
		cpt.verifier_sold();
	}
	
	@Test
	public void testSoldeVirement() {	
		cpt.getSoldeVirement();
	}
	@Test
	public void testCredit() {
		cpt.getCredit();
	}
	@Test
	public void testDebit() {
		cpt.getDebit();
	}
}
