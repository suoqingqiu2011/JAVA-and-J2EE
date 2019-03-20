package notebook.gl.uvsq.notebook.gl;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class App 
{
	private String file;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	private Receiver fileReceiver;
	private Receiver directoryReceiver;
	private Receiver windowReceiver;
	
	public App(String file) {
		this.file = file;	
		this.windowReceiver = new WindowReceiver();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			File xmlFile = new File("config.xml");
			Document doc = builder.parse(xmlFile);
			String directory = doc.getElementsByTagName("directory").item(0).getFirstChild().getNodeValue();
			String browser= doc.getElementsByTagName("browser").item(0).getFirstChild().getNodeValue();
			String editor = doc.getElementsByTagName("editor").item(0).getFirstChild().getNodeValue();
			String suffix = doc.getElementsByTagName("suffix").item(0).getFirstChild().getNodeValue();
			this.directoryReceiver = new DirectoryReceiver(directory);	
			this.fileReceiver = new FileReceiver(directory,editor,browser,suffix);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	
    public static void main( String[] args ) throws IOException
    {
    	App app;
    	if(args.length==2) {
    		app = new App(args[1]);
    		app.handleInput(args[0]);
    	}else if(args.length==1){
    		app = new App("");
    		app.handleInput(args[0]);
    	}else {
    		app = new App("");
    		app.printJansiLogoDemo();
        	app.printJansiMenuDemo();
        	String command = "";
        	Scanner sc = new Scanner(System.in);
        	while(true) {
        		command = sc.nextLine();
        		String[] command_file = command.split(" ");
        		if(command_file.length==2) {
        			app.setFile(command_file[1]);
        		}
        		app.handleInput(command_file[0]);
        	}
    	}	
    }

	private void handleInput(String comm) {
		Command c = null;
		switch(comm) {
		case "add":
			c = new Add(fileReceiver,directoryReceiver,windowReceiver);
			break;
		case "delete":
			c = new Delete(fileReceiver);
			break;
		case "search":
			c = new Search(directoryReceiver);
			break;
		case "list":
			c = new ListFile(directoryReceiver);
			break;
		case "view":
			c = new View(fileReceiver,windowReceiver);
			break;
		case "edit":
			c = new Edit(fileReceiver,directoryReceiver);
			break;
		case "quit":
			System.exit(0);
		default:
			
		}
		c.execute(file);
		c.update((DirectoryReceiver)directoryReceiver);
	}
}
