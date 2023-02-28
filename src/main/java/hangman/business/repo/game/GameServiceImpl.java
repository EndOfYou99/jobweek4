package hangman.business.repo.game;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hangman.business.repo.ranks.Ranks;
import hangman.business.repo.statistics.Statistics;
import hangman.business.repo.statistics.StatsService;
import hangman.business.repo.user.User;
import hangman.business.repo.word.WordServiceImpl;

@Component("GameServiceImpl")
public class GameServiceImpl implements GameService {

	Random rand = new Random();

	@Autowired
	GameRepository gameRepository;

	@Autowired
	WordServiceImpl wordService;

	@Autowired
	StatsService statsService;

	public GameServiceImpl() {

	}

	public GameServiceImpl(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public void startNewGame(UUID id, String difficulty, Game Game, User user) {

		Statistics statistics = new Statistics();
		Ranks rank = new Ranks();
		statsService.addStats(statistics);
		Game.setStatistics(statistics);

		setDifficulty(Game, difficulty);

		chooseWord(Game);

		Game.setUser(user);

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
			gameRepository.update(myBean);

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

		gameRepository.update(myBean);
	}

	public void setDifficulty(Game Game, String difficulty) {
		if (difficulty.equals("Easy")) {
			Game.setDiff(1);
		}
		if (difficulty.equals("Medium")) {
			Game.setDiff(14);
		}
		if (difficulty.equals("Hard")) {

			Game.setDiff(27);
		}
	}

	public void chooseWord(Game Game) {
		Game.setWords(wordService.setWord(getRandomWord() + Game.getDiff()));
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
		String id = Game.getId();
		UUID stringId = UUID.fromString(id);
		gameRepository.update(Game);
	}

	@Override
	public String toString() {
		return "GameServiceImpl [rand=" + rand + ", GameRepositoryImpl=" + gameRepository + "]";
	}

	@Override
	public boolean contains(UUID id) {
		return gameRepository.contains(id);
	}

	@Override
	public boolean Played10Games(Game game) {
		Long games = gameRepository.PlayedGames(game);
		if (games >= 10) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkForWrongLetterCount(Game game) {
		int result = gameRepository.checkForWrongLetterCount(game);
		if (result > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean winned5Games(Game game) {
		Long result = gameRepository.winnedGames(game);
		if (result < 5) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean winned5HardGames(Game game) {
		Long result = gameRepository.winnedHardGames(game);
		if (result < 5) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean winned5MediumGames(Game game) {
		Long result = gameRepository.winnedMediumGames(game);
		if (result < 5) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int countGames(User user) {
		return Math.toIntExact(gameRepository.finishedGames(user));
	}

	@Override
	public int countLoses(User user) {
		return Math.toIntExact(gameRepository.lostGames(user));
	}

	@Override
	public int getEasyGames(User user) {
		return Math.toIntExact(gameRepository.getEasyGames(user));
	}

	@Override
	public int getMediumGames(User user) {
		return Math.toIntExact(gameRepository.getMediumGames(user));
	}

	@Override
	public int getHardGames(User user) {
		return Math.toIntExact(gameRepository.getHardGames(user));
	}

	@Override
	public int countWins(User user) {
		return Math.toIntExact(gameRepository.winnedGames(user));
	}

	@Override
	public int countMonthlyWins(User user, LocalDate date) {
		return Math.toIntExact(gameRepository.monthlyWinnedGames(user, date));
	}

	@Override
	public int countMonthlyGames(User user, LocalDate date) {
		return Math.toIntExact(gameRepository.monthlyFinishedGames(user, date));
	}

	@Override
	public int countMonthlyLoses(User user, LocalDate date) {
		return Math.toIntExact(gameRepository.monthlyLostGames(user, date));
	}

	@Override
	public int getMonthlyEasyGames(User user, LocalDate date) {
		return Math.toIntExact(gameRepository.getMonthlyEasyGames(user, date));
	}

	@Override
	public int getMonthlyMediumGames(User user, LocalDate date) {
		return Math.toIntExact(gameRepository.getMonthlyMediumGames(user, date));
	}

	@Override
	public int getMonthlyHardGames(User user, LocalDate date) {
		return Math.toIntExact(gameRepository.getMonthlyHardGames(user, date));
	}

	@Override
	public List<Game> ongoing() {
		return gameRepository.ongoing();
	}

}
