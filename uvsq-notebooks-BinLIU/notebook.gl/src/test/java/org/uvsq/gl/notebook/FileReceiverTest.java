package org.uvsq.gl.notebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.uvsq.gl.notebook.commands.FileReceiver;
import org.uvsq.gl.notebook.tools.OSValidator;
import org.w3c.dom.Document;

public class FileReceiverTest {

	private FileReceiver fr;
	private File file = new File("testFileForJunit");
	private File directory = new File("doc");
	private String suffix;
	private File[] files;
	
	@Before
	public void setUp() throws Exception {
		System.err.println("Merci de fermer directement les fenetres s'il y en a");
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
	public void testFileReceiver() {
		assertNotNull(fr);
	}

	@Test
	public void testAdd() throws IOException, InterruptedException {
		files = directory.listFiles();
		for(File f : files) {
			if((f.getName()).equals(file.getName()+suffix)) {
				assertTrue(f.delete());
			}
		}
		try {
			fr.add(file.getName());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		files = directory.listFiles();
		for(File f : files) {
			if(f.getName()==file.getName()) {
				assertEquals(f.hashCode(), file.hashCode());
			}
		}
	}
	
	@Test
	public void testEdit() throws IOException, InterruptedException {
		fr.add(file.getName());
		File fileEdit = new File(file.getName()+suffix);	
		long size = fileEdit.lastModified();
		//System.out.println(size);
		try {
			FileWriter fw = new FileWriter(fileEdit);
			fw.append('e');
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		fr.edit(file.getName());
		long sizeEdit = fileEdit.lastModified();
		//System.out.println(sizeEdit);
		//assertTrue(fileEdit.delete());
		assertNotEquals(size,sizeEdit);
		
	}

	@Test
	public void testView() throws IOException, InterruptedException {
		//String fileName = file.getName();
		fr.add(file.getName());
		fr.view(file.getName());
		if(OSValidator.isWindows() == "win")
			//Runtime.getRuntime().exec("taskkill /F /IM  firefox.exe");
			Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		files = directory.listFiles();
		for(File f : files) {
			if((f.getName()).equals(file.getName()+".html")) {
				assertEquals(f.getName(),file.getName()+".html");
			}
		}
	}
	
	@Test
	public void testDel() throws IOException, InterruptedException {
		fr.add(file.getName());
		files = directory.listFiles();
		System.out.println(directory.listFiles().length);
		int size = files.length;
		fr.view(file.getName());
		if(OSValidator.isWindows() == "win")
			//Runtime.getRuntime().exec("taskkill /F /IM  firefox.exe");
			Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		fr.del(file.getName());
		files = directory.listFiles();
		int sizeDel = files.length;
		
		System.out.println(sizeDel);
		
		assertTrue(size>sizeDel);
	}



	
	

	

}
