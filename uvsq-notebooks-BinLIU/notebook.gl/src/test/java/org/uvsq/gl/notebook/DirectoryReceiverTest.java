package org.uvsq.gl.notebook;

import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.uvsq.gl.notebook.commands.DirectoryReceiver;
import org.uvsq.gl.notebook.tools.OSValidator;
import org.w3c.dom.Document;

import freemarker.template.TemplateException;

public class DirectoryReceiverTest {

	private DirectoryReceiver dr;
	
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
	public void testSearchGlobal() throws IOException, TemplateException {
		dr.search("test");
	}
	
	@Test
	public void testSearchTitle() throws IOException, TemplateException {
		dr.search("test","title");
	}
	
	@Test
	public void testSearchContext() throws IOException, TemplateException {
		dr.search("test","context");
	}
	
	@Test
	public void testSearchProject() throws IOException, TemplateException {
		dr.search("test","project");
	}
	
	@Test
	public void testFreemarker() throws IOException, TemplateException {
		this.dr.list("");
	}
	
	@Test
	public void testListManagerWrite() throws IOException, TemplateException {	
		File jFile = new File("jsondata");
		long w1 = jFile.lastModified();
		dr.listManager("", "w");
		long w2 = jFile.lastModified();
		assertNotEquals(w1,w2);
	}
	
	@Test
	public void testListManagerRead() throws IOException, TemplateException {	
		File htmlFile = new File("list.adoc");
		long w1 = htmlFile.lastModified();
		dr.listManager("", "r");
		long w2 = htmlFile.lastModified();
		assertNotEquals(w1,w2);
	}

}
