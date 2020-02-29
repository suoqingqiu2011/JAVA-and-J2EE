package org.uvsq.gl.notebook.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import freemarker.template.TemplateException;

/**
 * Hériter la classe abstraire {@code Command} et récrire la fonction {@code execute()} et {@code execute(String)} 
 * qui permet d'afficher touts les noms de fichiers dans un bon chemin et les informations détailles du 
 * fichiers qui peut aussi les enregistrer au format json.
 *
 * @author Administrator
 * @since JDK1.8
 */
public class ListFile extends Command {

	private Receiver directoryReceiver;
	/**
	 * C'est le constructeur
	 * @param directoryReceiver directoryReceiver
	 */
	public ListFile(Receiver directoryReceiver) {
		this.directoryReceiver = directoryReceiver;
	}

	/**
     * Réaliser la fonction {@code list(String)}
     * @param fileName nom du fichier
	 * @throws TemplateException s'affiche si eureur
	 * @throws IOException s'affiche si eureur
     * @see Command#execute(String)
     * @see DirectoryReceiver#list(String)
     */
	public Receiver execute(String fileName) throws IOException, TemplateException {
		((DirectoryReceiver)directoryReceiver).list(fileName);
		
		return this.directoryReceiver;
	}

}
