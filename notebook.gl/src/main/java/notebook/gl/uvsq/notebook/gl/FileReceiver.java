package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Asciidoctor.Factory;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

public class FileReceiver extends Receiver {

	private String path;
	private String editor;
	private String browser;
	private Asciidoctor asciidoctor;
	private String suffix;
	
	public FileReceiver(String path,String editor,String browser,String suffix) {
		this.path = path;
		this.editor = editor;
		this.browser = browser;
		Asciidoctor asciidoctor = Factory.create();
		this.asciidoctor = asciidoctor;
		this.suffix = suffix;
	}
	
	public void del(String fileName) {
		//cmd = "cmd /c \"del "+fileName+"\"";
		cmd = "/usr/bin/rm "+path+"/"+fileName;
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "gbk"); 
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null){
				System.out.println(line);
			}
			is.close();
			isr.close();
			br.close();
		}catch (IOException e){
			e.printStackTrace();
		}	
	}

	public void add(String fileName) {
//		//cmd = "cmd /c \"type nul>"+fileName+"\"";
//		String vimFile = "/usr/bin/vim "+fileName;
//		String touchFile = "/usr/bin/touch "+fileName;
//		String echoFileName = "/usr/bin/echo '="+fileName+"' > "+fileName;
//		String echoWhoAmI = "/usr/bin/echo `whoami` >> "+fileName;
//		String echoDate = "/usr/bin/echo `date` >> "+fileName;
//		String echoContext = "/usr/bin/echo ':context:' >> "+fileName;
//		String echoProject = "/usr/bin/echo ':project:' >> "+fileName;
//		String echoTest = "/usr/bin/echo 23 >>r.txt";
//		String[] command = {"/usr/bin/gnome-terminal", "-e", vimFile};
//		String[] commands= {touchFile,echoFileName,echoWhoAmI,echoDate,echoContext,echoProject};
//		Process pr,process;
//		try {
//			//Runtime.getRuntime().exec(touchFile);
//			process = Runtime.getRuntime().exec(vimFile);
//			process.waitFor();
////			for(int i=0;i<commands.length;i++) {
////				process = Runtime.getRuntime().exec(echoFileName);
////				System.out.println(commands[i]);
////			}
//			//pr = Runtime.getRuntime().exec(command);
//			//pr.waitFor();  		
//			InputStream is = process.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is, "gbk"); 
//			BufferedReader br = new BufferedReader(isr);
//			String line;
//			while ((line = br.readLine()) != null){
//				System.out.println(line);
//			}
//			is.close();
//			isr.close();
//			br.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		String vimFile = editor+" "+path+"/"+fileName+suffix;
		String[] command = {"/usr/bin/gnome-terminal", "-e", vimFile};
		Process pr;
		try {
			pr = Runtime.getRuntime().exec(command);
			pr.waitFor();  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(fileName+" has been added into "+path).reset());
	}

	public void edit(String fileName) {
		String vimFile = editor+" "+path+"/"+fileName+suffix;
		String[] command = {"/usr/bin/gnome-terminal", "-e", vimFile};
		Process pr;
		try {
			pr = Runtime.getRuntime().exec(command);
			pr.waitFor();  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(fileName+" has been saved.").reset());
	}

	public void view(String fileName) {
		String html = asciidoctor.convertFile(new File(path+"/"+fileName+suffix), new HashMap<String,Object>());
		cmd = browser+" "+path+"/"+fileName+".html";
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			process.waitFor();
		}catch (IOException | InterruptedException e){
			e.printStackTrace();
		}	
	}

}
