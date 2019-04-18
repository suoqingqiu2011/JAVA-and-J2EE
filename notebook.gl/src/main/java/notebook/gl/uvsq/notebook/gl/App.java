package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import freemarker.template.TemplateException;
import notebook.gl.uvsq.notebook.gl.App.Range;

/**
 * Cette classe {@code App} est principale et réaliser toutes les opérations désirées.
 *
 * @author Administrator
 * @since JDK1.8
 */

public class App {
	/**
	 * Donner les numéros pour {@code PROJECT} ,{@code CONTEXT} ,{@code TITLE} ,{@code UNSET} ,{@code MONTH} ,
	 */	
	public enum Range {
		PROJECT, CONTEXT, TITLE, UNSET, MONTH
	};

	public Range range;
	
	/**
	 * mettre le range
	 * @param range range
	 */
	public void setRange(String range) {
		switch (range) {
		case "context":
			this.range = Range.CONTEXT;
			break;
		case "project":
			this.range = Range.PROJECT;
			break;
		default:
			this.range = Range.TITLE;
		}
	}
	
	/**
	 * obtenir le range
	 * @return range
	 */
	public Range getRange() {
		return range;
	}

	private static String file;
	
	/**
	 * obtenir le nom du fichier
	 * @return file
	 */
	public String getFile() {
		return file;
	}
	
	/**
	 * Mettre le nom du fichier
	 * @param file nom de fichier
	 */
	public void setFile(String file) {
		this.file = file;
	}

	private Range indexRange;

	/**
	 * obtenir l'index de range
	 * @return indexRange
	 */
	public Range getIndexRange() {
		return indexRange;
	}

	private Receiver fileReceiver;
	private Receiver directoryReceiver;

