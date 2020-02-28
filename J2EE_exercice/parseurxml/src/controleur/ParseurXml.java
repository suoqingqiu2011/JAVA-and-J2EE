package controleur;
import java.util.HashMap;
import java.io.File;
import java.io.IOException; 

import javax.xml.parsers.DocumentBuilder; //pour l�ouverture et la construction du DOM
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseurXml {
	
	public static void main(String[] args) throws ParserConfigurationException,SAXException, IOException, TransformerException {

		String fichier = "Monframework.xml";
		HashMap<String,Class> newmap=new HashMap<String,Class>();
	
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
				
		    Document document= builder.parse(new File(fichier));
		    
		    Element racine=document.getDocumentElement();
		    final NodeList bases=document.getElementsByTagName("action");
		    for (int i = 0; i < bases.getLength(); i++) {   
		    	Node base = bases.item(i);
		    	
		    	String valprov=null;
		    	String valserv=null;
		    	NodeList elements=base.getChildNodes();
		    	for (int j = 0; j < elements.getLength(); j++) {  
		    		Node enfant=elements.item(j);
		    		if(enfant.getNodeName().equals("provenance")){
		    			System.out.print(enfant.getTextContent()+"\n");
		    			valprov=enfant.getTextContent();
		    		}
		    		if(enfant.getNodeName().equals("servlet-action")){
		    			System.out.print(enfant.getTextContent()+"\n");
		    			valserv=enfant.getTextContent();
		    		}
		    	}
		    	
		    	try{
				    newmap.put(valprov,Class.forName(valserv));
			    }catch(ClassNotFoundException e){
			    	e.printStackTrace();
			    }
		    	
		    	 /*System.out.print(document.getElementsByTagName("provenance").item(i).getFirstChild().getNodeValue()+"");   
		        System.out.println(document.getElementsByTagName("servlet-action").item(i).getFirstChild().getNodeValue());*/        
		       }
		    System.out.print(newmap.keySet());
		    System.out.print(newmap.values());
		    ParseurXml p = new ParseurXml();
	}

}
