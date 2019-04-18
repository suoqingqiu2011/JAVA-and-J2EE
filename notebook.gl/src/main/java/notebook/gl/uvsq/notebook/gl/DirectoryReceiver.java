package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;
import org.asciidoctor.AsciiDocDirectoryWalker;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Asciidoctor.Factory;
import org.asciidoctor.DirectoryWalker;
import org.asciidoctor.ast.DocumentHeader;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import org.json.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Hériter la classe abstraire {@code Receiver} et procéder toutes les opérations que les répertoires peuvent avoir .
 *
 * @author Administrator
 * @since JDK1.8
 */
public class DirectoryReceiver extends Receiver {

	private String path;
	private String ftlDir;
	private String ftl;
	private String ftlHtml;
	/**
	 * obtenir le chemin (path)
	 * @return path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 
	 * @param path chemin
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 
	 * @param path chemin
	 */
	public DirectoryReceiver(String path) {
		this.path = path;
	}
	/**
	 * C'est le constructeur.
	 * @param directory chemin de répertoire
	 * @param ftlDir répertoire
	 * @param ftl file template
	 * @param fHtml file html
	 */
	public DirectoryReceiver(String directory, String ftlDir, String ftl, String fHtml) {
		this(directory);
		this.ftlDir = ftlDir;
		this.ftl = ftl;
		this.ftlHtml = fHtml;
	}

