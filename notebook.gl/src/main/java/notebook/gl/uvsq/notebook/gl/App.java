package notebook.gl.uvsq.notebook.gl;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi.Color;


public class App 
{
	
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
    	App app = new App();
    	app.printJansiLogoDemo();
    	app.printJansiMenuDemo();
    	while(true) {
    		Scanner sc = new Scanner(System.in);
    		String line = sc.nextLine();
    		app.handleInput(line.charAt(0));
    	}
//    	 System.out.println( Ansi.ansi().eraseScreen().fg(Ansi.Color.RED).a("Hello").fg(Ansi.Color.GREEN).a(" World").reset() );
//        System.out.println( "Hello World!" );
    }

	private void handleInput(char charAt) {
		switch(charAt) {
		case 'q':
			System.exit(1);
		case 'h':
			this.printJansiMenuDemo();
			break;
		}
	}
}
