
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;


public class TestBigSumCalculator {

	
	BigSumCalculator bsCalculator;
	public TestBigSumCalculator(){
		ArrayList list = createBigIntArray();
		this.bsCalculator = new BigSumCalculator(list);
	}
	
	private ArrayList<BigInteger> createBigIntArray(){
		ArrayList list = new ArrayList<BigInteger>();
		for(int i = 0; i < 10; i++){
			list.add(new BigInteger("111222333444555666").add(BigInteger.ONE));
		}
		return list;
	}
	
	@Test
	public void testSumArrayList(){
		BigInteger actual = this.bsCalculator.sumAll();
		BigInteger expected = new BigInteger("1112223334445556670");
		assertEquals("SUMERROR: Not the expected sum",actual,expected);
	}
	
	@Test
	public void testGetFirst0SumNumbers(){
		String expected = new BigInteger("1112223334445556670").toString().substring(0, 0);
		String actual = this.bsCalculator.getFirstNumbersOfSum(0);
		assertEquals("SUBSUMERROR: Not the expected sum digits",actual,expected);
	}
	
	@Test
	public void testGetFirst10SumNumbers(){
		String expected = new BigInteger("1112223334445556670").toString().substring(0, 10);
		String actual = this.bsCalculator.getFirstNumbersOfSum(10);
		assertEquals("SUBSUMERROR: Not the expected sum digits",actual,expected);
	}
}
