package notebook.gl.uvsq.notebook.gl;

import java.io.IOException;

/**
 * Hériter la classe abstraire {@code Command} et récrire la fonction {@code execute(String)} 
 * qui permet de supprimer les fichiers par le récepteur.
 *
 * @author Administrator
 * @since JDK1.8
 */

public class Delete extends Command {

	private Receiver fileReceiver;
	/**
	 * C'est le constructeur
	 * @param fileReceiver fileReceiver
	 */
	public Delete(Receiver fileReceiver) {
		// TODO Auto-generated constructor stub
		this.fileReceiver = fileReceiver;
	}
	/**
     * Réaliser la fonction {@code del(String)}
     * @param fileName nom du fichier
	 * @throws IOException s'affiche si eureur
     * @see Command#execute(String)
     * @see FileReceiver#del(String)
     */

	@Override
	public void execute(String fileName) throws IOException {
		((FileReceiver)fileReceiver).del(fileName);
	}

}
