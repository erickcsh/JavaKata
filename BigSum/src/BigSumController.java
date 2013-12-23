import java.math.BigInteger;
import java.util.ArrayList;

import org.w3c.dom.Element;


public class BigSumController {
	
	XMLFileManager xmlManager;
	BigSumCalculator sumCalculator;
	
	public BigSumController(String file){
		xmlManager = new XMLFileManager(file);
		ArrayList<BigInteger> bigIntegers = elementsToBigInteger(xmlManager.getChildNodes("input"));
		sumCalculator = new BigSumCalculator(bigIntegers);
	}
	
	public void calculateAndWriteFirst10DigitsOfSum(){
		String total = sumCalculator.getFirstNumbersOfSum(10);
		//TODO: Write to File
	}
	
	public ArrayList<BigInteger> elementsToBigInteger(ArrayList<Element> elements){
		ArrayList<BigInteger> castBigIntegers = new ArrayList<BigInteger>();
		for(Element element : elements){
			castBigIntegers.add(new BigInteger(element.getTextContent()));
		}
		return castBigIntegers;
	}

}
