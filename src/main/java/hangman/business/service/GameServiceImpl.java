package hangman.business.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hangman.business.repo.Game;
import hangman.business.repo.GameRepository;

@Component("GameServiceImpl")
public class GameServiceImpl implements GameService {

	Random rand = new Random();

	@Autowired
	GameRepository gameRepository;

	public GameServiceImpl() {

	}

	public GameServiceImpl(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public void startNewGame(UUID id, String difficulty, Game Game) {

		setDifficulty(Game, difficulty);

		chooseWord(Game);

		gameRepository.add(id, Game);
	}

	public boolean checkEmpty() {
		boolean result = gameRepository.isEmpty();
		return result;
	}

	public Game getGame(UUID id) {
		Game Game = null;
		Game = gameRepository.getGameById(id);
		return Game;
	}

	public void makeTry(UUID id, String letter) {
		Game myBean = getGame(id);
		if (letter.equals("")) {
			gameRepository.update(id, myBean);

		}

		char[] res = myBean.getHiddenWord().toLowerCase().toCharArray();
		char c = letter.charAt(0);
		for (int i = 0, l = myBean.getWord().length(); i < l; i++) {
			if (myBean.getWord().toLowerCase().charAt(i) == c) {
				res[i] = c;
			}
		}
		res[0] = Character.toUpperCase(res[0]);
		myBean.setHiddenWord(String.valueOf(res));

		gameRepository.update(id, myBean);
	}

	public void setDifficulty(Game Game, String difficulty) {
		if (difficulty.equals("Easy")) {
			Game.setDiff(0);
		}
		if (difficulty.equals("Medium")) {
			Game.setDiff(13);
		}
		if (difficulty.equals("Hard")) {

			Game.setDiff(25);
		}
	}

	public void chooseWord(Game Game) {
		Game.setWord(Game.getWordList()[getRandomWord() + Game.getDiff()]);
		String firstLetter = Character.toString(Game.getWord().charAt(0)).toLowerCase();
		String lastLetter = Character.toString(Game.getWord().charAt(Game.getWord().length() - 1));
		hide(Game);
		Game.setHiddenWord(Check(Game.getWord(), Game.getHiddenWord(), firstLetter, lastLetter));
	}

	public int getRandomWord() {
		return rand.nextInt(12);
	}

	public String Check(String word, String hiddenWord, String first, String last) {

		char[] res = hiddenWord.toLowerCase().toCharArray();
		char space = '_';
		for (int i = 0, len = word.length(); i < len; i++) {
			if (word.toLowerCase().charAt(i) == space) {
				res[i] = space;
			}
		}

		char l = last.charAt(0);
		for (int i = 0, len = word.length(); i < len; i++) {
			if (word.toLowerCase().charAt(i) == l) {
				res[i] = l;
			}
		}
		char f = first.charAt(0);
		for (int i = 0, len = word.length(); i < len; i++) {
			if (word.toLowerCase().charAt(i) == f) {
				res[i] = f;
			}
		}

		res[0] = Character.toUpperCase(res[0]);
		return new String(res);
	}

	public void hide(Game Game) {
		String hiddenWord = "";
		for (int i = 0; i < Game.getWord().length(); i++) {
			hiddenWord += "*";
		}
		Game.setHiddenWord(hiddenWord);
	}

	public void updateUsedList(Game Game, String firstLetter, String lastLetter, String letter) {
		if (Game.getUsedLetters().contains("There are no letters currently tried.") && firstLetter.equals(lastLetter)) {
			Game.getUsedLetters().add(firstLetter.toLowerCase());
			Game.getUsedLetters().remove(0);
		}

		if (Game.getUsedLetters().contains("There are no letters currently tried.") && firstLetter != lastLetter) {
			Game.getUsedLetters().add(firstLetter.toLowerCase());
			Game.getUsedLetters().add(lastLetter);
			Game.getUsedLetters().remove(0);
		}
		if (Game.getUsedLetters().contains(letter)) {
			Game.setTries(Game.getTries() - 1);
		} else {
			Game.getUsedLetters().add(letter);
		}
	}

	public void checkAttempt(Game Game, String temp) {
		if (temp.equals(Game.getHiddenWord())) {
			Game.setTries(Game.getTries() + 1);
		}

		if (Game.getTries() == Game.getMaxTries() + 1) {
			Game.setResult("L");
		}
		if (Game.getHiddenWord().equals(Game.getWord())) {
			Game.setResult("W");
		}
	}

	@Override
	public String toString() {
		return "GameServiceImpl [rand=" + rand + ", GameRepositoryImpl=" + gameRepository + "]";
	}
}
