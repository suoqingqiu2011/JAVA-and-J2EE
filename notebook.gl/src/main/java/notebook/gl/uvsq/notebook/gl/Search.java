package notebook.gl.uvsq.notebook.gl;

import java.io.IOException;
import java.util.List;

import freemarker.template.TemplateException;
import notebook.gl.uvsq.notebook.gl.App.Range;

/**
 * Hériter la classe abstraire {@code Command} et récrire les fonctions {@code execute(String)} et {@code update(DirectoryReceiver,Range)}
 * qui permet de trouver le fichier par le mot du clé à partir du document asciidoctor. 
 *
 * @author Administrator
 * @since JDK1.8
 */
public class Search extends Command {

	private Receiver directoryReceiver;
	private App.Range range = App.Range.UNSET;
	/**
	 * C'est le constructeur
	 * @param directoryReceiver directoryReceiver
	 * @param range range
	 */
	public Search(Receiver directoryReceiver,App.Range range) {
		this.directoryReceiver = directoryReceiver;
		this.range = range;
	}

	/**
     * Réaliser la fonction {@code search(String)} ou {@code search(String,String)}
	 * @throws IOException s'affiche si eureur
	 * @throws TemplateException s'affiche si eureur
     * @see Command#execute(String)
     * @see DirectoryReceiver#search(String)
     * @see DirectoryReceiver#search(String, String)
     */
	@Override
	public void execute(String fileName) throws IOException, TemplateException {
		if(this.range != App.Range.UNSET) {
			((DirectoryReceiver)directoryReceiver).search(fileName,this.range.toString());
		}else {
			((DirectoryReceiver)directoryReceiver).search(fileName);
		}
	}
	/**
     * Réaliser la fonction {@code update(DirectoryReceiver,Range)}
	 * @throws IOException s'affiche si eureur
     */
	@Override
	public void update(DirectoryReceiver dr, Range order) throws IOException {
		this.range = App.Range.UNSET;
		super.update(dr, order);
	}

}
