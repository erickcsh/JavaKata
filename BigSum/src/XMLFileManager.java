import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;


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
	
	public void writeXMLFile(String filePath){
		try {
            Transformer transformer = getTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filePath)));

        } catch (Exception ex) {
            ex.printStackTrace();
        } 
	}

	private Transformer getTransformer()
			throws TransformerConfigurationException,
			TransformerFactoryConfigurationError {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		return transformer;
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

	public ArrayList<Element> getChildNodes(String parentTag) {
		Element parent = getFirstNodeByTag(parentTag);
		if(parent != null){
			return getElementNodes(parent.getChildNodes());
		}
		else{
			return null;
		}
	}
	
	public void setRelativeToParentElementText(String parentTag, String elementTag, String newText){
		Element parent = getFirstChildByTagRelativeToParentNode(parentTag, elementTag);
		if(parent != null){
			parent.setTextContent(newText);
		}
	}
	
	public Element getFirstChildByTagRelativeToParentNode(String parentTag, String childTag){
		Element parent = this.getFirstNodeByTag(parentTag);
		if(parent == null){
			return null;
		}
		NodeList childNodes = parent.getElementsByTagName(childTag);
		return (Element) childNodes.item(0);
		
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
