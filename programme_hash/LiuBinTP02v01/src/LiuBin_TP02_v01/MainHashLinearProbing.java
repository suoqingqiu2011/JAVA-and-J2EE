/** 
 * TP n¡ã V n¡ã : TP2 V01
 * 
 * Titre du TP : Hash Linear Probing
 * 
 * Date : 22 Novembre 2021
 * 
 * Nom : LIU
 * Pr¨¦nom : Bin
 * N¡ã d'¨¦tudiant : 22118363
 * 
 * email : happybin1989@gmail.com
 * 
 * Remarques : main class client -> HashLinearProbing
 */ 

package LiuBin_TP02_v01;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MainHashLinearProbing {
	
	public static void main(String[] args) throws IOException {
		System.out.println("-------------------------------");
		System.out.println("Taper le num:\n1 -> HashLinearProbing ; 2 -> HashJoin \nVotre choix: ------>");
		@SuppressWarnings("resource")
		Scanner scan00 = new Scanner(System.in);
		char choix00 = scan00.nextLine().charAt(0);
        
		switch (choix00){
            case '1' :
            	System.out.println("\n\n--------------- Hash Linear Probing ---------------");
        		HashLinearProbing();
        		break;
            case '2' :	
				System.out.println("\n\n--------------- Hash join ---------------");
				HashJoin();
				break;
            default:
            	break;
		}
	}
	
	/*---------- La partie de HashJoin ----------*/ 
	private static void HashJoin() throws IOException {
		char[] KeyR = new char[10] ;
		int[] ValuesR = new int[10];
		char[] KeyS = new char[10] ;
		int[] ValuesS = new int[10];
		

		HashJoin HJ = new HashJoin();
		/* r¨¦cup¨¦rer des keys et values ¨¤ partir de fichier R.txt */
		KeyR = HJ.readFile(KeyR,ValuesR,null,"R.txt");
		
		System.out.print("KEYS of R: ");
		printKeyTab(KeyR);
		
		System.out.print("VALUES of R: ");
		printValueTab(ValuesR);
		System.out.print("\n");
		
		/* r¨¦cup¨¦rer des keys et values ¨¤ partir de fichier S.txt */
		KeyS = HJ.readFile(KeyS,ValuesS,null,"S.txt");
		
		System.out.print("KEYS of S: ");
		printKeyTab(KeyS);
		
		System.out.print("VALUES of S: ");
		printValueTab(ValuesS);
		System.out.print("\n");
	
		int tab_size = 10*10; //initialiser ¨¤ taille max si tous sont des duplications
		char[] KeyRS = new char[tab_size] ;  //tab RS de Keys apr¨¨s la jointure
		int[] ValuesRS_R = new int[tab_size] ; //tab RS de Values viennant de R apr¨¨s la jointure
		int[] ValuesRS_S = new int[tab_size] ; //tab RS de Values viennant de S apr¨¨s la jointure
		
		/* la jointure de hash */
		tab_size = HJ.hashjoin (KeyR, KeyS, ValuesR, ValuesS, KeyRS,ValuesRS_R,ValuesRS_S);
		
		/* Apr¨¨s d¨¦terminer ses m¨ºmes tailles de tableaux KeyRS, ValuesRS_R et ValuesRS_S , je reconstruit des tabs ¨¤ taille exact */
		char[] K_RS = Arrays.copyOf(KeyRS, tab_size) ;
		int[] V_RS_R = Arrays.copyOf(ValuesRS_R, tab_size) ;
		int[] V_RS_S = Arrays.copyOf(ValuesRS_S, tab_size) ;
		
		System.out.println("Apr¨¨s la jointure de hash, je sors des keys et values en format de tableau: ");		
		System.out.print("KEYS of RS dans le tableau : ");
		printKeyTab(K_RS);
		
		System.out.print("VALUES_1 of RS relevant to R dans le tableau: ");
		printValueTab(V_RS_R);
		
		System.out.print("VALUES_2 of RS relevant to S dans le tableau: ");
		printValueTab(V_RS_S);
		
		HJ.writeFile(K_RS,V_RS_R,V_RS_S,"RS.txt");
		int len = K_RS.length;
		menu_join(HJ,K_RS,V_RS_R,V_RS_S, len);
		
	}
	
	/*---------- La partie de HashLinearProbing ----------*/ 
	private static void HashLinearProbing() {
		HashLinearProbing hp = new HashLinearProbing();
		char[] K = {'B','O','E','P','V','L','X','N','K','M'};
		int[] Val = new int[10];
		int M = 11; //la capacit¨¦
		char[] Key = new char[M] ;
		int[] Values = new int[M];
		int i = 0;
		while(i < 10) {  Val[i]=i;  i++; }
		
		int t = 0;
		while(t < M) {  Key[t]=' '; t++;}
	
		int j = 0;
		while(j < 10) {	 hp.put(Key, Values,null, K[j], Val[j] , 0, 0);  j++;	}
		
		hp.printHashTable(Key,Values);
		
		System.out.println("Apr¨¨s tous les insertion dans hash map: ");
		
        menu(hp, Key, Values, M);
	}
	
	private static void menu(HashLinearProbing hp,char[] Key,int[] Values,int M){
		System.out.println("-------------------------------");
		System.out.println("Taper le num:\n1 -> get ; 2 -> remove \nVotre choix: ------>");
		@SuppressWarnings("resource")
		Scanner scan0 = new Scanner(System.in);
		char choix = scan0.nextLine().charAt(0);
        
		switch (choix){
            case '1' :
                System.out.println("Taper key ¨¤ chercher selon Hash Table au_dessus: ------>");
                
                check(hp, Key, Values, M, choix);
              
                System.out.println("Vous voulez faire des autres actions: taper y, sinon taper n'importe quoi !!! ");
                Scanner scan2 = new Scanner(System.in);
                char choix1 = scan2.next().charAt(0);
                if(choix1 == 'Y' || choix1 == 'y'){
                	menu(hp, Key, Values, M);
                
                }else
                	System.out.println("----- SORTIE ------");
                	break;
            case '2' :
                System.out.println("Taper key ¨¤ enlever selon Hash Table au_dessus: ------>");   
                
                check(hp, Key, Values, M, choix);
              
                System.out.println("Vous voulez faire des autres actions: taper y, sinon taper n'importe quoi!!! ");
                Scanner scan3 = new Scanner(System.in);
                char choix2 = scan3.next().charAt(0);
                if(choix2 == 'Y' || choix2 == 'y') {
                	menu(hp, Key, Values, M);
               
                }else
                	System.out.println("------ SORTIE ------");
                	break;
            default:          	
                System.out.println("Tromp¨¦ ¨¤ taper, refaitez£º------>");
                menu(hp, Key, Values, M);  
            	break;
        }
	}
	/* les choix entre les fonction 'get' et 'remove'*/
	private static void check(HashLinearProbing hp,char[] Key,int[] Values,int M,char choix){
		@SuppressWarnings("resource")
		Scanner scan1 = new Scanner(System.in);
		char tap = scan1.next().charAt(0);
		int s = 0;
		
        while(s < M) {
            if(tap == Key[s]) {
            	if(choix == '1') {
            		int valueReturn1[][] = hp.get(Key,Values,null, tap);
                    System.out.println("Get the value of '"+tap+"' : "+valueReturn1[0][0]);
            	}
            	if(choix == '2') {
            		Key = hp.remove(Key,Values,null,tap);
	                hp.printHashTable(Key,Values);  
            	}
            	break;
            }
            s++;
            if(s == M){
            	System.out.println("Il n'a pas ce key dans le Hash Map!!! V¨¦rifiez, s'il vous plait...");   
            }  
        }    
	}
	
	private static void menu_join(HashJoin HJ,char[] Key,int[] Values1,int[] Values2,int M) throws IOException{
		System.out.println("-------------------------------");
		System.out.println("Taper le num:\n1 -> get ; 2 -> remove \nVotre choix: ------>");
		@SuppressWarnings("resource")
		Scanner scan0 = new Scanner(System.in);
		char choix = scan0.nextLine().charAt(0);
        
		switch (choix){
            case '1' :
                System.out.println("Taper key ¨¤ chercher selon Hash Table au_dessus: ------>");
                
                check_join(HJ, Key, Values1,Values2, M, choix);
              
                System.out.println("Vous voulez faire des autres actions: taper y, sinon taper n'importe quoi !!! ");
                Scanner scan2 = new Scanner(System.in);
                char choix1 = scan2.next().charAt(0);
                if(choix1 == 'Y' || choix1 == 'y'){
                	menu_join(HJ, Key, Values1,Values2, M);
                
                }else
                	System.out.println("----- SORTIE ------");
                	break;
            case '2' :
                System.out.println("Taper key ¨¤ enlever selon le Table de jointure au_dessus: ------>");   
                
                check_join(HJ, Key, Values1,Values2, M, choix);
              
                System.out.println("Vous voulez faire des autres actions: taper y, sinon taper n'importe quoi!!! ");
                Scanner scan3 = new Scanner(System.in);
                char choix2 = scan3.next().charAt(0);
                if(choix2 == 'Y' || choix2 == 'y') {
                	menu_join(HJ, Key, Values1,Values2, M);
               
                }else
                	System.out.println("------ SORTIE ------");
                	break;
            default:          	
                System.out.println("Tromp¨¦ ¨¤ taper, refaitez£º------>");
                menu_join(HJ, Key, Values1,Values2, M);  
            	break;
        }
	}
	/* les choix entre les fonction 'get' et 'remove'*/
	private static void check_join(HashJoin HJ,char[] Key,int[] Values1,int[] Values2,int M,char choix) throws IOException{
		@SuppressWarnings("resource")
		Scanner scan1 = new Scanner(System.in);
		char tap = scan1.next().charAt(0);
		int s = 0;
		
        while(s < M) {
            if(tap == Key[s]) {
            	if(choix == '1') {
            		/*  Justemen pour v¨¦rifier des donn¨¦es das RS.txt  */
            		char[] RS_cle = new char[Key.length] ;  //afficher tab RS de Keys dans RS.txt
            		int[] RS_R_val = new int[Values1.length] ; //affichertab RS de Values viennant de R dans RS.txt
            		int[] RS_S_val= new int[Values2.length] ; //afficher tab RS de Values viennant de S dans RS.txt
            		
            		RS_cle = HJ.readFile(RS_cle, RS_R_val, RS_S_val, "RS.txt");
            		print_RSfile(RS_cle,RS_R_val,RS_S_val); 
            		
            		int value_RS[][] = HJ.get(RS_cle,RS_R_val,RS_S_val,tap); 
            		
            		System.out.println("\nTrouver "+value_RS.length+ " elements de "+tap+":" );
            		for(int i = 0;i < value_RS.length;i++) {
            			System.out.println(tap+": "+value_RS[i][0] +" "+value_RS[i][1] );
            		}
            		
            	}
            	if(choix == '2') {
            		Key = HJ.remove(Key,Values1,Values2,tap); 
            		
            		System.out.println("Apr¨¨s enlever l'element de "+tap+" : ");			
            		
            		HJ.writeFile(Key,Values1,Values2,"RS.txt");	
            		/*  Justemen pour v¨¦rifier des donn¨¦es das RS.txt  */
            		char[] RS_c = new char[Key.length] ;  //afficher tab RS de Keys dans RS.txt
            		int[] RS_R_v = new int[Key.length] ; //affichertab RS de Values viennant de R dans RS.txt
            		int[] RS_S_v= new int[Key.length] ; //afficher tab RS de Values viennant de S dans RS.txt
            		
            		RS_c = HJ.readFile(RS_c, RS_R_v, RS_S_v, "RS.txt");
            		
            		/*selon le nombre des ¨¦l¨¦ments, je diminiue les tailles des tableaux et reconstruit des nouveaux.*/
            		char[] k_rs = Arrays.copyOf(RS_c, RS_c.length) ;
            		int[] val_rsr = Arrays.copyOf(RS_R_v, RS_c.length) ;
            		int[] val_rss = Arrays.copyOf(RS_S_v, RS_c.length) ;
            		print_RSfile(k_rs,val_rsr,val_rss); 
            	}
            	break;
            }
            s++;
            if(s == M){
            	System.out.println("Il n'a pas ce key dans la tableau de jointure!!! V¨¦rifiez,s'il vous plait...");   
            }  
        }
        
	}
	/* la fonction statique pour affichier le tab de keys , values1 et values2 dans RS.txt*/
	private static void print_RSfile(char[] RS_cle,int[] RS_R_val,int[] RS_S_val) throws IOException {

		System.out.print("\nRetirer KEYS of RS dans RS.txt : ");
		printKeyTab(RS_cle);
		
		System.out.print("Retirer VALUES_1 of RS relevant to R dans RS.txt: ");
		printValueTab(RS_R_val);
		
		System.out.print("Retirer VALUES_2 of RS relevant to S dans RS.txt: ");
		printValueTab(RS_S_val);
	}
	
	/* la fonction statique pour affichier le tab de key*/
	private static void printKeyTab(char[] tab) {
		System.out.print("[ ");
		for (int i = 0; i < tab.length; i++) {
			System.out.print("'"+tab[i]+"' ");
		}	
		System.out.print("]\n");
	}
	/* la fonction statique pour affichier le tab de values*/
	private static void printValueTab(int[] tab) {
		System.out.print("[ ");
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i]+" ");
		}	
		System.out.print("]\n");
	}

}
