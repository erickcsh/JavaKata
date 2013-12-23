
public class Application {

	public static void main(String[] args) {
		BigSumController controller = new BigSumController(args[0]);
		controller.calculateAndWriteFirst10DigitsOfSum();
	}

}
