/** 
 * TP n¡ã V n¡ã : 1
 * 
 * Titre du TP : jointure
 * 
 * Date : 30 Octobre 2021
 * 
 * Nom : LIU
 * Pr¨¦nom : Bin
 * N¡ã d'¨¦tudiant : 22118363
 * 
 * email : happybin1989@gmail.com
 * 
 * Remarques : Main class
 */ 


package LiuBin_TP1_V01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LiuBinTP1jointure {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		char[] r = new char[10];
		char[] s = new char[10];
		char[] rs = new char[10];
		
		for (int i = 0; i < rs.length; i++) {
			rs[i]=' ';
		}	
    
		Operations O = new Operations();
		//lire des donn¨¦es avec l'index dans "R.txt" et enregistrement dans r[] 
		
		System.out.println("Tapez l'index 'A','B' ou 'C' pour retirer un table 'r' ¨¤ partir de Relation R :" );  
       // char ch = 0 ;  
        @SuppressWarnings("resource")
		Scanner cin=new Scanner(System.in); 
        String str=cin.nextLine(); 
        char ch_in=str.charAt(0);
	        
		r = O.readFile(r,"R.txt",ch_in);  
		
		System.out.print(" Table r: ");
		for (int i = 0; i < r.length; i++) {
			System.out.print(r[i]+" ");
		}	
		System.out.print("\n");
		//lire des donn¨¦es avec l'index dans "s.txt" et enregistrement dans s[] 
		
		System.out.println("Tapez l'index 'A','B' ou 'C' pour retirer un table 's' ¨¤ partir de Relation S :" );   
       // char ch = 0 ;  
        @SuppressWarnings("resource")
		Scanner cin1=new Scanner(System.in); 
        String str1=cin1.nextLine(); 
        char ch_in1=str1.charAt(0);
		
		s = O.readFile(s,"S.txt",ch_in1); 
		
		System.out.print(" Table s: ");
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i]+" ");
		}
	
		O.join(rs,r,s);
		O.writeFile(rs,"RS.txt"); 
  
		//v¨¦rifier des donn¨¦es sauvegard¨¦es dans "RS.txt"
        try {   
            System.out.print("\n Table rs: ");
            BufferedReader reader = new BufferedReader(new FileReader("RS.txt"));
            while (true) {
            	String line = reader.readLine();
            	if (line == null) {
	            	reader.close();
	            	break;
            	}else { 
            		System.out.println(line);
            	}
            }
            
       }catch (Exception e) {
            e.printStackTrace();
       }
	}

}
