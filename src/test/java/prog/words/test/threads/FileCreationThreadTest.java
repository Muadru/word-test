package prog.words.test.threads;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

import com.google.common.collect.Lists;

import prog.words.test.dtos.CollectedWords;

public class FileCreationThreadTest {
	
	private static final String TEST_WORD = "test";
	private static final String TEST_WORD_2 = "test2";
	
	private FileCreationThread thread = null;
	
	@Test
	public void shouldCountOneWord() {
		// given
		CollectedWords words = new CollectedWords();
		words.addWord(TEST_WORD);
		thread = new FileCreationThread(words, "^[A-Za-z]", null);
		List<String> allLines = Lists.newArrayList();
		allLines.add(TEST_WORD);
		
		// when
		String result = thread.constructFileContents();
		
		// then
		assertEquals(TEST_WORD + " - 1" + System.lineSeparator(), result);
	}
	
	@Test
	public void shouldCountTwoWords() {
		// given
		CollectedWords words = new CollectedWords();
		words.addWord(TEST_WORD);
		words.addWord(TEST_WORD_2);
		thread = new FileCreationThread(words, "^[A-Za-z]", null);
		List<String> allLines = Lists.newArrayList();
		allLines.add(TEST_WORD);
		
		// when
		String result = thread.constructFileContents();
		
		// then
		assertEquals(TEST_WORD + " - 1" + System.lineSeparator() + TEST_WORD_2 + " - 1" + System.lineSeparator(), result);
	}
	
	@Test
	public void shouldCountZero() {
		// given
		CollectedWords words = new CollectedWords();
		thread = new FileCreationThread(words, "^[A-Za-z]", null);
		List<String> allLines = Lists.newArrayList();
		allLines.add(TEST_WORD);
		
		// when
		String result = thread.constructFileContents();
		
		// then
		assertEquals("", result);
	}
}
