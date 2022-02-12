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
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

public class Operations {
	//lire 'r.txt' et 's.txt'
	public char[] readFile(char[] tab,String filename,char ch_in) throws IOException {
		try {		
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader inp_r = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(inp_r);
			String line;
			
			while ((line = reader.readLine()) != null) { 
				char ch = line.charAt(0); 
				if(ch == ch_in) {
					//System.out.println(ch);
			        String temp = line.replace(" ","");  //System.out.println(temp);
			        tab = temp.toCharArray();
			        break;
				}	
		    }				
			reader.close();

			}catch (Exception e) {
		        e.printStackTrace();
			}
		return tab;
	}
	
	//Ecrire la table du jointure dans 'rs.txt'
	public char[] writeFile(char[] rs,String filename) throws IOException {
		try {		
			 File f = new File(filename);
			 
			 if(!f.exists() && !f.isDirectory()) {
			 	f.mkdirs();
			 	f.createNewFile();
			 }
			
			 Writer out = new FileWriter(f);
			 BufferedWriter bw = new BufferedWriter(out);
			 for(char c : rs){
			 	bw.write(c);
			 	bw.write(" ");
			 	bw.flush();
			 }		 
			 bw.close();
			
		}catch(Exception e) {
			e.printStackTrace();
	    }
		return rs;
	}
	
	//Retirer le jointure entre r et s
	public char[] join(char[] rs, char[] r, char[] s){	
		int count = 0;
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < s.length; j++) {
				if (r[i] == s[j]) { 		
					rs[count] = r[i];
					count++;
				}
			}
		}		
		// System.out.print(rs);
	return rs;
	} 
}
