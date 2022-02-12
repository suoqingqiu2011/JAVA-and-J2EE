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
 * Remarques : interface HashProbing
 */ 

package LiuBin_TP02_v01;

public interface HashProbing {
	public void put(char[] Key,int[] Values1,int[] Values2, char key, int value1,int value2,int index);
	public int[][] get(char[] Key,int[] Values1,int[] Values2,char key);
	public char[] remove(char[] Key,int[] Values1,int[] Values2,char key);

}
