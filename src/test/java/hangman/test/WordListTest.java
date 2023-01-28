package hangman.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import hangman.business.repo.WordList;

class WordListTest {

	WordList WordList = new WordList();

	@Test
	void testListNotEmpty() {
		assertNotNull(WordList.getWordList());
	}

	@Test
	void testListLength() {
		assertEquals(WordList.getWordList().length, 39);
	}

}
