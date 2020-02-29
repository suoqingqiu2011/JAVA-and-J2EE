package org.uvsq.gl.notebook;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.uvsq.gl.notebook.commands.Add;
import org.uvsq.gl.notebook.commands.Command;
import org.uvsq.gl.notebook.commands.FileReceiver;
import org.uvsq.gl.notebook.tools.OSValidator;
import org.w3c.dom.Document;

import freemarker.template.TemplateException;

public class AddTest {

	private Command comm;
	private File file = new File("testFileForJunit4");
	private File directory = new File("doc");
	private String suffix;
	FileReceiver fileReceiver;

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
		fileReceiver = new FileReceiver(directory, editor, browser, suffix);
		comm = new Add(fileReceiver);
	}

	@Test
	public void testExecute() {
		try {
			if(file.exists()) assertTrue(file.delete());
			comm.execute(file.getName());
			File[] files = directory.listFiles();
			boolean flag = false;
			for(File f:files) {
				if(f.getName().equals(file.getName()+suffix)) {
					flag = true;
				}
			}
			assertTrue(flag);
		} catch (IOException | InterruptedException | TemplateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExecuteString() throws IOException, InterruptedException {
		assertNotNull(new Add(fileReceiver).execute(file.getName()));
	}

}
