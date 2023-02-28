package hangman.business.repo.game;

import java.util.ArrayList;

import org.springframework.hateoas.RepresentationModel;

public class Gamedto extends RepresentationModel<Gamedto> {

	private String id;

	private String word;

	private String user;

	private int maxTries;

	private int tries;

	private int diff;

	private String hiddenWord;

	private String result;

	ArrayList usedLetters;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getMaxTries() {
		return maxTries;
	}

	public void setMaxTries(int maxTries) {
		this.maxTries = maxTries;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public String getHiddenWord() {
		return hiddenWord;
	}

	public void setHiddenWord(String hiddenWord) {
		this.hiddenWord = hiddenWord;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ArrayList getUsedLetters() {
		return usedLetters;
	}

	public void setUsedLetters(ArrayList usedLetters) {
		this.usedLetters = usedLetters;
	}

	@Override
	public String toString() {
		return "Gamedto [id=" + id + ", word=" + word + ", user=" + user + ", maxTries=" + maxTries + ", tries=" + tries
				+ ", diff=" + diff + ", hiddenWord=" + hiddenWord + ", result=" + result + ", usedLetters="
				+ usedLetters + "]";
	}

}
