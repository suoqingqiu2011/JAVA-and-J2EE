package org.uvsq.gl.notebook.commands;
/**
 * Hériter la classe abstraire {@code Receiver} et définir les attributs 
 * que les différents récepteurs vont appliquer.
 *
 * @author Administrator
 * @since JDK1.8
 */
public abstract class Receiver {
	protected Process process;
	protected String cmd="";
}
