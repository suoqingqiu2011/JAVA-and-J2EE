package org.uvsq.gl.notebook.commands;

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
import org.uvsq.gl.notebook.tools.OSValidator;
import org.uvsq.gl.notebook.tools.Outil;
import org.fusesource.jansi.AnsiConsole;

/**
 * Hériter la classe abstraire {@code Receiver} et procéder toutes les opérations que les fichiers peuvent avoir .
 *
 * @author Administrator
 * @since JDK1.8
 */
public class FileReceiver extends Receiver {

	private String path;
	private String editor;
	private String browser;
	private Asciidoctor asciidoctor;
	private String suffix;
	
	/**
	 * C'est le constructeur.
	 * @param path chemin 
	 * @param editor editeur
	 * @param browser navigateur
	 * @param suffix .adoc
	 */
	public FileReceiver(String path,String editor,String browser,String suffix) {
		this.path = path;
		this.editor = editor;
		this.browser = browser;
		Asciidoctor asciidoctor = Factory.create();
		this.asciidoctor = asciidoctor;
		this.suffix = suffix;
	}

	
	public String getBrowser() {
		return browser;
	}

	/**
	 * Cette fonction {@code del(String)} permet de supprimer des notes déjà existants dans un fichier .
	 * 
	 * @param fileName nom du fichier
	 * @throws IOException s'affiche si erreur de lire les fichiers.
	 */	
	public void del(String fileName) throws IOException{
		
		fileName=fileName.replaceAll(" ", "_");
		if (OSValidator.isUnix() == "unix")
		{
			cmd = "/usr/bin/rm "+path+File.separator+fileName+suffix;
			String cmd2 = "/usr/bin/rm "+path+File.separator+fileName+".html";
			try {
				//Tests Existence fichier avant suppression.
				//File file1 = new File(path+File.separator+fileName+suffix);
				//File file2 = new File(path+File.separator+fileName+".html");
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
		}else if(OSValidator.isWindows() == "win")
		{
			
			String[] cmd= {"C:"+File.separator+"Windows"+File.separator+"System32"+File.separator+"cmd.exe","/c","del"+" "+path+File.separator+fileName+suffix};
			String[] cmd2= {"C:"+File.separator+"Windows"+File.separator+"System32"+File.separator+"cmd.exe","/c","del"+" "+path+File.separator+fileName+".html"};
							
			//System.out.println("del"+" "+StringEscapeUtils.unescapeJava(path)+File.separator+fileName+suffix);
			File file1 = new File(path+File.separator+fileName+suffix);
			File file2 = new File(path+File.separator+fileName+".html");
			
			if (file1.exists())
			{
				try {
					Runtime runtime = Runtime.getRuntime();				
					process = runtime.exec(cmd);			
					}catch (IOException e){
						e.printStackTrace();
					}	
				if(file2.exists())
				{
					try {
						Runtime runtime = Runtime.getRuntime();				
						process = runtime.exec(cmd2);			
					}catch (IOException e){
							e.printStackTrace();
					}
				}else
				{
					System.err.println("Impossible de supprimer le fichier "+fileName+".html");
				}
				AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(fileName+" a été supprimé.").reset());
				AnsiConsole.systemUninstall();
			}else
			{
				System.err.println("Impossible de supprimer le fichier "+fileName+suffix);
			}
			
		}
		
		
	}

	/**
	 * Cette fonction {@code add(String)} permet d'ajouter des notes déjà existants dans un fichier 
	 * ou ceux_ci rédigés par l'éditeur de texte (Vim).
	 * Et plus, elle s'affiche son contextes .
	 * 
	 * @param fileName nom du fichier
	 * @throws IOException s'affiche si eureur de lire les fichiers.
	 * @throws InterruptedException s'affichera si eureur de Thread.
	 */	
	public void add(String fileName) throws IOException,InterruptedException{
		StringBuffer sb = new StringBuffer();
		sb.append("= "+Outil.nameTransform(new File(fileName).getName(),suffix)+"\n");
		fileName=fileName.replaceAll(" ", "_");
		File file = new File(path+File.separator+fileName+suffix);
		if(file.exists()) {
			System.err.println("Ce nom de fichier existe déja.");
			//System.err.println("File exists");
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
		AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(fileName+" a été ajouté dans "+path).reset());

		AnsiConsole.systemUninstall();
	}

	/**
	 * Cette fonction {@code edit(String)} permet de mofifier des notes déjà existants dans un fichier 
	 * qui est rédigé par l'éditeur de texte (Vim).
	 * 
	 * @param fileName nom du fichier
	 * @throws IOException s'affiche si eureur de lire les fichiers.
	 * @throws InterruptedException s'affichera si eureur de Thread.
	 */	
	public void edit(String fileName) throws IOException,InterruptedException{
		
		fileName=fileName.replaceAll(" ", "_");
		if(!new File(path+File.separator+fileName+suffix).exists()) {
			
			System.err.println("Vous devriez d'abord ajouter le fichier. add + nom du fichier ou voir l'aide");

			return;
		}
		if (OSValidator.isUnix() == "unix")
		{
			String vimFile = editor+" "+path+File.separator+fileName+suffix;
			String[] command = {"/usr/bin/gnome-terminal","--wait", "-e", vimFile};		
			try {
				Process pr;
				Runtime rt = Runtime.getRuntime();
				pr = rt.exec(command);
				pr.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if (OSValidator.isWindows() == "win")
		{
			String[] commande = {editor,path+File.separator+fileName+suffix};
			try {
				Process pr;
				Runtime rt = Runtime.getRuntime();
				pr = rt.exec(commande);
				pr.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(new File(fileName).getName()+" a été enregistré.").reset());

		//	AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(new File(fileName).getName()+" has been saved.").reset());
		AnsiConsole.systemUninstall();
	}

	/**
	 * Cette fonction {@code view(String)} permet de voir une note sur la navigateur. 
	 * Elle peut transformer toutes les types de fichiers au format de html en utilisant
	 * le langage 'asciidoctor'.
	 * 
	 * @param fileName nom du fichier
	 * @throws IOException s'affiche si eureur de lire les fichiers.
	 * @throws InterruptedException s'affichera si eureur de Thread.
	 */	
	public void view(String fileName) throws IOException,InterruptedException {
		fileName=fileName.replaceAll(" ", "_");

		if (OSValidator.isUnix() == "unix")
		{
		try {
			String html = asciidoctor.convertFile(new File(path+File.separator+fileName+suffix), new HashMap<String,Object>());
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Ce fichier n'existe pas");

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
				
				System.err.println("Ce fichier n'existe pas");
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
