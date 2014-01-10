import java.math.BigInteger;
import java.util.ArrayList;

import org.w3c.dom.Element;


public class BigSumController {
	
	private final String INPUT_TAG = "input";
	private final String OUTPUT_TAG = "output";
	private final String NUMBER_TAG = "number";
	
	XMLFileManager xmlManager;
	BigSumCalculator sumCalculator;
	String file;
	
	public BigSumController(String file){
		this.file = file;
		xmlManager = new XMLFileManager(file);
		ArrayList<BigInteger> bigIntegers = elementsToBigInteger(xmlManager.getChildNodes(INPUT_TAG));
		sumCalculator = new BigSumCalculator(bigIntegers);
	}
	
	public void calculateAndWriteFirst10DigitsOfSum(){
		String total = sumCalculator.getFirstNumbersOfSum(10);
		xmlManager.setRelativeToParentElementText(OUTPUT_TAG, NUMBER_TAG, total);
		xmlManager.writeXMLFile(file);
	}
	
	public ArrayList<BigInteger> elementsToBigInteger(ArrayList<Element> elements){
		ArrayList<BigInteger> castBigIntegers = new ArrayList<BigInteger>();
		for(Element element : elements){
			castBigIntegers.add(new BigInteger(element.getTextContent()));
		}
		return castBigIntegers;
	}

}
