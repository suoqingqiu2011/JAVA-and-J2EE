package org.uvsq.gl.notebook.commands;

import java.io.IOException;

/**
 * Hériter la classe abstraire {@code Command} et récrire la fonction {@code execute(String)} 
 * qui permet de générer le document asciioctor selon les fichiers .
 *
 * @author Administrator
 * @since JDK1.8
 */
public class View extends Command {

	private Receiver fileReceiver;
	/**
	 * C'est le constructeur
	 * @param fileReceiver fileReceiver
	 */
	public View(Receiver fileReceiver) {
		this.fileReceiver = fileReceiver;
	}

	/**
     * Réaliser la fonction {@code view(String)}
     * @param fileName nom du fichier
	 * @throws InterruptedException s'affiche si eureur
	 * @throws IOException s'affiche si eureur
     * @see FileReceiver#view(String)
     */
	@Override
	public Receiver execute(String fileName)  throws IOException, InterruptedException {
		((FileReceiver)fileReceiver).view(fileName);
		
		return this.fileReceiver;
	}

}
