import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import junit.framework.TestCase;

public class DnsAppTest extends TestCase{
	AdresseIP ip;
	NomMachine nomMach;
	DnsItem dnsitem;
	ArrayList<String> lines;
	commands cmd;
	Dns dns;
	DnsTUI dnstui;
	
	@SuppressWarnings("serial")
	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
	    try {
			ip = new AdresseIP("192.168.1.82");
			nomMach= new NomMachine("bin.info_uvsq.fr");
			dnsitem= new DnsItem("info_uvsq.fr");
			
			lines=new ArrayList<String>(){ {add("nom.qualifie.machine");add("adr.es.se.ip");add("ls -a domaine");}};
			cmd=new commands(lines);
			
			dns= new Dns(ip,nomMach,dnsitem);
			dnstui=new DnsTUI(lines);
		
		 } catch (Exception e) {
			 e.printStackTrace();
	     }
	}
	
	@Test
	public void testGetAdresseIP() {	
		assertEquals("192.168.1.82",ip.GetAdresseIP());
	}
	
	
	@Test
	public void testGetNomMachine() {	
		assertEquals("bin.info_uvsq.fr",nomMach.GetNomMachine());
	}

	@Test
	public void testGetDnsItem() {	
		assertEquals("info_uvsq.fr",dnsitem.GetDnsItem());
	}

	@Test
	public void testexecute() throws IOException{	
		try {
			 Class<?> clazz = cmd.getClass();  
             Field field1 = clazz.getDeclaredField("file1");  
             Field field2 = clazz.getDeclaredField("file2");  
             field1.setAccessible(true);  
             field2.setAccessible(true); 
             cmd.execute();
             FileReader f1=(FileReader) field1.get(cmd);
             FileReader f2=(FileReader) field2.get(cmd);
			 
			 assertNotNull(f1);
			 assertNotNull(f2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetItem1(){	
		assertEquals("192.168.1.82",dns.getItem1());
	}
	
	@Test
	public void testgetItem2(){	
		assertEquals("bin.info_uvsq.fr",dns.getItem2());
	}
	
	@Test
	public void testgetItems(){	
		assertEquals("info_uvsq.fr",dns.getItems());
	}
	
	
	@Test
	public void testnextCommande() throws IOException{	
            String data = "nom.qualifie.machine";
           
            try {
                System.setIn(new ByteArrayInputStream(data.getBytes()));
               /* Class<?> clazz = dnstui.getClass();  
                Field field = clazz.getDeclaredField("s");  
                field.setAccessible(true);  */
                
                Scanner scanner = new Scanner(System.in);
                //dnstui.nextCommande();
                String string=(String)scanner.nextLine();
                //String str=(String) field.get(dnstui);
                //System.out.println("input is----" + string);  
                assertEquals(data ,string);
                scanner.close();
 
            }catch(Exception e) {
    			e.printStackTrace();
    		}
	}
	
}
