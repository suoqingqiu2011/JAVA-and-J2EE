package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.jruby.RubyIO.Sysopen;

public class DirectoryReceiver extends Receiver {

	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DirectoryReceiver(String path) {
		this.path = path;
	}
	
	//unused
	public void list() {
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
	
	public void list(String fileName) {
		cmd = "/usr/bin/ls "+path+fileName;
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

	public void search(String word) {	
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
		 		  System.out.println(Arrays.toString(hitDoc.getValues("fileContent")));
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
	
	public void search(String word,String attri) {	
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
		    		String index = (String) asciidoctor.readDocumentHeader(f).getAttributes().get(attri);
		    		index += " "+asciidoctor.readDocumentHeader(f).getDocumentTitle().getMain();
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
		 	System.out.println("We have found these notes for "+attri+" : "+word);
		 	for (int i = 0; i < hits.length; i++) {
		
		 		  Document hitDoc = isearcher.doc(hits[i].doc);
		 		  for(String item:hitDoc.getValues("fileContent")) {
		 			  if(item.indexOf(word)==0) {
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
	
	
//	public void search(String word, String attri) {
//	    try {
//	    	Analyzer analyzer = new StandardAnalyzer();
//		    Path indexPath = Files.createTempDirectory("tempIndex");
//		    Directory directory = FSDirectory.open(indexPath);
//		    IndexWriterConfig config = new IndexWriterConfig(analyzer);
//		    IndexWriter iwriter = new IndexWriter(directory, config);		   
//		    DirectoryWalker directoryWalker = new AsciiDocDirectoryWalker(getPath()); 
//		    Document doc = new Document(); 
//		    int count =0;
//		    for (File file : directoryWalker.scan()) {
//		    	if(file.getName().equals("index.adoc")) continue;
//		    	StringBuffer sb = new StringBuffer();
//		    	Outil.readToBuffer(sb, file.getPath());
//		       //String index = (String) asciidoctor.readDocumentHeader(file).getAttributes().get(attri);
//		       doc.add(new Field("fileContent", sb.toString(), TextField.TYPE_STORED));
//		       count += 1;
//		       iwriter.addDocument(doc);
//		    }
//		 	iwriter.close();
//		 	// Now search the index:
//		    DirectoryReader ireader = DirectoryReader.open(directory);
//		 	IndexSearcher isearcher = new IndexSearcher(ireader);
//		    // Parse a simple query that searches for "text":
//		 	QueryParser parser = new QueryParser("fileContent", analyzer);
//		 	word = "\\:"+attri+"\\: "+word;
//		 	Query query = parser.parse("test");
//		 	ScoreDoc[] hits = isearcher.search(query, count).scoreDocs;
//		 	// Iterate through the results:
//		 	for (int i = 0; i < hits.length; i++) {
//		 	      Document hitDoc = isearcher.doc(hits[i].doc);
//		 	      System.out.println(Arrays.toString(hitDoc.getValues("fileContent")));
//		    }	    	    	    	
//			ireader.close();
//			directory.close();
//		    IOUtils.rm(indexPath);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	    		
//	}
}
