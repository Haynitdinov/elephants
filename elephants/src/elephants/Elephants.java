package elephants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Elephants {

	public static final int MAX_WEIGHT = 6500; // maximum possible weight of the elephant

	private int nElephants; // quantity of elephants in the set
	private int[] weights; // weights of elephants
	private int[] origSequence; // start positions of elephants
	private int[] permutations; // target position of elephants
	static boolean[] checked; // boolean to indicate if elephant is on good position (default false)

	// load data from the file and assign it to class variables
	public void loadDataFromFileInput(String fileName) {

		try {
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			StringTokenizer fromFile = new StringTokenizer(rd.readLine());

			this.nElephants = Integer.parseInt(fromFile.nextToken());
			this.weights = new int[this.nElephants];
			this.origSequence = new int[this.nElephants];
			this.permutations = new int[this.nElephants];

			fromFile = new StringTokenizer(rd.readLine());
			for (int i = 0; i < this.nElephants; i++) {
				this.weights[i] = Integer.parseInt(fromFile.nextToken());
			}
			fromFile = new StringTokenizer(rd.readLine());
			for (int i = 0; i < this.nElephants; i++) {
				this.origSequence[i] = Integer.parseInt(fromFile.nextToken()) - 1;
			}

			fromFile = new StringTokenizer(rd.readLine());
			for (int i = 0; i < this.nElephants; i++) {
				this.permutations[Integer.parseInt(fromFile.nextToken()) - 1] = this.origSequence[i];
			}

			rd.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}

	// method returns the output loaded from the file
	public static long loadDataFromFileOut(String fileName) {

		long result = 0;

		try {
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			StringTokenizer fromFile = new StringTokenizer(rd.readLine());

			result = Long.parseLong(fromFile.nextToken());

			rd.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();

		}
		return result;
	}

	// method calculates minimum cost of changing elephant's start positions to
	// target position
	public long countMinCost() {
		checked = new boolean[this.nElephants];
		int minTotalWeight = MAX_WEIGHT;

		long result = 0;

		for (int i = 0; i < this.nElephants; ++i) {
			minTotalWeight = Math.min(minTotalWeight, this.weights[i]);
		}

		for (int i = 0; i < this.nElephants; ++i) {
			if (!checked[i]) {
				int minCycleWeight = MAX_WEIGHT;
				long cycleSum = 0;
				int index = i;
				int cycleLength = 0;
				for (;;) {
					minCycleWeight = Math.min(minCycleWeight, this.weights[index]);
					cycleSum += this.weights[index];

					index = this.permutations[index];
					checked[index] = true;
					++cycleLength;
					if (index == i) {
						break;
					}

				}
				result += Math.min(cycleSum + (cycleLength - 2) * (long) minCycleWeight,
						cycleSum + minCycleWeight + (cycleLength + 1) * (long) minTotalWeight);
			}
		}

		return result;
	}

	public int getnElephants() {
		return nElephants;
	}

	public void setnElephants(int nElephants) {
		this.nElephants = nElephants;
	}

	public int[] getWeights() {
		return weights;
	}

	public void setWeights(int[] weights) {
		this.weights = weights;
	}

	public int[] getOrigSequence() {
		return origSequence;
	}

	public void setOrigSequence(int[] origSequence) {
		this.origSequence = origSequence;
	}

	public int[] getPermutations() {
		return permutations;
	}

	public void setPermutations(int[] permutations) {
		this.permutations = permutations;
	}

}
