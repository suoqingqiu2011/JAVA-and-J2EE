package rdfssparql_BinLIU;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Derivation;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.RDFSRuleReasonerFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class CreateRDFfiles {

	public static void main(String[] args) throws IOException {
		System.out.println("********************** Selon la base de faits RDF: **********************");
		System.out.println("*************************************************************************");
		faits_RDF();
		
		System.out.println("\n********************* Selon le schema RDFS input: ***********************");
		System.out.println("*************************************************************************");
		schema_RDF();
	}
	
	public static void faits_RDF() {
		String inputFileName = "rdf_ex.ttl";
		
		Model model = ModelFactory.createDefaultModel();
		readFile(model, inputFileName);
		
		String queryString_select =  readSparqlFile("sparql/SELECT_rdf.sparql");
		System.out.println("\n********** SELECT query: **********");
		MyquerySelect(model, queryString_select);
		
		String queryString_ask = readSparqlFile("sparql/ASK_rdf.sparql");
		System.out.println("\n*********** ASK query: ***********");
		MyqueryAsk(model, queryString_ask);
		
		String queryString_con = readSparqlFile("sparql/CONSTRUCT_rdf.sparql");
		writeFile(model, queryString_con,"Mail.rdf","RDF");
	}
	
	
	public static void schema_RDF() {
		 	String DATA_FILE = "rdfs.ttl" ;
	      
	        Model data = RDFDataMgr.loadModel(DATA_FILE) ;
	        
	        OntModel model = ModelFactory.createOntologyModel();
	        Resource config = model
	                .createResource()
	                .addProperty(ReasonerVocabulary.PROPsetRDFSLevel, "simple");
			
	        Reasoner reasoner = RDFSRuleReasonerFactory.theInstance().create(config);
	        InfModel inf = ModelFactory.createInfModel(reasoner, data);
	     
	        PrintWriter out = new PrintWriter(System.out);
	        String trace = "";
	        for (StmtIterator i = inf.listStatements(); i.hasNext(); ) {
	            Statement s = i.nextStatement();
	            System.out.println("Statement is " + s);
	            for (Iterator<Derivation> id = inf.getDerivation(s); id.hasNext(); ) {
	                Derivation deriv = (Derivation) id.next();
	                deriv.printTrace(out, true);
	                trace += deriv.toString();
	            }
	        }
	        out.flush();
	        System.out.println("trace: "+trace);
	        
	        String queryString_select = readSparqlFile("sparql/SELECT_RDFS.sparql");
			System.out.println("\n********** SELECT query: **********");
			MyquerySelect(inf, queryString_select);
			
			String queryString_ask = readSparqlFile("sparql/ASK_RDFS.sparql");
			System.out.println("\n*********** ASK query: ***********");
			MyqueryAsk(inf, queryString_ask);
			
			String queryString_con = readSparqlFile("sparql/CONSTRUCT_RDFS.sparql");
			writeFile(inf, queryString_con,"RDFS.rdf","RDFS");
	}
	
	public static String readSparqlFile(String filename) {
		//List<String> sqlList = new ArrayList<String>();
		String queryString= null;
			try {
				InputStream is = new FileInputStream(filename);
				StringBuffer sqlSb = new StringBuffer();
				byte[] buff = new byte[1024];
				int byteRead = 0;
				while ((byteRead = is.read(buff)) != -1) {
					sqlSb.append(new String(buff, 0, byteRead,"utf-8"));
				}
				is.close();
				
				String[] sqlArr = sqlSb.toString().split("(;\\s*\\r\\n)|(;\\s*\\n)");
				for (int i = 0; i < sqlArr.length; i++) {
					queryString = sqlArr[i].replaceAll("--.*", "").trim();
				}
				/*if (!queryString.equals("")) {
					sqlList.add(queryString);
				}*/				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return queryString;
	}
	
	public static void readFile(Model model, String inputFileName) {
		InputStream in = null;
		try {
			in = new FileInputStream(new File(inputFileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}

		model.read(in, "", "TTL");
		// list the statements in the graph
		StmtIterator iter = model.listStatements();
		// print out the predicate, subject and object of each statement
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement(); // get next statement
			Resource sub = stmt.getSubject(); // get the subject
			Property pred = stmt.getPredicate(); // get the predicate
			RDFNode object = stmt.getObject(); // get the object

			String subject = sub.toString(); // get the subject
			String predicate = pred.toString(); // get the predicate

			System.out.print("URI du subject: " + subject + "; ");
			System.out.print(" URI du predicat: " + predicate + "; ");
			if (object instanceof Resource) {
				System.out.print(" URI de l'object: " + object);
			} else {// object is a literal
				System.out.print("URI de l'object: \"" + object.toString() + "\"");
			}
			System.out.println(" .");
		}
		System.out.println();
	}

	public static void MyquerySelect(Model model, String queryString_select) {
		Query query = QueryFactory.create(queryString_select);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();
            ResultSetFormatter.out(System.out, results, query);
        } catch (Exception e) {
            System.out.println("epic fail: " + e);
        }
	}
	
	public static void MyqueryAsk(Model model, String queryString_ask) {
		Query query = QueryFactory.create(queryString_ask);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            boolean result = qexec.execAsk();
            System.out.print("ASK query return a booleen value: ");
            ResultSetFormatter.out(System.out, result);
        } catch (Exception e) {
            System.out.println("epic fail: " + e);
        }
	}

	public static void writeFile(Model model, String queryString_con,String filename,String mode_rdf) {
		Query query = QueryFactory.create(queryString_con);
		QueryExecution qExecution = QueryExecutionFactory.create(query, model);

		ArrayList<String> fullmail = new ArrayList<String>();
		
		Model con_result = qExecution.execConstruct();
		StmtIterator iter = con_result.listStatements();
		
		if (mode_rdf.equals("RDF")) {
			while (iter.hasNext()) {
				Statement statement = iter.nextStatement();
				RDFNode object = statement.getObject();
				// System.out.print(subject.toString()+" "+predicate.toString()+" ");
				if (object instanceof Resource) {
					 fullmail.add(object.toString());
					// System.out.println(object.toString()+".");
				}else{
					 fullmail.add("\""+object.toString()+"\"");
					// System.out.println("\""+object.toString()+"\""+".");
				}
			}
	
			int i = 0;
			while (iter.hasNext()) {
				Statement statement1 = iter.nextStatement();
				Resource subj_eml = statement1.getSubject(); 
				subj_eml.addProperty(FOAF.mbox, fullmail.get(i)); 
				i++;
			}
		}
		
		FileWriter fwriter = null;
		try {
			fwriter = new FileWriter(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		con_result.write(fwriter); // write it by defaut 'xml'
		System.out.println("\n\n***** Resultat dans le cas de requ¨ºtes CONSTRUCT: *****");
		con_result.write(System.out, "TURTLE");
		System.out.println("--------------------------------------------------------");

		StmtIterator iter1 = con_result.listStatements();
		while (iter1.hasNext()) {
			Statement con_statement1 = iter1.nextStatement();
			Resource con_subject = con_statement1.getSubject();
			Property con_predicate = con_statement1.getPredicate();
			RDFNode con_object = con_statement1.getObject();

			System.out.print("URI du subject: " + con_subject.toString() + ";  URI du pr¨¦dicat: "  + con_predicate.toString() + ";  ");
			if (con_object instanceof Resource) {
				System.out.println("URI de l'object:" + con_object.toString() + ".");
			} else {
				System.out.println("URI de l'object: \"" + con_object.toString() + "\" .");
			}
		}System.out.println("*************************************************************************\n");
	}
}
