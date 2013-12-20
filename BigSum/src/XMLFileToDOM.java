import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLFileToDOM {

	Document document;
	
	public XMLFileToDOM(String file) {
		try{
			File xmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.parse(xmlFile);
			//document.getDocumentElement().normalize();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public NodeList getNodesByTag(String tag) {
		return document.getLastChild().getElementById(tag). .getElementsByTagName(tag);
	}

	public Node getChildNodes(String tag) {
		Node parent = getNodesByTag(tag).item(0);
		if(parent != null){
			return parent.getChildNodes().item(0);
		}
		else{
			return null;
		}
	}


}
