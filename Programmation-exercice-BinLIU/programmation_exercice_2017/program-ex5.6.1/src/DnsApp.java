import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

interface Commande{
	public void execute() throws IOException;
}

class AdresseIP{
	String Ad_ip;
	AdresseIP(String Ad_ip){
		this.Ad_ip=Ad_ip;
	}
	
	String GetAdresseIP() {
		return Ad_ip;
	}
}

class NomMachine{
	String NameMachine;
	NomMachine(String NameMachine){
		this.NameMachine=NameMachine;
	}
	
	String GetNomMachine() {
		return NameMachine;
	}
}

class DnsItem{
	String dns_item;
	DnsItem(String dns_item){
		this.dns_item=dns_item;
	}
	
	String GetDnsItem() {
		return dns_item;
	}
}

class commands implements Commande {

	ArrayList<String> lines;

	public commands(ArrayList<String> lines){
		this.lines=lines;
	}
	
	FileReader file1;
	FileReader file2;
	public void execute() throws IOException {	
		try {
			file1 = new FileReader("src/FileLocalisation.txt");
			BufferedReader bre = new BufferedReader(file1);
			String path;
			while ((path = bre.readLine())!= null){
			//System.out.println(path);
			
				file2 = new FileReader(path);
				BufferedReader bre1 = new BufferedReader(file2);
				String str;
				while ((str = bre1.readLine())!= null){
					lines.add(str);
			      // System.out.println(str);
				}
				//System.out.println(lines);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	
	}
}

class Dns{
	AdresseIP IP;
	NomMachine nomPC;
	DnsItem nomDomaine;
	Dns(AdresseIP IP, NomMachine nomPC,DnsItem nomDomaine){
		this.IP=IP;
		this.nomPC=nomPC;
		this.nomDomaine=nomDomaine;
	}
	
	String getItem1() {
		return IP.GetAdresseIP() ;
	}
	 String getItem2() {
		return nomPC.GetNomMachine();
	}
	
	 String getItems() {
		return nomDomaine.GetDnsItem();
	}
}

class DnsTUI extends commands{
	String s;
	DnsTUI(ArrayList<String> lines){
		super(lines);
	}	
	
	public void nextCommande() throws IOException{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in) ;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String NomCompter = addr.getHostName().toString();
			NomMachine NomPC=new NomMachine(NomCompter);
	
			
			int len_max=100;
			int i=1;
			
			while(i<=len_max) {
				System.out.print("> ");
			    s= sc.nextLine();
			    
			    int flag;
			    /* les commandes seront inseres dans le console*/
				if("nom.qualifie.machine".equals(s.trim())){
					flag=0;
					affiche(NomPC,lines,flag);
							
				}else if("adr.es.se.ip".equals(s.trim())) {		
					flag=1;
					affiche(NomPC,lines,flag);
					
				}else if("ls -a domaine".equals(s.trim())) {
					Collections.sort(lines);     /* trie par l'ordre de la 1er lettre du nom qualite*/               
					System.out.println("--- Nom du domaine par tri de NomMachine: ---");
					for(String k: lines){  
			            System.out.println(k.substring(k.indexOf(".")+1, k.lastIndexOf(":")));                      
			        } 
					
				}else if("exit".equals(s.trim())){ /* quitter le console */
					System.out.println("\n***** quit the console *****\n");
					break;
				}else {
					System.out.println("!!! The commands don't exist.Please retry. !!!");
				}
				i++;
			}
		}catch (Exception e) {
			  e.printStackTrace();
	    }	
	}
	
	void affiche(NomMachine nomPC,ArrayList<String> lines,int flag) {
		
		for (String t : lines) {
			
			String nom_qualite=t.substring(0,t.indexOf(":")); /* la chaine de caractere avant ':'*/
			Dns dns=new Dns(null,nomPC,null);
		
			if(nom_qualite.contains(dns.getItem2())){				
				String ip=t.substring(t.indexOf(":")+1); /* la chaine de caractere apres ':'*/
				AdresseIP IP=new AdresseIP(ip);
				String entree_dns=nom_qualite.replaceAll(dns.getItem2()+".", "");		/* sauf nom de machine*/
				DnsItem dnsitem=new DnsItem(entree_dns);
				
				Dns dns1=new Dns(IP,nomPC,dnsitem);
				
				if(flag==0) {
					System.out.println("IP de cette machine <"+dns.getItem2()+">:"+dns1.getItem1()); 
				}else if(flag==1) {
					System.out.println("Nom qualifie de cette machine:"+"'"+nom_qualite+"'"); 
					System.out.println("Entree du DNS de cette machine:"+"'"+dns1.getItems()+"'"); 
				}
			}	
		}
	}
}

public class DnsApp {
	
	void run(ArrayList<String> lines) throws IOException {
		DnsTUI dnstui=new DnsTUI(lines);
		System.out.println("\n***** Enter on the console and please insert the commmands *****\n");
		dnstui.nextCommande();
	}
	
	public static void main(String[] args) throws IOException{
		
		ArrayList<String> lines = new ArrayList<String>();
		commands cmds=new commands(lines);
		cmds.execute();
		
		DnsApp dnsapp=new DnsApp();
		dnsapp.run(lines);
		
	}
}
