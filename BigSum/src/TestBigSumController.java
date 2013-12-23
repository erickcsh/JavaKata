import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;


public class TestBigSumController {
	
	private final String TEST_BIG_NUM_FILE = "../test_bs_input.xml";
	
	private BigSumController sumController;
	
	public TestBigSumController(){
		sumController = new BigSumController(TEST_BIG_NUM_FILE);
	}
	
	@Test
	public void testCreateBigIntegerArray(){
		ArrayList<BigInteger> expected = new ArrayList<BigInteger>();
		expected.add(new BigInteger("37107287533902102798797998220837590246510135740250"));
		expected.add(new BigInteger("46376937677490009712648124896970078050417018260538"));
		XMLFileManager xmlManager = new XMLFileManager(TEST_BIG_NUM_FILE);
		ArrayList<BigInteger> actual = sumController.elementsToBigInteger(xmlManager.getChildNodes("input"));
		assertEquals("BIG_INTEGER_CAST: not correct parser from elements to big integer",expected,actual);
	}
	

}
