package prog.words.test.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import com.google.common.collect.Lists;

import prog.words.test.dtos.CollectedWords;

/**
 * Task responsible for file reading and word counting
 * 
 * @author MG
 *
 */
public class FileProcessTask implements Callable<Boolean> {

	private static final String WORD_SPLIT_EXPRESSION = "\\W+";

	private Path filePath;
	private CollectedWords context;

	/**
	 * Sets required fields
	 * 
	 * @param fileName name of file which will be processed
	 * @param collectedWords used to holds counted words
	 */
	public FileProcessTask(Path filePath, CollectedWords collectedWords) {
		this.filePath = filePath;
		this.context = collectedWords;
	}

	/**
	 * Process files, by reading all lines and splitting to words, updates
	 * world collection which holds all words
	 */
	@Override
	public Boolean call() throws Exception {
		List<String> allLines = readFile(filePath);
		if (allLines == null || allLines.isEmpty()) {
			return false;
		}
		countWordsInLines(allLines);
		return true;
	}

	/**
	 * Split lines to words and updates word count
	 * 
	 * @param allLines lines to process
	 */
	protected void countWordsInLines(List<String> allLines) {
		for (String line : allLines) {
			for (String word : line.split(WORD_SPLIT_EXPRESSION)) {
				context.addWord(word);
			}
		}
	}

	/**
	 * Reads file content and return file lines
	 * 
	 * @param name file name which will be processed
	 * @return file lines
	 */
	private List<String> readFile(Path filePath) {
		List<String> fileLines = Lists.newArrayList();
		try {
			fileLines = Files.readAllLines(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileLines;
	}

}
