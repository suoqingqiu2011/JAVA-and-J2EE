package notebook.gl.uvsq.notebook.gl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.asciidoctor.AsciiDocDirectoryWalker;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Asciidoctor.Factory;
import org.asciidoctor.DirectoryWalker;

public abstract class Command {

	public abstract void execute(String fileName);
	public void update(DirectoryReceiver dr, String order) {
		Asciidoctor asciidoctor = Factory.create();
		 List<String> filesContext = new ArrayList<String>();
		 List<String> filesProject = new ArrayList<String>();
		 List<String> filesMonth = new ArrayList<String>();
		    DirectoryWalker directoryWalker = new AsciiDocDirectoryWalker(dr.getPath()); 

		    for (File file : directoryWalker.scan()) {
		    	filesContext.add(asciidoctor.readDocumentHeader(file).getDocumentTitle().toString());
		    	filesProject.add(asciidoctor.readDocumentHeader(file).getDocumentTitle().toString());
		    	filesMonth.add(asciidoctor.readDocumentHeader(file).getRevisionInfo().getDate());
		    }
		    
		    Collections.sort(filesContext);
		    Collections.sort(filesProject);
		    Collections.sort(filesMonth);
		    String html = "";
		    switch(order) {
		    	case "context":
		    		html = asciidoctor.convert(filesContext.toString(), new HashMap<String,Object>());
		    		break;
		    	case "project":
		    		html = asciidoctor.convert(filesProject.toString(), new HashMap<String,Object>());
		    		break;
		    	case "month":
		    		html = asciidoctor.convert(filesMonth.toString(), new HashMap<String,Object>());
		    		break;
		    }
		    File indexADoc = new File(dr.getPath()+"/index.adoc");
		    try {
				PrintWriter pw = new PrintWriter(indexADoc);
				pw.append(html);
				pw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	}

}
