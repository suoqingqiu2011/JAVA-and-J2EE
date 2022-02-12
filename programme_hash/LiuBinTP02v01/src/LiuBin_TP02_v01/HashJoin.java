package LiuBin_TP02_v01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Vector;

public class HashJoin implements HashProbing{
	
	@Override
	public void put(char[] KeyRS,int[] ValuesRS_R,int[] ValuesRS_S,char key, int valueRS_R, int valueRS_S, int index) {
	
			KeyRS[index] = key ;
			ValuesRS_R[index] = valueRS_R ;	
			ValuesRS_S[index] = valueRS_S ;

	}

	@Override
	public int[][] get(char[] KeyRS,int[] ValuesRS_R,int[] ValuesRS_S,char key) {
		
		int[][] valueToReturn = new int[10][2];
		int index = 0;
		int i = 0;
		while((index < KeyRS.length) && String.valueOf(KeyRS[index])!= null) {  
		    if (KeyRS[index] == key) {
		    	valueToReturn[i][0] = ValuesRS_R[index];   
		    	valueToReturn[i][1] = ValuesRS_S[index];  
		    	i ++;
		    }
		    index ++; 
		}
		/*diminuer la taille de tableau ¨¤ retourner, car il exite des position nil dans la taille '10' de tab  */
		int[][] valueToReturnLimit = Arrays.copyOf(valueToReturn, i);  
  
		return valueToReturnLimit;	
	}

	@Override
	public char[] remove(char[] KeyRS,int[] ValuesRS_R,int[] ValuesRS_S,char key) {
		int index = 0; 
		
	    while(index < KeyRS.length) { 
	    	if (KeyRS[index] == key ) {
	    		KeyRS[index] = ' '; 
	    		ValuesRS_R[index] = 0;
	    		ValuesRS_S[index] = 0;
	    	 }
	    	index ++;
	    }
	    System.out.print("[ ");
		for (int i = 0; i < KeyRS.length; i++) {
			System.out.print("'"+KeyRS[i]+"' ");
		}	
		System.out.print("]\n");
		System.out.print("[ ");
		for (int i = 0; i < ValuesRS_R.length; i++) {
			System.out.print(" "+ValuesRS_R[i]+" ");
		}	
		System.out.print("]\n");
		System.out.print("[ ");
		for (int i = 0; i < ValuesRS_S.length; i++) {
			System.out.print(" "+ValuesRS_S[i]+" ");
		}	
		System.out.print("]\n");
	    
	    
	    return KeyRS;
	}
	
	public char[] readFile(char[] Key,int[] Values1,int[] Values2,String filename) throws IOException {
		try {		
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader inp_r = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(inp_r);
			String line;
			String[] temp = new String[3] ;
			int i = 0;
			while ((line = reader.readLine()) != null) { 
				temp[i] = line;				
				if( i == 0) {
					Key = temp[i].replace(" ", "").toCharArray();
				} 
				if(i == 1) {  
					String[] strArray = temp[i].split(" "); 
					for (int j = 0; j < strArray.length;j++) {
						Values1[j] = Integer.parseInt(strArray[j]);
			        }  
				} 
				if(i == 2) {  
					String[] strArray = temp[i].split(" "); 
					for (int w = 0; w < strArray.length; w++) {
						Values2[w] = Integer.parseInt(strArray[w]);
			        }  
				} 
				i++;		       
		    }	
			reader.close();
			}catch (Exception e) {
		        e.printStackTrace();
			}
		return Key;
	}
	
	/*Ecrire la table du jointure dans 'rs.txt'*/
	public void writeFile(char[] KeyRS,int[] ValuesRS_R,int[] ValuesRS_S,String filename) throws IOException {
		try {		
			 File f = new File(filename);
			 
			 if(!f.exists() && !f.isDirectory()) {
			 	f.mkdirs();
			 	f.createNewFile();
			 }
			
			 Writer out = new FileWriter(f);
			 BufferedWriter bw = new BufferedWriter(out);
			 
			 int[] indexNullElement = new int[KeyRS.length]; //marquer la position de null element pour ¨¦viter de l'enregistrer dans RS.txt
			 /* stocker les Keys sur 1er ligne  */
			 int cpt = 0;
			 for(int a=0 ; a < KeyRS.length;a++){
				if(KeyRS[a]!= ' ') {
				 	bw.write(KeyRS[a]);
				 	bw.write(" ");
				 	bw.flush();
				}else {
					indexNullElement[cpt] = a;
					cpt ++;
				}
			 }	
			 bw.newLine();
			 /* stocker les values qui viens de R sur 2¨¨me ligne  */
			 int cpt1 = 0;
			 for(int b=0; b < ValuesRS_R.length;b ++){ 
				
			 	if((indexNullElement[cpt1] != b) || (cpt == 0)) { 
				 	bw.write(String.valueOf(ValuesRS_R[b])); 
				 	bw.write(" ");
				 	bw.flush();	

				 }else if(indexNullElement[cpt1] == b){ 
				 	cpt1 ++;
				 }
			 }	
			 bw.newLine();
			 /* stocker les values qui viens de S sur 3¨¨me ligne  */
			 int cpt2 = 0;
			 for(int c = 0; c < ValuesRS_S.length; c++){
				 if((indexNullElement[cpt2] != c)|| (cpt == 0)) { 
				 	bw.write(String.valueOf(ValuesRS_S[c]));
				 	bw.write(" "); 	
				 	bw.flush(); 
				 	
				 }else if(indexNullElement[cpt2] == c){  
				 	cpt2 ++;
				 }
			 }	
			 bw.close();
			
		}catch(Exception e) {
			e.printStackTrace();
	    }
		
	}
	/* la jointure principale */	
	public int hashjoin(char[] R, char[] S,int[] ValuesR,int[] ValuesS, char[] KeyRS, int[] ValuesRS_R, int[] ValuesRS_S){
		int i=0;
		int j=0;
		int k=0;
		Vector<Character> kRS = new Vector<Character>();
		Vector<Integer> valRS_R = new Vector<Integer>();
		Vector<Integer> valRS_S = new Vector<Integer>();
		
		Arrays.sort(R);
		Arrays.sort(S);	
		while( i < R.length && j < S.length ){
				if( R[i] == S[j] ){
					// if R(i) = S(j) 
					k = j;
					while( (i < R.length) && (R[i] == S[j]) ){
						// while R(i) = S(j)
						while(( j < S.length) && (R[i] == S[j])){
							// while R(i) = S(j) then 						
							kRS.add(R[i]);
							valRS_R.add(ValuesR[i]);
							valRS_S.add(ValuesS[j]);
							
							j++;
						}
						i++;
						j = k;
					}
				}else if( R[i] > S[j] ) {
					// if R(i) > S(j) then j=j+1
					j++;
				}else if( R[i] < S[j] ) {
					// else R(i) < S(j) then i=i+1
					i++;
				}
		}
		
		/* Justement pour v¨¦rifier des ¨¦tats de la jointure*/
		int t = 0;
		System.out.println("V¨¦rifiez la jointure dans la fonction de 'hashjoin': ");
		while(t < kRS.size()) {
			System.out.println("{ "+kRS.elementAt(t)+" "+valRS_R.elementAt(t)+" "+valRS_S.elementAt(t)+" }");
			
			put(KeyRS, ValuesRS_R, ValuesRS_S, kRS.elementAt(t) , valRS_R.elementAt(t) , valRS_S.elementAt(t), t);		
			t++;
		}	
		return kRS.size();
	}
	
	
}
