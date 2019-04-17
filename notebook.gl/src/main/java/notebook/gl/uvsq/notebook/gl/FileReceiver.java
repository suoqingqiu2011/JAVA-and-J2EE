package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

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
		fileName=fileName.replaceAll(" ", "_");
		cmd = "/usr/bin/rm "+path+"/"+fileName+suffix;
		String cmd2 = "/usr/bin/rm "+path+"/"+fileName+".html";
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			runtime.exec(cmd2);
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
		StringBuffer sb = new StringBuffer();
		sb.append("= "+fileName+"\n");
		fileName=fileName.replaceAll(" ", "_");
		File file = new File(path+File.separator+fileName+suffix);
		if(file.exists()) {
			System.err.println("File exists");
			return;
		}
		
		if (OSValidator.isUnix() == "unix")
		{
			String[] command= {"whoami","date +\"%d/%m/%Y\""};
			Process pr;
			try {
				for(String comm : command) {
					pr = Runtime.getRuntime().exec(comm);
		           InputStream is = pr.getInputStream();
			   		InputStreamReader isr = new InputStreamReader(is, "gbk"); 
			   		BufferedReader br = new BufferedReader(isr);
			   		String line;
			   		while ((line = br.readLine()) != null){
			   			line = line.replaceAll("\"","");
			   			sb.append(line+"\n");
			   		}
			   		is.close();
			   		isr.close();
			   		br.close();
		   		}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(OSValidator.isWindows() == "win")
		{
			String[] command1= {"C:"+File.separator+"Windows"+File.separator+"System32"+File.separator+"cmd.exe","/c","echo %username%"};
			String[] command2= {"C:"+File.separator+"Windows"+File.separator+"System32"+File.separator+"cmd.exe","/c","echo %date%"};
			String [][] commands = {command1,command2};
			Process p;
			try {
				for(String [] comm : commands) {
					p = Runtime.getRuntime().exec(comm);
			        Scanner sc = new Scanner(p.getInputStream());
			        while(sc.hasNext())
			        	sb.append(sc.nextLine()+"\n");
			        sc.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
			
		
		sb.append(":context: \n:project: \n\n");
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(sb.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (OSValidator.isUnix() == "unix")
		{
			String vimFile = editor+" "+path+File.separator+fileName+suffix;
			String[] command2 = {"/usr/bin/gnome-terminal", "--wait","-e", vimFile};
			Process pr2;
			try {

					pr2 = Runtime.getRuntime().exec(command2);
					pr2.waitFor();
		   		
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if (OSValidator.isWindows() == "win")
		{
			String[] commande = {editor,path+File.separator+fileName+suffix};
			Process p;
			try {
				p = Runtime.getRuntime().exec(commande);
				p.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(fileName+" has been added into "+path).reset());
		AnsiConsole.systemUninstall();
	}

	public void edit(String fileName) {
		
		fileName=fileName.replaceAll(" ", "_");
		if(!new File(path+File.separator+fileName+suffix).exists()) {
			System.err.println("You should add file first. Try add + filename or see help");
			return;
		}
		String vimFile = editor+" "+path+File.separator+fileName+suffix;
		String[] command = {"/usr/bin/gnome-terminal","--wait", "-e", vimFile};		
		try {
			Process pr;
			Runtime rt = Runtime.getRuntime();
			pr = rt.exec(command);
	       int exitVal = pr.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(fileName+" has been saved.").reset());
		AnsiConsole.systemUninstall();
	}

	public void view(String fileName) {
		fileName=fileName.replaceAll(" ", "_");

		if (OSValidator.isUnix() == "unix")
		{
		try {
			String html = asciidoctor.convertFile(new File(path+File.separator+fileName+suffix), new HashMap<String,Object>());
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("No such file");
			return;
		}
		
		cmd = browser+" "+path+File.separator+fileName+".html";
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			process.waitFor();
		}catch (IOException | InterruptedException e){
			e.printStackTrace();
		}	
		
		} else if (OSValidator.isWindows() == "win")
		{
			try {
				String html = asciidoctor.convertFile(new File(path+File.separator+fileName+suffix), new HashMap<String,Object>());
			}catch(Exception e) {
				e.printStackTrace();
				System.err.println("No such file");
				return;
			}
			
			cmd = browser+" "+path+File.separator+fileName+".html";
			try {
				Runtime runtime = Runtime.getRuntime();
				process = runtime.exec(cmd);
				process.waitFor();
			}catch (IOException | InterruptedException e){
				e.printStackTrace();
			}	
		}
		
	}

}
