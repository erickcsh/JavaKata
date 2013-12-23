import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.w3c.dom.Element;


public class TestXMLFileManager {

	public static final String TEST_FILE_PATH = "../test_file.xml";
	
	XMLFileManager xmlManager;
	
	public TestXMLFileManager(){
		xmlManager = new XMLFileManager(TEST_FILE_PATH);
	}
	
	@Test
	public void testGetTestNode(){
		String expected = "test";
		String actual = xmlManager.getFirstNodeByTag("test").getNodeName();
		assertEquals("NODE_ERROR: Not getting the expected node",expected,actual);
	}
	
	@Test
	public void testGetNotExistingNode(){
		Element actual = xmlManager.getFirstNodeByTag("not_existing");
		assertEquals("NOT_EMPTY_NODE: Not getting the expected node",null,actual);
	}
	
	@Test
	public void testGetNotExistingNodeChilds(){
		ArrayList<Element> expected = null;
		ArrayList<Element> actual = xmlManager.getChildNodes("not_existing");
		assertEquals("NOT_EMPTY_NODE_CHILD_ERROR: Childs does not exist",
				expected,actual);
	}
	
	@Test
	public void testGetChildNodesOfNodeWithoutChilds(){
		ArrayList<Element> expected = new ArrayList<Element>();
		ArrayList<Element> actual = xmlManager.getChildNodes("empty_test");
		assertEquals("NOT_EMPTY_NODE_CHILD_ERROR: Childs not empty",
				expected,actual);
	}
	
	@Test
	public void testGetTestChildNodes(){
		ArrayList<Element> childs = xmlManager.getChildNodes("test");
		int expected = 2;
		int actual = childs.size();
		assertEquals("NOT_CORRECT_CHILDS_ERROR: Not getting the expected childs",expected,actual);
		String child1_expected_value = "Text";
		String child1_actual_value = childs.get(0).getTextContent();
		assertEquals("NODE_ERROR: Not getting the expected node",child1_actual_value,child1_expected_value);
	}
	
	
}
