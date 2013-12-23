import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLFileManager {

	Document document;
	
	public XMLFileManager(String file) {
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
		if(isNodeElement(matchingNodes.item(0))){
			return (Element) matchingNodes.item(0);
		}
		return null;
	}

	public ArrayList<Element> getChildNodes(String parent_tag) {
		Element parent = getFirstNodeByTag(parent_tag);
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
			if(isNodeElement(node)){
				elementNodes.add((Element) node);
			}
		}
		return elementNodes;
	}
	
	private boolean isNodeElement(Node node){
		return node.getNodeType() == Node.ELEMENT_NODE;
	}


}
