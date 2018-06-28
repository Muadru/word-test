package prog.words.test.threads;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.collect.Lists;

import prog.words.test.dtos.CollectedWords;
import prog.words.test.threads.FileProcessTask;

public class FileProcessTaskTest {
	
	private static final String TEST_WORD = "test";

	@Test
	public void shouldCountOneOccuranceIn1Line() {
		// given
		CollectedWords words = new CollectedWords();
		FileProcessTask task = new FileProcessTask(null, words);
		List<String> allLines = Lists.newArrayList();
		allLines.add(TEST_WORD);
		
		// when
		task.countWordsInLines(allLines);
		
		// then
		assertEquals(TEST_WORD + " - 1" + System.lineSeparator(), words.removeFirstWord(Pattern.compile("^[A-Za-z]")));
	}
	
	@Test
	public void shouldCountOneOccuranceIn2Lines() {
		// given
		CollectedWords words = new CollectedWords();
		FileProcessTask task = new FileProcessTask(null, words);
		List<String> allLines = Lists.newArrayList();
		allLines.add(TEST_WORD);
		allLines.add(TEST_WORD);
		
		// when
		task.countWordsInLines(allLines);
		
		// then
		assertEquals(TEST_WORD + " - 2" + System.lineSeparator(), words.removeFirstWord(Pattern.compile("^[A-Za-z]")));
	}
	
	@Test
	public void shouldCountZeroOccurance() {
		// given
		CollectedWords words = new CollectedWords();
		FileProcessTask task = new FileProcessTask(null, words);
		List<String> allLines = Lists.newArrayList();
		
		// when
		task.countWordsInLines(allLines);
		
		// then
		assertEquals(null, words.removeFirstWord(Pattern.compile("^[A-Za-z]")));
	}
}
