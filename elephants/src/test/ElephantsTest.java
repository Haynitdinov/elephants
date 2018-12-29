package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import elephants.Elephants;

@TestInstance(Lifecycle.PER_CLASS)
class ElephantsTest {

	static String[] inputFiles;
	long[] outputs;
	BufferedReader rd;
	StringTokenizer strTok;

	// create an array of outputs and list of files with input data
	@BeforeAll
	public void prepareTestData() throws IOException {
		File fileInput = new File("input_output/in/");
		inputFiles = fileInput.list();

		File fileOutput = new File("input_output/out/");
		String[] fileListOutput = fileOutput.list();
		outputs = new long[fileListOutput.length];
		for (int i = 0; i < fileListOutput.length; i++) {
			rd = new BufferedReader(new FileReader("input_output/out/" + fileListOutput[i]));
			strTok = new StringTokenizer(rd.readLine());
			outputs[i] = Long.parseLong(strTok.nextToken());
		}

	}

	// check if the loaded data from the file is equal to an actual data
	@ParameterizedTest
	@CsvSource({ "slo0.in", "slo1.in", "slo10a.in", "slo10b.in", "slo1ocen.in", "slo2.in", "slo2ocen.in", "slo3.in",
			"slo3ocen.in", "slo4.in", "slo4ocen.in", "slo5.in", "slo6.in", "slo7.in", "slo8a.in", "slo8b.in",
			"slo9a.in", "slo9b.in" })
	void should_LoadDataFromFileInput(String fileName) throws IOException {

		// given
	    

		String file = "input_output/in/" + fileName;
		InputStream targetStream = new FileInputStream(file);
		rd = new BufferedReader(new FileReader(file));
		strTok = new StringTokenizer(rd.readLine());

		int NfromFile = Integer.parseInt(strTok.nextToken());
		int[] weightsFromFile = new int[NfromFile];
		int[] origFromFile = new int[NfromFile];
		int[] permFromFile = new int[NfromFile];

		strTok = new StringTokenizer(rd.readLine());
		for (int i = 0; i < NfromFile; i++) {
			weightsFromFile[i] = Integer.parseInt(strTok.nextToken());
		}
		strTok = new StringTokenizer(rd.readLine());
		for (int i = 0; i < NfromFile; i++) {
			origFromFile[i] = Integer.parseInt(strTok.nextToken()) - 1;
		}

		strTok = new StringTokenizer(rd.readLine());
		for (int i = 0; i < NfromFile; i++) {
			permFromFile[Integer.parseInt(strTok.nextToken()) - 1] = origFromFile[i];
		}
		rd.close();

		// when
		Elephants el = new Elephants();
		el.dataFromInput(new InputStreamReader(targetStream));

		// then
		assertAll(() -> assertEquals(NfromFile, el.getnElephants()),
				() -> assertArrayEquals(el.getOrigSequence(), origFromFile),
				() -> assertArrayEquals(el.getPermutations(), permFromFile),
				() -> assertArrayEquals(el.getWeights(), weightsFromFile)

		);
	}

	// check if the method calculate correct result for each test case
	@Test
	void should_CountMinCost() throws FileNotFoundException {

		// given
		long[] testResults = new long[inputFiles.length];
		
	
		// when
		for (int i = 0; i < inputFiles.length; i++) {
			String file = "input_output/in/" + inputFiles[i];
			InputStream targetStream = new FileInputStream(file);
			Elephants el = new Elephants();
			el.dataFromInput(new InputStreamReader(targetStream));
			testResults[i] = el.countMinCost();
		}

		// then
		assertArrayEquals(testResults, outputs);

	}

}
