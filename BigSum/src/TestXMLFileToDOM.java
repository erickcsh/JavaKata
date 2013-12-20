import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class TestXMLFileToDOM {

	public static final String TEST_FILE_PATH = "../test_file.xml";
	
	XMLFileToDOM xmlParser;
	
	public TestXMLFileToDOM(){
		xmlParser = new XMLFileToDOM(TEST_FILE_PATH);
	}
	
	@Test
	public void testGetTestNode(){
		String expected = "test";
		String actual = xmlParser.getNodesByTag("test").item(0).getNodeName();
		assertEquals("NODE_ERROR: Not getting the expected node",actual,expected);
	}
	
	@Test
	public void testGetNotExistingNode(){
		int expected = 0;
		int actual = xmlParser.getNodesByTag("not_existing").getLength();
		assertEquals("NOT_EMPTY_NODE: Not getting the expected node",actual,expected);
	}
	
	@Ignore
	@Test
	public void testGetNotExistingNodeChilds(){
		/*NodeList expected = null;
		//NodeList actual = xmlParser.getChildNodes("not_existing");
		assertEquals("NOT_EMPTY_NODE_CHILD_ERROR: Not getting the expected node",
				actual,expected);*/
	}
	
	@Test
	public void testGetTestChildNodes(){
		Node childs = xmlParser.getChildNodes("test");
		int expected = 2;
		System.out.println(childs.getNodeValue());
		int actual = 1;//childs.getLength();
		assertEquals("NOT_CORRECT_CHILDS_ERROR: Not getting the expected childs",expected,actual);
		/*String child1_expected_value = "1";
		String child2_expected_value = "2";
		String child1_actual_value = childs.item(0).getNodeValue();
		String child2_actual_value = childs.item(1).getNodeValue();
		assertEquals("NODE_ERROR: Not getting the expected node",child1_actual_value,child1_expected_value);
		assertEquals("NODE_ERROR: Not getting the expected node",child2_actual_value,child2_expected_value);*/
	}
	
	
}
