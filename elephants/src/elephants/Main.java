package elephants;

import java.io.File;

public class Main {
	static long result = 0;
	static long checkResult = 0;

	public static void main(String[] args) {

		File fileInput = new File("input_output/in/");
		String[] fileListInput = fileInput.list();

		File fileOutput = new File("input_output/out/");
		String[] fileListOutput = fileOutput.list();
		
		
		for (int i = 0; i < fileListInput.length; i++) {

			Elephants elephants = new Elephants();
			elephants.loadDataFromFileInput("input_output/in/" + fileListInput[i]);

			result = elephants.countMinCost();

			checkResult = Elephants.loadDataFromFileOut("input_output/out/" + fileListOutput[i]);

			if (result == checkResult) {
				System.out.println(result);
			} else {
				System.out.println("Smth is wrong, they are not equal " + fileListOutput[i]);
			}

		}
	}
}
