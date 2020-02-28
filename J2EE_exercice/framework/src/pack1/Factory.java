package pack1;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Factory {
	
	HashMap<String,Class> newmap=new HashMap<String,Class>();
	private static Factory instance=null;
	
	private Factory() throws ParserConfigurationException, SAXException, IOException{
	
		String fichier = "/eclipse-workspace/framework/Monframework.xml";
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
			
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
	    			//System.out.print(enfant.getTextContent()+"\n");
	    			valprov=enfant.getTextContent();
	    		}
	    		if(enfant.getNodeName().equals("servlet-action")){
	    			//System.out.print(enfant.getTextContent()+"\n");
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
	    System.out.println(newmap.keySet());
	    System.out.println(newmap.values());
	   // ParseurXml p = new ParseurXml();
	}
  
	
	public Action getAction(String provenance){
		@SuppressWarnings("rawtypes")
		Class k=(Class)newmap.get(provenance);
		System.out.println(provenance+"\n");
		Action Ac=null;
		
		try{
			Ac=(Action)k.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return Ac;
	}
	
	public static Factory getInstance(){
		if(instance==null){
			try{
				instance=new Factory();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return instance;
	}
}
