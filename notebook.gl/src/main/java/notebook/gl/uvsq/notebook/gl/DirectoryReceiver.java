package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

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
import org.junit.Assert;

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

	public void search(String wordName) {
		
	    try {
	    	Analyzer analyzer = new StandardAnalyzer();
		    Path indexPath = Files.createTempDirectory("tempIndex");
		    Directory directory = FSDirectory.open(indexPath);
		    IndexWriterConfig config = new IndexWriterConfig(analyzer);
		    IndexWriter iwriter = new IndexWriter(directory, config);
		    Document doc = new Document();
		    String text = "This is the text to be indexed.";
		    doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
		    iwriter.addDocument(doc);
		    iwriter.close(); 
		    // Now search the index:
		    DirectoryReader ireader = DirectoryReader.open(directory);
		    IndexSearcher isearcher = new IndexSearcher(ireader);
		    // Parse a simple query that searches for "text":
		    QueryParser parser = new QueryParser("fieldname", analyzer);
		    Query query = parser.parse("text");
		    ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
		    Assert.assertEquals(1, hits.length);
		    // Iterate through the results:
		    for (int i = 0; i < hits.length; i++) {
		      Document hitDoc = isearcher.doc(hits[i].doc);
		    Assert.assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
		    }
			ireader.close();
			directory.close();
		    IOUtils.rm(indexPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    		
	}
}
