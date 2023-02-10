package hangman.business.repo.statistics;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import hangman.business.repo.game.Game;
import hangman.business.repo.monthlyRanks.MonthlyRanks;
import hangman.business.repo.ranks.Ranks;

@Entity
public class Statistics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statId;
	@Column(nullable = true)
	int maxTries;

	@Column(nullable = true)
	int tries;

	@Column(nullable = true)
	int diff;

	@Column(nullable = true)
	String hiddenWord;

	@Column(nullable = true)
	String result;

	@Column(nullable = true)
	ArrayList usedLetters;

	LocalDate date;

	@OneToOne(mappedBy = "statistics")
	Game game;

	@ManyToOne
	Ranks ranks;

	@ManyToOne
	MonthlyRanks monthlyRanks;

	public Statistics() {
		this.maxTries = 6;
		this.tries = 0;
		this.result = "N";
		this.usedLetters = new ArrayList();
		usedLetters.add("There are no letters currently tried.");
		this.date = LocalDate.now();
	}

	public int getStatId() {
		return statId;
	}

	public void setStatId(int statId) {
		this.statId = statId;
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Ranks getRanks() {
		return ranks;
	}

	public void setRanks(Ranks ranks) {
		this.ranks = ranks;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public MonthlyRanks getMonthlyRanks() {
		return monthlyRanks;
	}

	public void setMonthlyRanks(MonthlyRanks monthlyRanks) {
		this.monthlyRanks = monthlyRanks;
	}

	@Override
	public String toString() {
		return "Statistics [statId=" + statId + ", maxTries=" + maxTries + ", tries=" + tries + ", diff=" + diff
				+ ", hiddenWord=" + hiddenWord + ", result=" + result + ", usedLetters=" + usedLetters + "]";
	}

}
