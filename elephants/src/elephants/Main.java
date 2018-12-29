package elephants;

import java.io.InputStreamReader;

public class Main {
	static long result = 0;
	
	public static void main(String[] args) {
		
			Elephants elephants = new Elephants();
			elephants.dataFromInput(new InputStreamReader(System.in));

			result = elephants.countMinCost();
			System.out.println(result);
	}
}
