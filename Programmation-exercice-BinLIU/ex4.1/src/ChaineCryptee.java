import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ChaineCryptee {
	private String enclair;
	private int decalage;
	
	private ChaineCryptee(String enclair, int decalage){
		this.enclair=enclair;
		this.decalage=decalage;
	}
	
	public String decrypte(){
		try{
			return enclair;
		}catch(Exception e){
			return null;
		}
	}	

	public char[] crypte(){	
		try{
			if(enclair==""){
				return null;
			}else{
				char [] encrypte = new char[enclair.length()];
				char [] encry=enclair.toCharArray();
				for(int i=0;i<enclair.length();i++){
					encrypte[i]=decaleCaractere(encry[i],decalage);
				}
				return encrypte;
			}
		}catch(Exception e){
			return null;
		}
	}
	
	private static char decaleCaractere(char c , int decalage){
		return (c<'A'||c>'Z')?c:(char)(((c-'A'+decalage)%26)+'A');
	}
	
	 public static void rwFile(char[] cs) throws IOException{
		 	File file = new File("text.txt");
	        FileWriter fw = null;
	        
	        if(!file.exists()){
                file.createNewFile();
	        }
	        
	        try {
	        	
	            fw = new FileWriter(file, true);
            
                fw.write(cs);
                fw.write("\r\n");
                fw.flush();
	                
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (fw != null) {
	                try {
	                    fw.close();
	                } catch (IOException e) {
	                   
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	 
	 
	 public static ChaineCryptee FromCrypted(String incrypted,int aShift){
		 ChaineCryptee cc= new ChaineCryptee(incrypted,aShift);
		 return cc;
	 }
	 
	 public static ChaineCryptee Fromdecrypted(String indecrypted,int aShift){
		 ChaineCryptee cc= new ChaineCryptee(indecrypted,aShift);
		 return cc;
	 }
	 
	 public static void main(String[] args) throws IOException {
		 
		 ChaineCryptee c1 = FromCrypted("BONJOUR A TOUS",3);
		 ChaineCryptee c2 = Fromdecrypted("BONJOUR A TOUS",3);
		 
		 try{
			 String str1="Chaine chiffree:";
			 System.out.print(str1);
			 System.out.println(c1.crypte());
			 
			 char[] char1= str1.toCharArray();
			 rwFile(char1);
			 rwFile(c1.crypte());		 
			 
			 String str2="Chaine en clair:";
			 System.out.println(str2+ c2.decrypte());
			 
			 char[] char2= str2.toCharArray();
			 rwFile(char2);
			 char[] cc2 = c2.decrypte().toCharArray();
			 rwFile(cc2);
		 
		 }catch (IOException e) {
             e.printStackTrace();
         }
	 }
	
}
