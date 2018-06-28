package prog.words.test.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.regex.Pattern;

import org.junit.Test;

public class CollectedWordsTest {
	
	private static final String TEST_WORD = "test";
	private static final String TEST_WORD_2 = "test2";
	private CollectedWords words = null;
	
	@Test
	public void shouldAffFirstOccurance() {
		// given
		words = new CollectedWords();
		
		// when
		words.addWord(TEST_WORD);
		
		// then
		assertEquals(1, words.wordCollection.size());
		assertEquals(Integer.valueOf(1), words.wordCollection.get(TEST_WORD));
	}
	
	@Test
	public void shouldAffSecondOccurance() {
		// given
		words = new CollectedWords();
		
		// when
		words.addWord(TEST_WORD);
		words.addWord(TEST_WORD);
		
		// then
		assertEquals(1, words.wordCollection.size());
		assertEquals(Integer.valueOf(2), words.wordCollection.get(TEST_WORD));
	}
	
	@Test
	public void shouldGet23WordsOccurance() {
		// given
		words = new CollectedWords();
		
		// when
		words.addWord(TEST_WORD);
		words.addWord(TEST_WORD);
		words.addWord(TEST_WORD_2);
		
		// then
		assertEquals(2, words.wordCollection.size());
		assertEquals(Integer.valueOf(2), words.wordCollection.get(TEST_WORD));
		assertEquals(Integer.valueOf(1), words.wordCollection.get(TEST_WORD_2));
	}
	
	@Test
	public void shouldRemoveOccurance() {
		// given
		words = new CollectedWords();
		words.addWord(TEST_WORD);
		words.addWord(TEST_WORD);
		
		// when
		String result = words.removeFirstWord(Pattern.compile("^[A-Za-z]"));
		
		// then
		assertEquals(0, words.wordCollection.size());
		assertEquals(TEST_WORD + " - 2" + System.lineSeparator(), result);
	}
	
	@Test
	public void shouldRemove2Occurance() {
		// given
		words = new CollectedWords();
		words.addWord(TEST_WORD);
		words.addWord(TEST_WORD);
		words.addWord(TEST_WORD_2);
		
		// when
		String result = words.removeFirstWord(Pattern.compile("^[A-Za-z]"));
		String result2 = words.removeFirstWord(Pattern.compile("^[0-1]"));
		
		// then
		assertEquals(1, words.wordCollection.size());
		assertEquals(TEST_WORD + " - 2" + System.lineSeparator(), result);
		assertNull(result2);
	}
	
	@Test
	public void shouldRemoveNonOccurance() {
		// given
		words = new CollectedWords();
		
		// when
		String result = words.removeFirstWord(Pattern.compile("^[A-Za-z]"));
		
		// then
		assertEquals(0, words.wordCollection.size());
		assertNull(result);
	}

}
