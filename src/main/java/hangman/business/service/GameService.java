package hangman.business.service;

import java.util.UUID;

import hangman.business.repo.Game;

public interface GameService {

	public void startNewGame(UUID id, String difficulty, Game Game);

	public Game getGame(UUID id);

	public void makeTry(UUID id, String letter);

	public boolean checkEmpty();

	public void chooseWord(Game Game);

	public void checkAttempt(Game Game, String temp);

	public void setDifficulty(Game Game, String difficulty);

	public void hide(Game Game);

	public String Check(String word, String hiddenWord, String first, String last);

	public void updateUsedList(Game Game, String firstLetter, String lastLetter, String letter);

	public int getRandomWord();
}
