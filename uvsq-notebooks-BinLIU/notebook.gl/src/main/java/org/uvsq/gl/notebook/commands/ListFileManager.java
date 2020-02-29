package org.uvsq.gl.notebook.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import freemarker.template.TemplateException;

/**
 * Hériter la classe abstraire {@code Command} et récrire la fonction {@code execute()} et {@code execute(String)} 
 * qui permet de manager des ordres alphabétiques sur la liste des notes classés selon différents critères.
 *
 * @author Administrator
 * @since JDK1.8
 */
public class ListFileManager extends Command {

	private Receiver directoryReceiver;
	private String manageMode;
	
	/**
	 * C'est le constructeur
	 * @param directoryReceiver directoryReceiver
	 * @param manageMode modele de management
	 */
	public ListFileManager(Receiver directoryReceiver, String manageMode) {
		this.directoryReceiver = directoryReceiver;
		this.manageMode = manageMode;
	}
	/**
     * Réaliser la fonction {@code list(String)}
     * @param fileName nom du fichier
	 * @throws TemplateException s'affiche si eureur
	 * @throws IOException s'affiche si eureur
     * @see Command#execute(String)
     * @see DirectoryReceiver#listManager(String,String)
     */
	public Receiver execute(String fileName) throws IOException,TemplateException{
		((DirectoryReceiver)directoryReceiver).listManager(fileName, manageMode);
		
		return this.directoryReceiver;
	}

}
