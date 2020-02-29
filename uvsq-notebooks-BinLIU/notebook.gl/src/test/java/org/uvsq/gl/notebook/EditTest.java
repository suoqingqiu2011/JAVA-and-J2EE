package org.uvsq.gl.notebook;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.uvsq.gl.notebook.commands.Edit;
import org.uvsq.gl.notebook.commands.FileReceiver;
import org.uvsq.gl.notebook.tools.OSValidator;
import org.w3c.dom.Document;

public class EditTest {

	FileReceiver fr;
	File file = new File("testFileForJunit4");
	File directory = new File("doc");
	String suffix;
	File[] files;
	@Before
	public void setUp() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File xmlFile = null;
		if (OSValidator.isUnix() == "unix")
		{
			xmlFile = new File("config.xml");
		}else if (OSValidator.isWindows() == "win")
		{
			xmlFile = new File("configW.xml");
		}
		Document doc = builder.parse(xmlFile);
		String directory = doc.getElementsByTagName("directory").item(0).getFirstChild().getNodeValue();
		String browser = doc.getElementsByTagName("browser").item(0).getFirstChild().getNodeValue();
		String editor = doc.getElementsByTagName("editor").item(0).getFirstChild().getNodeValue();
		suffix = doc.getElementsByTagName("suffix").item(0).getFirstChild().getNodeValue();				
		fr = new FileReceiver(directory, editor, browser, suffix);
	}
	
	
	@Test
	public void testExecute() throws IOException, InterruptedException {
		assertNotNull(new Edit(fr).execute(file.getName()));
	}

}
