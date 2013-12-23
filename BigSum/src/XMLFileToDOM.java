import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
			document.getDocumentElement().normalize();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public Element getFirstNodeByTag(String tag) {
		NodeList matchingNodes = document.getElementsByTagName(tag);
		if(matchingNodes.getLength() == 0){
			return null;
		}
		if(matchingNodes.item(0).getNodeType() == Node.ELEMENT_NODE){
			return (Element) matchingNodes.item(0);
		}
		return null;
	}

	public ArrayList<Element> getChildNodes(String tag) {
		Element parent = getFirstNodeByTag(tag);
		if(parent != null){
			return getElementNodes(parent.getChildNodes());
		}
		else{
			return null;
		}
	}

	private ArrayList<Element> getElementNodes(NodeList nodes) {
		ArrayList<Element> elementNodes = new ArrayList<Element>();
		for(int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				elementNodes.add((Element) node);
			}
		}
		return elementNodes;
	}


}
