package hangman.business.repo.game;

import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hangman.business.repo.statistics.Statistics;
import hangman.business.repo.user.User;
import hangman.business.repo.word.Word;

@Entity
public class Game {
	@Id
	String id;

	@JsonIgnore
	@ManyToOne
	Word words;

	@JsonIgnore
	@ManyToOne
	User user;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	Statistics statistics;

	public Game() {

		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getMaxTries() {
		return statistics.getMaxTries();
	}

	public void setMaxTries(int maxTries) {
		statistics.setMaxTries(maxTries);
	}

	public int getTries() {
		return statistics.getTries();
	}

	public void setTries(int tries) {
		statistics.setTries(tries);
	}

	public int getDiff() {
		return statistics.getDiff();
	}

	public void setDiff(int diff) {
		statistics.setDiff(diff);
	}

	public String getWord() {
		return words.getWord();
	}

	public String getHiddenWord() {
		return statistics.getHiddenWord();
	}

	public void setHiddenWord(String hiddenWord) {
		statistics.setHiddenWord(hiddenWord);
	}

	public ArrayList getUsedLetters() {
		return statistics.getUsedLetters();
	}

	public void setUsedLetters(ArrayList usedLetters) {
		statistics.setUsedLetters(usedLetters);
	}

	public String getResult() {
		return statistics.getResult();
	}

	public void setResult(String result) {
		statistics.setResult(result);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Word getWords() {
		return words;
	}

	public void setWords(Word words) {
		this.words = words;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", words=" + words + ", user=" + user + ", statistics=" + statistics + "]";
	}

}
