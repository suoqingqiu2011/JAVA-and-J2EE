package org.uvsq.gl.notebook.commands;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asciidoctor.AsciiDocDirectoryWalker;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Asciidoctor.Factory;
import org.uvsq.gl.notebook.App;
import org.uvsq.gl.notebook.App.Range;

import freemarker.template.TemplateException;

import org.asciidoctor.DirectoryWalker;

/**
 * Cette classe abstraire {@code Command} est définie par le fondement des
 * différents fonctionalités.
 *
 * @author Administrator
 * @since JDK1.8
 */
public abstract class Command {
	/**
	 * Cette fonction abstraire {@code execute(String)} permet de réaliser des
	 * différents fonctionalités comme {@code del(String)}
	 * ,{@code add(String)},{@code edit(String)} etc.
	 * 
	 * @param fileName nom du fichier
	 * @throws InterruptedException s'affiche si eureur
	 * @throws IOException s'affiche si eureur
	 * @throws TemplateException s'affiche si erreur
	 */	
	public abstract Receiver execute(String fileName) throws IOException, InterruptedException, TemplateException;
	/**
	 * Cette fonction {@code update(DirectoryReceiver, App.Range)},elle réalise la
	 * fonctionalité de recherche principalement. On parcours tous les types
	 * 'asciidoctor' de fichiers et va trouver le fichier 'index.adoc' pour mettre à
	 * jour des caractéristiques comme la date ,le titre et des attributs. Sinon on
	 * crée un nouveau fichier 'index.adoc' et associe un ensemble de note dedans.
	 * 
	 * @param dr    directoryReceiver
	 * @throws IOException s'affiche si eureur de rédiger les fichiers 'index.adoc'.
	 */
	public void update(DirectoryReceiver dr) throws IOException {
		Asciidoctor asciidoctor = Factory.create();
		List<String> files = new ArrayList<String>();
		DirectoryWalker directoryWalker = new AsciiDocDirectoryWalker(dr.getPath());
		Map<String, ArrayList<String>> filesMap = new HashMap<String, ArrayList<String>>();
		for (File file : directoryWalker.scan()) {
			if (file.getName().equals("index.adoc"))
				continue;
			String value = (String) asciidoctor.readDocumentHeader(file).getAttributes().get("context");
			List<String> tmpFiles = filesMap.get(value);
			if (tmpFiles != null)
				files = tmpFiles;
			files.add(asciidoctor.readDocumentHeader(file).getDocumentTitle().getMain());
			filesMap.put(value, (ArrayList<String>) files);
			files = new ArrayList<String>();
		}
		File indexADoc = new File(dr.getPath() + File.separator + "index.adoc");
		PrintWriter pw = new PrintWriter(indexADoc);

		pw.append("= Index.adoc\n");
		pw.append("It's a file generated automaticly, please don't change its content. <author@email.fr>\n");
		pw.append("\n");
		pw.append("============================Context\n");
		pw.append("\n");
		for (String key : filesMap.keySet()) {
			pw.append("* " + key + "\n");
			files = filesMap.get(key);
			Collections.sort(files);
			pw.append("* " + files.toString() + "\n");
			pw.append("\n");
		}
		pw.append("\n");
		filesMap.clear();
		files.clear();
		pw.append("============================Project\n");
		pw.append("\n");
		for (File file : directoryWalker.scan()) {
			if (file.getName().equals("index.adoc"))
				continue;
			String value = (String) asciidoctor.readDocumentHeader(file).getAttributes().get("project");
			List<String> tmpFiles = filesMap.get(value);
			if (tmpFiles != null)
				files = tmpFiles;
			files.add(asciidoctor.readDocumentHeader(file).getDocumentTitle().getMain());
			filesMap.put(value, (ArrayList<String>) files);
			files = new ArrayList<String>();
		}

		for (String key : filesMap.keySet()) {
			pw.append("* " + key + "\n");
			files = filesMap.get(key);
			Collections.sort(files);
			pw.append("* " + files.toString() + "\n");
			pw.append("\n");
		}
		pw.append("\n");
		pw.append("============================Month\n");
		pw.append("\n");
		filesMap.clear();
		files.clear();
		for (File file : directoryWalker.scan()) {
			if (file.getName().equals("index.adoc"))
				continue;
			String value = asciidoctor.readDocumentHeader(file).getRevisionInfo().getDate();
			if (value != null) {
				value = value.split("/")[1];
				switch (value) {
				case "01":
					value = "January";
					break;
				case "02":
					value = "Febuary";
					break;
				case "03":
					value = "March";
					break;
				case "04":
					value = "April";
					break;
				case "05":
					value = "May";
					break;
				case "06":
					value = "June";
					break;
				case "07":
					value = "July";
					break;
				case "08":
					value = "August";
					break;
				case "09":
					value = "September";
					break;
				case "10":
					value = "October";
					break;
				case "11":
					value = "November";
					break;
				case "12":
					value = "December";
					break;
				}
			}
			List<String> tmpFiles = filesMap.get(value);
			if (tmpFiles != null) files = tmpFiles;
			files.add(asciidoctor.readDocumentHeader(file).getDocumentTitle().getMain());
			filesMap.put(value, (ArrayList<String>) files);
			files = new ArrayList<String>();
		}

		for (String key : filesMap.keySet()) {
			pw.append("* " + key + "\n");
			files = filesMap.get(key);
			Collections.sort(files);
			pw.append("* " + files.toString() + "\n");
			pw.append("\n");
		}
		pw.append("\n");
		pw.close();

	}
}
