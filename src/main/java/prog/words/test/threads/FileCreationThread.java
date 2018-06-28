package prog.words.test.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

import prog.words.test.dtos.CollectedWords;

/**
 * Thread responsible for creating result file with counted words
 * 
 * @author MG
 *
 */
public class FileCreationThread extends Thread {

	private String filePath;
	private Pattern wordMatchingPattern;
	private CollectedWords collectedWords;

	/**
	 * Sets required attributes
	 * 
	 * @param collectedWords holds all word list
	 * @param wordComparisonPatern pattern to get words to file
	 * @param resultFileLocation directory of output file
	 */
	public FileCreationThread(CollectedWords collectedWords, String wordComparisonPatern, String resultFileLocation) {
		this.collectedWords = collectedWords;
		this.wordMatchingPattern = Pattern.compile(wordComparisonPatern);
		this.filePath = resultFileLocation + "/result" + wordComparisonPatern + ".txt";
	}

	/**
	 * Writes counted words to result file based on pattern
	 */
	public void run() {
		createResultFile(constructFileContents());
	}

	/**
	 * Construct result file text, which contains counted words
	 * 
	 * @return result file text
	 */
	protected String constructFileContents() {
		String resultWordString = collectedWords.removeFirstWord(wordMatchingPattern);
		StringBuilder builder = new StringBuilder("");
		while (resultWordString != null) {
			builder.append(resultWordString);
			resultWordString = collectedWords.removeFirstWord(wordMatchingPattern);
		}
		return builder.toString();
	}

	/**
	 * Creates result file
	 * 
	 * @param text result file text
	 */
	private void createResultFile(String text) {
		try {
			if (!text.isEmpty()) {
				Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.CREATE);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
