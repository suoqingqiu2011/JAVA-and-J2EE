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
import java.util.ArrayList;
import java.util.List;


public class LiuBinTP1jointure {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		char[] r = new char[10];
		char[] s = new char[10];
		List<Character> rs = new ArrayList<>();
    
		Operations O = new Operations();
		//lire des donn¨¦es dans "R.txt"	et enregistrement dans r[] 
		r = O.readFile(r,"R.txt");  
		System.out.print(" r: ");
		for (int i = 0; i < r.length; i++) {
			System.out.print(r[i]+" ");
		}	
		//lire des donn¨¦es dans "s.txt"	et enregistrement dans s[] 
		s = O.readFile(s,"S.txt"); 
		System.out.print("\n s: ");
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i]+" ");
		}
			
		/*List<Character> L = O.join(rs,r,s); 		
		System.out.println("\n"+L);*/
		O.join(rs,r,s);
		O.writeFile(rs,"RS.txt"); 
  
		//v¨¦rifier des donn¨¦es sauvegard¨¦es dans "RS.txt"
        try {   
            System.out.print("\n rs: ");
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
