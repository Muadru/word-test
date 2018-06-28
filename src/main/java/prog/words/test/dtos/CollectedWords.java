package prog.words.test.dtos;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;

/**
 * Holds collection of words and acts as intermediate to outside
 * 
 * @author MG
 *
 */
public class CollectedWords {

	/**
	 * Word and occurrence count map
	 */
	protected Map<String, Integer> wordCollection = Maps.newTreeMap();

	/**
	 * Puts new word to collection and update count
	 * 
	 * @param word Word to count
	 */
	public synchronized void addWord(String word) {
		if (wordCollection.containsKey(word)) {
			wordCollection.put(word, wordCollection.get(word) + 1);
		} else {
			wordCollection.put(word, 1);
		}
	}
	
	/**
	 * Retrieves first word from collection according to pattern
	 * 
	 * @param wordMatchingPattern p
	 * @return String containing "(Word) - (Count)"
	 */
	public synchronized String removeFirstWord(Pattern wordMatchingPattern) {
		for (String key : wordCollection.keySet()) {
			Matcher m = wordMatchingPattern.matcher(key);
			if (m.find()) {
				StringBuilder builder = new StringBuilder();
				builder.append(key);
				builder.append(" - ");
				builder.append(wordCollection.get(key));
				builder.append(System.lineSeparator());
				wordCollection.remove(key);
				return builder.toString();
			}
		}
		return null;
	}
}
