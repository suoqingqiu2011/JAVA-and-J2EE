package notebook.gl.uvsq.notebook.gl;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi.Color;


public class App 
{
	
//	private Command add;
//	private Command delete;
//	private Command search;
//	private Command list;
//	private Command view;
//	private Command edit;
	private String file;
	private Receiver fileReceiver;
	private Receiver directoryReceiver;
	private Receiver windowReceiver;
	
	public App(String file) {
		this.file = file;
		this.fileReceiver = new FileReceiver();
		this.windowReceiver = new WindowReceiver();
		this.directoryReceiver = new DirectoryReceiver();	
	}
	
	public void printJansiMenuDemo() {
		Reader in = new InputStreamReader(this.getClass().getResourceAsStream("menu.txt"));
		try {
			char[] buf = new char[1024];
			int l = 0;
			try {
				while ((l = in.read(buf)) >= 0) {
					for (int i = 0; i < l; i++) {
//						AnsiConsole.out.print(Ansi.ansi().fg(Color.YELLOW).a(buf[i]).reset());
						System.out.println(buf[i]);
					}
				}
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
//					AnsiConsole.out.print(Ansi.ansi().fg(Color.CYAN).a(buf[i]).reset());
					System.out.println(buf[i]);
				}
			}
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
    	
    	App app = new App(args[1]);
    	app.printJansiLogoDemo();
    	app.printJansiMenuDemo();
    	app.handleInput(args[0]);
    	
//    	 System.out.println( Ansi.ansi().eraseScreen().fg(Ansi.Color.RED).a("Hello").fg(Ansi.Color.GREEN).a(" World").reset() );
//        System.out.println( "Hello World!" );
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
			List fileReceivers = new ArrayList<FileReceiver>();
			c = new Search(fileReceivers);
			break;
		case "list":
			c = new ListFile(directoryReceiver);
			break;
		case "view":
			c = new View(fileReceiver,windowReceiver);
			break;
		case "edit":
			c = new Edit(fileReceiver);
			break;
		}
		c.execute(file);
	}
}
