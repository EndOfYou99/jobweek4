package hangman.business.repo.game;

import java.time.LocalDate;
import java.util.UUID;

import hangman.business.repo.user.User;

public interface GameService {

	public void startNewGame(UUID id, String difficulty, Game Game, User user);

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

	public boolean contains(UUID id);

	// for achievements
	public boolean Played10Games(Game game);

	public boolean checkForWrongLetterCount(Game game);

	public boolean winned5Games(Game game);

	public boolean winned5HardGames(Game game);

	public boolean winned5MediumGames(Game game);

	public int countWins(User user);

	public int countGames(User user);

	public int countLoses(User user);

	public int getEasyGames(User user);

	public int getMediumGames(User user);

	public int getHardGames(User user);

	public int countMonthlyWins(User user, LocalDate date);

	public int countMonthlyGames(User user, LocalDate date);

	public int countMonthlyLoses(User user, LocalDate date);

	public int getMonthlyEasyGames(User user, LocalDate date);

	public int getMonthlyMediumGames(User user, LocalDate date);

	public int getMonthlyHardGames(User user, LocalDate date);
}
