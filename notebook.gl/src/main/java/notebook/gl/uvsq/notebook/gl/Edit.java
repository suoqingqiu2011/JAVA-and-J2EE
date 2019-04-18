package notebook.gl.uvsq.notebook.gl;

import java.io.IOException;

/**
 * Hériter la classe abstraire {@code Command} et récrire la fonction {@code execute(String)} 
 * qui permet d'enregistrer les fichiers sur le bon chemin.
 *
 * @author Administrator
 * @since JDK1.8
 */
public class Edit extends Command {

	private Receiver fileReceiver;
	/**
	 * C'est le constructeur
	 * @param fileReceiver fileReceiver
	 */
	public Edit(Receiver fileReceiver) {
		this.fileReceiver = fileReceiver;
	}

	/**
     * Réaliser la fonction {@code edit(String)}
     * @param fileName nom du fichier
	 * @throws InterruptedException s'affiche si eureur
	 * @throws IOException s'affiche si eureur
     * @see Command#execute(String)
     * @see FileReceiver#edit(String)
     */
	@Override
	public void execute(String fileName) throws IOException, InterruptedException {
		((FileReceiver)fileReceiver).edit(fileName);
	}

}