	/**
	 * Cette fonction {@code list()} permet de lister tous les fichiers sur un bon chemin (path) .
	 * 
	 * @throws IOException s'affiche si eureur de parcourir le chemin (path).
	 */	
	public void list() throws IOException{
		System.out.println(path);
		cmd = "/usr/bin/ls "+path;
		System.out.println(cmd);
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
	/**
	 * Cette fonction {@code list(String)} permet de générer le document asciidoctor présentant 
	 * la liste en fusionnant les données JSON et le template .
	 * 
	 * @param fileName nom du fichier
	 * @throws IOException s'affiche si eureur de lire des fichiers.
	 * @throws TemplateException s'affichera si eureur de l'utilisation du Temple.
	 */
	public void list(String fileName) throws IOException,TemplateException{
	
		Asciidoctor asciidoctor = Factory.create();
		DirectoryWalker directoryWalker = new AsciiDocDirectoryWalker(getPath()); 
		
		Configuration configuration = new Configuration(Configuration.getVersion());
		try {
			configuration.setDirectoryForTemplateLoading(new File(ftlDir));
			configuration.setDefaultEncoding("utf-8");
			Template template = configuration.getTemplate(ftl);
			JSONObject object = new JSONObject();
			for (File file : directoryWalker.scan()) {
				 if(file.getName().equals("index.adoc")) continue;
		 		 AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(asciidoctor.readDocumentHeader(file).getDocumentTitle().getMain()).reset());
		 		 DocumentHeader dh = asciidoctor.readDocumentHeader(file);		 		 
			     JSONObject jsonObject1 = new JSONObject();
			     jsonObject1.put("Author",dh.getAuthor());
			     jsonObject1.put("Date",dh.getRevisionInfo().getDate());
			     jsonObject1.put("Project",dh.getAttributes().get("project"));
			     jsonObject1.put("Context",dh.getAttributes().get("context"));
			     object.put(dh.getDocumentTitle().getMain(),jsonObject1);
			}
			
			Map root = new HashMap();
			root.put("info", object);
				
			Writer out = new FileWriter(new File(ftlHtml));	
			template.process(root, out);
			out.flush();
			out.close();
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		{"ma deuxieme note":
			[{"date":"24/03/2019","author":{"firstName":"pavle","fullName":"pavle","initials":"p"}},
			{"context":"note","project":"calcul securise"}],
			
			"something wrong":[{"date":"24/03/2019","author":{"firstName":"pavle","fullName":"pavle","initials":"p"}},{"context":"bug","project":"prog"}],"test":[{"date":"24/03/2019","author":{"firstName":"pavle","fullName":"pavle","initials":"p"}},{"context":"test","project":"test"}],"mode dutilisation":[{"date":"24/03/2019","author":{"firstName":"pavle","fullName":"pavle","initials":"p"}},{"context":"bug","project":"prog"}],"ma premiere note":[{"date":"24/03/2019","author":{"firstName":"pavle","fullName":"pavle","initials":"p"}},{"context":"note","project":"gl"}],"cool kid":[{"date":"24/03/2019","author":{"firstName":"pavle","fullName":"pavle","initials":"p"}},{"context":"dairy","project":"dairy"}],"not cool kid":[{"date":"24/03/2019","author":{"firstName":"pavle","fullName":"pavle","initials":"p"}},{"context":"daily","project":"daily"}],"maven config tutorial":[{"date":"24/03/2019","author":{"firstName":"pavle","fullName":"pavle","initials":"p"}},{"context":"tuto","project":"gl"}]}
		*/
		
	}
	
	/**
	 * Cette fonction {@code search(String)} permet de chercher par mot clés 
	 * dans l’ensemble de la note
	 * 
	 * @param word mot de clé
	 * @throws IOException s'affiche si eureur de l'application de {@code Analyzer}, {@code Asciidoctor} ou {@code DirectoryWalker}.
	 * @throws TemplateException s'affichera si eureur de l'utilisation du Temple.
	 */
	public void search(String word) throws IOException, TemplateException {	
	    try {
	    	Analyzer analyzer = new StandardAnalyzer();
		    Path indexPath = Files.createTempDirectory("tempIndex");
		    Directory directory = FSDirectory.open(indexPath);
		    IndexWriterConfig config = new IndexWriterConfig(analyzer);
		    IndexWriter iwriter = new IndexWriter(directory, config);		   
		    Asciidoctor asciidoctor = Factory.create();
		    asciidoctor.convertDirectory(
		    	    new AsciiDocDirectoryWalker(getPath()),
		    	    new HashMap<String, Object>());
		    DirectoryWalker directoryWalker = new AsciiDocDirectoryWalker(getPath()); 
		    List<File> asciidocFiles = directoryWalker.scan(); 
		    int count = asciidocFiles.size();
		    for (File f : asciidocFiles) {
		    	if(f.getName().equals("index.adoc")) continue;
		    		Document doc = new Document(); 
		    		StringBuffer buffer = new StringBuffer();
		    		Outil.readToBuffer(buffer, f.getPath());
		 		    doc.add(new Field("fileContent", buffer.toString(), TextField.TYPE_STORED));
		 		    iwriter.addDocument(doc);
		    }
		    iwriter.close(); 
		 	// Now search the index:
		    DirectoryReader ireader = DirectoryReader.open(directory);
		 	IndexSearcher isearcher = new IndexSearcher(ireader);
		 	 // Parse a simple query that searches for "text":
		 	QueryParser parser = new QueryParser("fileContent", analyzer);
		 	Query query = parser.parse(word);
		 	ScoreDoc[] hits = isearcher.search(query, count).scoreDocs;
		 	// Iterate through the results:
		 	for (int i = 0; i < hits.length; i++) {
		 		  Document hitDoc = isearcher.doc(hits[i].doc);
		 		 AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a(Arrays.toString(hitDoc.getValues("fileContent"))).reset());
		 	}	    	    
			ireader.close();
			directory.close();
		    IOUtils.rm(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    		
	}
	
	/**
	 * Cette fonction {@code search(String)} permet de chercher par mot clésdans un élément
	 * particulier comme le titre ou un attribut .
	 * 
	 * @param word mot de clé
	 * @param attri attributs
	 * @throws IOException s'affiche si eureur de lire des fichiers.
	 * @throws TemplateException s'affichera si eureur de l'utilisation du Temple.
	 */
	public void search(String word,String attri) throws IOException,TemplateException{	
	    try {
	    	Analyzer analyzer = new StandardAnalyzer();
		    Path indexPath = Files.createTempDirectory("tempIndex");
		    Directory directory = FSDirectory.open(indexPath);
		    IndexWriterConfig config = new IndexWriterConfig(analyzer);
		    IndexWriter iwriter = new IndexWriter(directory, config);		   
		    Asciidoctor asciidoctor = Factory.create();
		    asciidoctor.convertDirectory(
		    	    new AsciiDocDirectoryWalker(getPath()),
		    	    new HashMap<String, Object>());
		    DirectoryWalker directoryWalker = new AsciiDocDirectoryWalker(getPath()); 
		    List<File> asciidocFiles = directoryWalker.scan(); 
		    int count = asciidocFiles.size();
		    for (File f : asciidocFiles) {
		    	if(f.getName().equals("index.adoc")) continue;
		    		Document doc = new Document(); 
		    		String index="";
		    		if(attri.toLowerCase().equals("title")) {
		    			index += asciidoctor.readDocumentHeader(f).getDocumentTitle().getMain();
		    		}
		    		else{
		    			index += (String) asciidoctor.readDocumentHeader(f).getAttributes().get(attri);
		    			index += " "+asciidoctor.readDocumentHeader(f).getDocumentTitle().getMain();
		    		}
		    		doc.add(new Field("fileContent", index, TextField.TYPE_STORED));
		 		    iwriter.addDocument(doc);
		    }
		    iwriter.close(); 
		 	// Now search the index:
		    DirectoryReader ireader = DirectoryReader.open(directory);
		 	IndexSearcher isearcher = new IndexSearcher(ireader);
		 	 // Parse a simple query that searches for "text":
		 	QueryParser parser = new QueryParser("fileContent", analyzer);
		 	Query query = parser.parse(word);
		 	ScoreDoc[] hits = isearcher.search(query, count).scoreDocs;
		 	// Iterate through the results:
		 	AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a("We have found these notes for "+attri+" : "+word).reset());
		 	for (int i = 0; i < hits.length; i++) {
		
		 		  Document hitDoc = isearcher.doc(hits[i].doc);
		 		  for(String item:hitDoc.getValues("fileContent")) {
		 			  if(attri.toLowerCase().equals("title")) {
		 				  System.out.println(item);
		 			  }
		 			  else if(item.indexOf(word)==0) {
		 				  System.out.println(item.substring(word.length()+1));
		 			  }
		 		  }	  
		 	}	    	    
			ireader.close();
			directory.close();
		    IOUtils.rm(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    		
	}
	

}
