package org.uvsq.gl.notebook.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Cette classe {@code Outil} ne contient que la fonction {@code readToBuffer(StringBuffer,String)} . 
 * @author Administrator
 * @since JDK1.8
 * 
 */
public class Outil {
	/**
     * Les contenus des fichiers sont tous mis dans le buffer 
     * @param buffer buffer
     * @param filePath défini le chemin du fichier
     * @throws IOException la gestion d'exception retourne la notation du problème si le buffer n'arrive pas à lire. 
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
    	/**
    	 * Définir un nouveau objet {@code FileInputStream}
    	 */
        InputStream is = new FileInputStream(filePath);
        /**
         * Déclarer une variable pour enregistrer des contenus lus par la ligne
         */
        String line; 
        /**
    	 * Définir un nouveau objet {@code BufferedReader}
    	 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); 
        while (line != null) { 
            buffer.append(line); 
            buffer.append("\n"); 
            line = reader.readLine(); 
        }
        reader.close();
        is.close();
    }
    
    public static String nameTransform(String name,String suffix)  {
    	File f = new File(name);
    	System.out.println(f.getPath());
    	String ret = f.getName();
    	int i=-1;
    	if((i =ret.indexOf(suffix))!=-1) {
    		ret = ret.substring(0, i);
    	}
    	return ret;
    }
    
    
   }
