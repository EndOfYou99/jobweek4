package hangman.business.repo.statistics;

public class StatisticsDTO {

	private int statId;

	int maxTries;

	int tries;

	int diff;

	String hiddenWord;

	String result;

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

}
