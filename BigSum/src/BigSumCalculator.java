import java.math.BigInteger;
import java.util.ArrayList;

public class BigSumCalculator {
	
	private ArrayList<BigInteger> bigNumCollection;

	public BigSumCalculator(ArrayList<BigInteger> bigNumCollection ){
		this.bigNumCollection = bigNumCollection;
	}

	public BigInteger sumAll() {
		BigInteger sum = BigInteger.ZERO;
		for(BigInteger num : bigNumCollection){
			sum = sum.add(num);
		}
		return sum;
	}

	public String getFirstNumbersOfSum(int quantity) {
		BigInteger sum = sumAll();
		return sum.toString().substring(0, quantity);
	}

}
