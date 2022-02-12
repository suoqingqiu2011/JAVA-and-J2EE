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
 * Remarques : class HashLinearProbing
 */ 

package LiuBin_TP02_v01;

public class HashLinearProbing implements HashProbing {
	int M = 11; //la capacit¨¦
	
	
	public int hash(char key) {
	    return String.valueOf(key).hashCode() % M; 
	}
	
	@Override
	public void put(char[] Key,int[] Values1,int[] Values2,char key, int value1,int value2, int index) {
		index = hash(key);	//obtenue selon hashcode
	    while(Key[index]!= ' ') { 
	    	index = (index + 1) % M; 
	    }	    
    	
	    Key[index] = key; 
	    Values1[index] = value1;   
	}

	@Override
	public int[][] get(char[] Key,int[] Values,int[] Values1,char key) {
		int index = hash(key);
		//int valueToReturn = -1;
		int[][] valueToReturn = new int[10][2];
	    while(Key[index] != key && String.valueOf(Key[index])!= null) {
	    	index = (index + 1) % M;
	    }
	    if (Key[index] == key) {
	    	valueToReturn[0][0] = Values[index];
	    }
	    
	    return valueToReturn;	    
	}

	@Override
	public char[] remove(char[] Key,int[] Values,int[] Values1,char key) {
		int index = hash(key);
		char savedKey;
		int savedValue;  

	    while(Key[index] != key && Key[index]!= ' ') { 
	    	index = (index + 1) % M;
	    }
	    Key[index] = ' ';
	    Values[index] = 0;
	    
	    index = (index + 1) % M;
	    while(Key[index]!= ' ') {
			savedKey = Key[index];
			savedValue = Values[index];
			Key[index] = ' ';
			Values[index] = 0;
			put (Key,Values,null,savedKey ,savedValue,0,0);
			
	    	index = (index + 1) % M;
	    }
	    return Key;
	}

	/* La fonction pour printer HashTable */
    public void printHashTable(char[] keys,int[] values){
        System.out.println("\nHash Table: ");
        int i=0;
        while(i < M) {
            if(String.valueOf(keys[i])!= null) {
                System.out.println("[ '"+keys[i] +"',"+ values[i]+" ]" );
            }
            i++;
        }
        System.out.println();
    }
   
}
