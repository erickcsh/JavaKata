import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.w3c.dom.Element;


public class TestXMLFileToDOM {

	public static final String TEST_FILE_PATH = "../test_file.xml";
	
	XMLFileToDOM xmlParser;
	
	public TestXMLFileToDOM(){
		xmlParser = new XMLFileToDOM(TEST_FILE_PATH);
	}
	
	@Test
	public void testGetTestNode(){
		String expected = "test";
		String actual = xmlParser.getFirstNodeByTag("test").getNodeName();
		assertEquals("NODE_ERROR: Not getting the expected node",expected,actual);
	}
	
	@Test
	public void testGetNotExistingNode(){
		Element actual = xmlParser.getFirstNodeByTag("not_existing");
		assertEquals("NOT_EMPTY_NODE: Not getting the expected node",null,actual);
	}
	
	@Test
	public void testGetNotExistingNodeChilds(){
		ArrayList<Element> expected = null;
		ArrayList<Element> actual = xmlParser.getChildNodes("not_existing");
		assertEquals("NOT_EMPTY_NODE_CHILD_ERROR: Childs does not exist",
				expected,actual);
	}
	
	@Test
	public void testGetChildNodesOfNodeWithoutChilds(){
		ArrayList<Element> expected = new ArrayList<Element>();
		ArrayList<Element> actual = xmlParser.getChildNodes("empty_test");
		assertEquals("NOT_EMPTY_NODE_CHILD_ERROR: Childs not empty",
				expected,actual);
	}
	
	@Test
	public void testGetTestChildNodes(){
		ArrayList<Element> childs = xmlParser.getChildNodes("test");
		int expected = 2;
		int actual = childs.size();
		assertEquals("NOT_CORRECT_CHILDS_ERROR: Not getting the expected childs",expected,actual);
		String child1_expected_value = "Text";
		String child1_actual_value = childs.get(0).getTextContent();
		assertEquals("NODE_ERROR: Not getting the expected node",child1_actual_value,child1_expected_value);
	}
	
	
}
