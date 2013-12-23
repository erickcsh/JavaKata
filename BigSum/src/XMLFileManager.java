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
	
	public void writeXMLFile(String file_path){
		try {
            Transformer transformer = getTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file_path)));

        } catch (Exception ex) {
            ex.printStackTrace();;
        } 
	}

	private Transformer getTransformer()
			throws TransformerConfigurationException,
			TransformerFactoryConfigurationError {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
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

	public ArrayList<Element> getChildNodes(String parent_tag) {
		Element parent = getFirstNodeByTag(parent_tag);
		if(parent != null){
			return getElementNodes(parent.getChildNodes());
		}
		else{
			return null;
		}
	}
	
	public void setRelativeToParentElementText(String parent_tag, String element_tag, String new_text){
		Element parent = getFirstChildByTagRelativeToParentNode(parent_tag, element_tag);
		if(parent != null){
			parent.setTextContent(new_text);
		}
	}
	
	public Element getFirstChildByTagRelativeToParentNode(String parent_tag, String child_tag){
		ArrayList<Element> childs = getChildNodes(parent_tag);
		if(childs == null){
			return null;
		}
		return findChildByTag(childs, child_tag);
		
	}

	private Element findChildByTag(ArrayList<Element> childs, String child_tag) {
		int childSearchedIndex = -1;
		int count = -1;
		for(Element child : childs){
			count++;
			if(child.getTagName() == child_tag){
				childSearchedIndex = count;
				break;
			}
		}
		return getAppropiateElementFromIndex(childs, childSearchedIndex);
	}

	private Element getAppropiateElementFromIndex(ArrayList<Element> childs, int index) {
		if(index == -1){
			return null;
		}
		return childs.get(index);
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