	/**
	 * C'est le constructeur permet récupérer les configurations à partir de 'config.xml' ou 'configW.xml' selon 
	 * des différents systèmes. les configurations sont présentés comme le path de répertiore, celui de 
	 * navigateur, celui de éditeur etc et en plus des caractéristiques basiques pour le template.
	 * 
	 * @param file
	 * @exception ParserConfigurationException s'affiche si eureur de la configuration.
	 * @exception SAXException s'affiche si eureur d'analyser les fichiers au format '.xml'.
	 * @exception IOException s'affiche si toutes les possibilités d'eureur de lire les fichiers.
	 */
	private App(String file) {
		
		this.file = file;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			File xmlFile = null;
			if (OSValidator.isUnix() == "unix")
			{
				xmlFile = new File("config.xml");
			}else if (OSValidator.isWindows() == "win")
			{
				xmlFile = new File("configW.xml");
			}
			Document doc = builder.parse(xmlFile);
			String directory = doc.getElementsByTagName("directory").item(0).getFirstChild().getNodeValue();
			String browser = doc.getElementsByTagName("browser").item(0).getFirstChild().getNodeValue();
			String editor = doc.getElementsByTagName("editor").item(0).getFirstChild().getNodeValue();
			String suffix = doc.getElementsByTagName("suffix").item(0).getFirstChild().getNodeValue();
			String range = doc.getElementsByTagName("order").item(0).getFirstChild().getNodeValue();
			switch (range) {
			case "context":
				indexRange = Range.CONTEXT;
				break;
			case "project":
				indexRange = Range.PROJECT;
				break;
			case "month":
				indexRange = Range.MONTH;
				break;
			}
			this.range = App.Range.UNSET;
			String ftlDir = doc.getElementsByTagName("freemarkerDirectory").item(0).getFirstChild().getNodeValue();
			String ftl = doc.getElementsByTagName("freemarkerTemplateFile").item(0).getFirstChild().getNodeValue();
			String fHtml = doc.getElementsByTagName("freemarkerTemplateOutHtml").item(0).getFirstChild().getNodeValue();
			this.directoryReceiver = new DirectoryReceiver(directory,ftlDir,ftl,fHtml);
			this.fileReceiver = new FileReceiver(directory, editor, browser, suffix);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static volatile App instance = null;
	/**
	 * Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
	 * d'éviter un appel coûteux à synchronized, une fois que l'instanciation est faite.
	 * 
	 * Le mot-clé {@code synchronized} sur ce bloc empêche toute instanciation multiple 
	 * même par différents "threads". Il est très important.
	 * 
	 * @return instance
	 */
	public final static App getInstance() {
        
        if (instance == null) {
           synchronized(App.class) {
             if (instance == null) {
               instance = new App(file);
             }
           }
        }
        return instance;
    }

	/**
	 * Cette fonction {@code printJansiMenuDemo()} est juste pour enrichir les références de command quand on
	 * va taper sur le console. Ces références sont définis dans le fichier 'menu.txt'.  
	 * @throws IOException s'affiche si eureur de lire les fichiers.
	 */
	public void printJansiMenuDemo() throws IOException{
		Reader in = new InputStreamReader(this.getClass().getResourceAsStream("menu.txt"));
		try {
			char[] buf = new char[1024];
			int l = 0;
			try {
				while ((l = in.read(buf)) >= 0) {
					for (int i = 0; i < l; i++) {
						AnsiConsole.out.print(Ansi.ansi().fg(Color.YELLOW).a(buf[i]).reset());
					}
				}
				AnsiConsole.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			closeQuietly(in);
		}

	}
	
	/**
	 * Cette fonction {@code printJansiLogoDemo()} est juste pour montrer un dessin de logo quand on
	 * va taper sur le console. Ce logo est dans le fichier 'logo.txt'.  
	 * @exception IOException s'affiche si eureur de lire les fichiers.
	 */
	public void printJansiLogoDemo() throws IOException {
		
		Reader in = new InputStreamReader(getClass().getResourceAsStream("logo.txt"));
		try {
			char[] buf = new char[1024];
			int l = 0;
			while ((l = in.read(buf)) >= 0) {
				for (int i = 0; i < l; i++) {
					AnsiConsole.out.print(Ansi.ansi().fg(Color.CYAN).a(buf[i]).reset());
				}
			}
			AnsiConsole.out.println();
		} finally {
			closeQuietly(in);
		}
	}

	/**
	 * Cette fonction {@code closeQuietly(Closeable)} va montrer des erreurs los de la fermeture de 'Stream'. 
	 * @param c close
	 * @throws IOException s'affiche si eureur de 'threads'.
	 */
	public void closeQuietly(Closeable c) throws IOException{
		try {
			c.close();
		} catch (IOException ioe) {
			ioe.printStackTrace(System.err);
		}
	}

	/**
	 * Cette fonction principale statique {@code main(String[])} va réaliser toutes les fonctionalités dans notre projets.
	 * Et elle peut reconnaître des formats de commands définis par nos propres moyens dans le console. 
	 * @param args String[] arges
	 * @exception IOException s'affiche si eureur de 'threads'.
	 * @throws InterruptedException s'affiche si eureur
	 * @throws TemplateException s'affiche si eureur
	 */
	public static void main(String[] args) throws IOException, InterruptedException, TemplateException {
		if (args.length != 0) {
			App app = App.getInstance();
			String arg = Arrays.toString(args);
			int i = arg.lastIndexOf("-r");
			if (i != -1) {
				app.setRange(args[args.length - 1]);
				app.setFile(arg.substring(arg.indexOf(" ") + 1, arg.indexOf("-r") - 2));
			} else {
				if (arg.contains(",")) {
					String tmpFile = arg.substring(arg.indexOf(",") + 1,arg.length()-1);
					tmpFile = tmpFile.replaceAll(" ","");
					tmpFile = tmpFile.replaceAll(","," ");
					app.setFile(tmpFile);
				}
			}
			app.handleInput(args[0]);
		} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String in = null;
			App app = new App("");
			app.printJansiLogoDemo();
			app.printJansiMenuDemo();
			AnsiConsole.systemUninstall();
			while ((in = br.readLine()) != null) {
				String[] arguments = in.split(" ");
				int i = in.lastIndexOf("-r");
				if (i != -1) {
					app.setRange(arguments[arguments.length - 1]);
					app.setFile(in.substring(in.indexOf(" ") + 1, in.indexOf("-r") - 1));
				} else {
					if (in.contains(" ")) {
						String tmpFile = in.substring(in.indexOf(" ") + 1);
						app.setFile(tmpFile);
					}
				}
				app.handleInput(arguments[0]);
			}
		}
	}

	/**
	 * Cette fonction {@code handleInput(String)} va reconnaître le premiere mot de command (le type du traitement) et 
	 * ensuite va rappeler toutes les fonctionalités. Mais tout à bord, elle va lancer une application externe 
	 * pour continuer toutes les opérations .
	 * @throws InterruptedException s'affiche si eureur
	 * @throws IOException s'affiche si eureur
	 * @throws TemplateException s'affiche si eureur
	 * 
	 * @see Add
	 * @see Delete
	 * @see Search
	 * @see ListFile
	 * @see View
	 * @see Edit
	 * @see Command#update(DirectoryReceiver, Range)
	 */
	private void handleInput(String comm) throws IOException, InterruptedException, TemplateException {
		Command c = null;
		switch (comm) {
		case "a":
		case "add":
			c = new Add(fileReceiver);
			break;
		case "d":
		case "delete":
			c = new Delete(fileReceiver);
			break;
		case "s":
		case "search":
			c = new Search(directoryReceiver, getRange());
			break;
		case "l":
		case "list":
			c = new ListFile(directoryReceiver);
			break;
		case "v":
		case "view":
			c = new View(fileReceiver);
			break;
		case "e":
		case "edit":
			c = new Edit(fileReceiver);
			break;
		case "q":
			System.exit(0);
		case "quit":
			System.exit(0);
		case "h":
		case "help":
		default:
			printJansiMenuDemo();
			return;
		}
		c.execute(file);
		if (file != null && file != "")
			c.update((DirectoryReceiver) directoryReceiver, indexRange);
		file = "";
		range = Range.UNSET;
	}
}
