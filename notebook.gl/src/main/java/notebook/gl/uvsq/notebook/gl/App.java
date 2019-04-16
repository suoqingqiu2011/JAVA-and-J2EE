package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
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

public class App {
	public enum Range {
		PROJECT, CONTEXT, TITLE, UNSET, MONTH
	};

	public Range range;

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

	public Range getRange() {
		return range;
	}

	private static String file;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	private Range indexRange;

	public Range getIndexRange() {
		return indexRange;
	}

	private Receiver fileReceiver;
	private Receiver directoryReceiver;

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
	public final static App getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (instance == null) {
           // Le mot-clé synchronized sur ce bloc empêche toute instanciation
           // multiple même par différents "threads".
           // Il est TRES important.
           synchronized(App.class) {
             if (instance == null) {
               instance = new App(file);
             }
           }
        }
        return instance;
    }

	public void printJansiMenuDemo() {
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

	public void closeQuietly(Closeable c) {
		try {
			c.close();
		} catch (IOException ioe) {
			ioe.printStackTrace(System.err);
		}
	}

	public static void main(String[] args) throws IOException {
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

	private void handleInput(String comm) {
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
