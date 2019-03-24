package notebook.gl.uvsq.notebook.gl;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class DirectoryReceiverTest {

	private DirectoryReceiver directoryReceiver;
	@Before
	public void setUp() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File xmlFile = new File("config.xml");
		Document doc = builder.parse(xmlFile);
		String directory = doc.getElementsByTagName("directory").item(0).getFirstChild().getNodeValue();
		String ftlDir = doc.getElementsByTagName("freemarkerDirectory").item(0).getFirstChild().getNodeValue();
		String ftl = doc.getElementsByTagName("freemarkerTemplateFile").item(0).getFirstChild().getNodeValue();
		String fHtml = doc.getElementsByTagName("freemarkerTemplateOutHtml").item(0).getFirstChild().getNodeValue();
		this.directoryReceiver = new DirectoryReceiver(directory,ftlDir,ftl,fHtml);
	}

	@Test
	public void testSearch() {
//		DirectoryReceiver dr = new DirectoryReceiver("doc");
//		dr.search(null);
	}
	
	@Test
	public void testFreemarker() {
		this.directoryReceiver.list("");
	}

}
