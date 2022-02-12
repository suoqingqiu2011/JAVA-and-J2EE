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
 * Remarques : Nestedloop --- jointure de R et S
 */


package LiuBin_TP1_V01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Operations {
	//lire 'r.txt' et 's.txt'
	public char[] readFile(char[] tab,String filename) throws IOException {
		try {		      
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line ;
			
				while ((line = reader.readLine()) != null) { 
			        String temp =line.replace(" ","");
			        tab= temp.toCharArray();
			    } 
				
			reader.close();
			}catch (Exception e) {
		        e.printStackTrace();
			}
		
		return tab;
	}
	//Ecrire la table du jointure dans 'rs.txt'
	public List<Character> writeFile(List<Character> rs,String filename) throws IOException {
		try {		
			 File f = new File(filename);
			 
			 if(!f.exists() && !f.isDirectory()) {
			 	f.mkdirs();
			 	f.createNewFile();
			 }
			
			 Writer out = new FileWriter(f);
			 BufferedWriter bw = new BufferedWriter(out);
			 for(Character c : rs){
			 	bw.write(c);
			 	bw.write(" ");
			 	bw.flush();
			 }
			 
			 bw.close();
			
		 }catch (Exception e) {
	         e.printStackTrace();
	    }
		return rs;
	}
	
	//Retirer le jointure entre r et s
	public List<Character> join(List<Character> rs, char[] r, char[] s){		
		 for (int i = 0; i < r.length; i++) {
		        for (int j = 0; j < s.length; j++) {
		            if (r[i] == s[j]) {
		                rs.add(r[i]);	 		               
		            }
		        }
		  }		
		// System.out.print(rs);
	return rs;
	} 
}
