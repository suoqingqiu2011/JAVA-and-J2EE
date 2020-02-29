package org.uvsq.gl.notebook.commands;

import java.io.IOException;

/**
 * Hériter la classe abstraire {@code Command} et récrire la fonction {@code execute(String)} 
 * qui permet d'ajouter les fichiers par le récepteur.
 *
 * @author Administrator
 * @since JDK1.8
 * 
 */
public class Add extends Command {
	
	private Receiver fileReceiver;

	/**
	 * C'est le constructeur
	 * @param fileReceiver fileReceiver
	 */
	public Add(Receiver fileReceiver) {
		this.fileReceiver = fileReceiver;
	}


	/**
     * Réaliser la fonction {@code add(String)}
     * @param fileName nom du fichier
     * @throws InterruptedException s'affiche si eureur
     * @throws IOException s'affiche si eureur
     */
	@Override
	public Receiver execute(String fileName) throws IOException, InterruptedException {
		((FileReceiver)fileReceiver).add(fileName);
		
		return this.fileReceiver;
	}

}
