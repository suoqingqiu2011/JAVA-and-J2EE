package org.uvsq.gl.notebook;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.uvsq.gl.notebook.commands.DirectoryReceiver;
import org.uvsq.gl.notebook.commands.FileReceiver;
import org.uvsq.gl.notebook.commands.ListFile;
import org.uvsq.gl.notebook.tools.OSValidator;
import org.w3c.dom.Document;

import freemarker.template.TemplateException;

public class ListFileTest {

	DirectoryReceiver dr;
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
		String ftlDir = doc.getElementsByTagName("freemarkerDirectory").item(0).getFirstChild().getNodeValue();
		String ftl = doc.getElementsByTagName("freemarkerTemplateFile").item(0).getFirstChild().getNodeValue();
		String fHtml = doc.getElementsByTagName("freemarkerTemplateOutHtml").item(0).getFirstChild().getNodeValue();
		this.dr = new DirectoryReceiver(directory,ftlDir,ftl,fHtml);
	}
	@Test
	public void testExecute() throws IOException, TemplateException {
		assertNotNull(new ListFile(dr).execute(file.getName()));
	}
}