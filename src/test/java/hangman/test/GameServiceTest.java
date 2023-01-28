package hangman.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import hangman.business.repo.Game;
import hangman.business.repo.GameRepository;
import hangman.business.repo.GameRepositoryImpl;
import hangman.business.service.GameService;
import hangman.business.service.GameServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class GameServiceTest {

	GameRepository gameRepo;

	private GameService gameService;

	String[] list = new String[] { "Abyss", "Bubbly", "Buzz", "Buff", "Cozy", "Fluff", "Fluffy", "Fizz", "Fizzy", "Fox",
			"Jinx", "Lucky", "Puzzle", "Foxglove", "Abruptly", "Voyeurism", "Pneumonia", "Jiujitsu", "Espionage",
			"Witchcraft", "Razzmatazz", "Zigzagging", "Buckaroo", "Iatrogenic", "Jawbreaker", "Voodoo",
			"Jazz_singer_drops_beats", "Buzzing_around_the_beekeeper", "Awkward_klutzy_numbskull",
			"Croquet_players_fix_games", "Throwing_gnarly_punches", "Absurd_wizard_mystifies", "Jiujitsu_masters_train",
			"Wimpy_geek_panics", "Twelve_foxes_hunt", "A_quiet_jinx_sulks", "Nine_tailed_demon_fox",
			"The_great__wall_of_china", "Sage_of_six_paths" };

	Game Game = new Game(0, 0, 6, 0, 1, "", "", list, "N");;

	@BeforeEach
	public void setUp() {
		gameRepo = Mockito.mock(GameRepository.class);
		gameService = new GameServiceImpl(gameRepo);
		gameService = Mockito.spy(gameService);
		String[] list = new String[] { "Abyss", "Bubbly", "Buzz", "Buff", "Cozy", "Fluff", "Fluffy", "Fizz", "Fizzy",
				"Fox", "Jinx", "Lucky", "Puzzle", "Foxglove", "Abruptly", "Voyeurism", "Pneumonia", "Jiujitsu",
				"Espionage", "Witchcraft", "Razzmatazz", "Zigzagging", "Buckaroo", "Iatrogenic", "Jawbreaker", "Voodoo",
				"Jazz_singer_drops_beats", "Buzzing_around_the_beekeeper", "Awkward_klutzy_numbskull",
				"Croquet_players_fix_games", "Throwing_gnarly_punches", "Absurd_wizard_mystifies",
				"Jiujitsu_masters_train", "Wimpy_geek_panics", "Twelve_foxes_hunt", "A_quiet_jinx_sulks",
				"Nine_tailed_demon_fox", "The_great__wall_of_china", "Sage_of_six_paths" };
		Game = new Game(0, 0, 6, 0, 1, "", "", list, "N");

	}

	@Test
	void testChooseWord() {
		gameService.chooseWord(Game);
	}

	@Test
	public void testGetGame() {
		UUID id = UUID.fromString("668c1752-ae7a-4675-b18f-e6204d359078");

		Mockito.when(gameService.getGame(id)).thenReturn(Game);

		Game check = gameService.getGame(id);

		verify(gameRepo).getGameById(id);
		assertEquals(check, Game);
	}

	@Test
	public void testcheckEmpty() {
		Mockito.when(gameService.checkEmpty()).thenReturn(true);
		boolean check = gameService.checkEmpty();

		verify(gameRepo).isEmpty();
		assertEquals(check, true);
	}

	@Test

	public void testCheckAttemptFalse() {
		// given

		Game.setHiddenWord("h***o");
		Game.setTries(0);
		Game.setMaxTries(6);
		Game.setResult("N");
		String temp = "h***o";
		// when
		gameService.checkAttempt(Game, temp);

		// then
		assertEquals(Game.getHiddenWord(), temp);
		assertEquals(Game.getResult(), "N");
		assertEquals(Game.getTries(), 1);
	}

	@Test
	public void testCheckAttemptTrue() {
		// given

		Game.setHiddenWord("h*llo");
		Game.setTries(0);
		Game.setMaxTries(6);
		Game.setResult("N");
		String temp = "h***o";
		// when
		gameService.checkAttempt(Game, temp);

		// then
		assertNotEquals(Game.getHiddenWord(), temp);
		assertEquals(Game.getResult(), "N");
		assertEquals(Game.getTries(), 0);
	}

	@Test
	public void testCheckForLose() {
		// given

		Game.setHiddenWord("h***o");
		Game.setTries(6);
		Game.setMaxTries(6);
		Game.setResult("N");
		String temp = "h***o";
		// when
		gameService.checkAttempt(Game, temp);

		// then
		assertEquals(Game.getHiddenWord(), temp);
		assertEquals(Game.getResult(), "L");
		assertEquals(Game.getTries(), 7);
	}

	@Test
	public void testCheckForWin() {
		// given

		Game.setWord("hello");
		Game.setHiddenWord("hello");
		Game.setTries(6);
		Game.setMaxTries(6);
		Game.setResult("N");
		String temp = "h*llo";
		// when
		gameService.checkAttempt(Game, temp);

		// then
		assertEquals(Game.getHiddenWord(), Game.getWord());
		assertEquals(Game.getResult(), "W");
		assertEquals(Game.getTries(), 6);
	}

	@ParameterizedTest
	@CsvSource({ "0,Easy", "12,Medium", "25,Hard" })
	public void testSetDifficulty(int result, String difficulty) {

		gameService.setDifficulty(Game, difficulty);
	}

	@Test
	public void testHide() {

		Game.setWord("hello");
		gameService.hide(Game);
		String hidden = Game.getHiddenWord();
		assertNotNull(hidden);
		assertEquals(Game.getWord().length(), Game.getHiddenWord().length());
	}

	@ParameterizedTest
	@CsvSource({ "hello,h***o,h,o", "hello_i_am_me,h****_*_****e,h,e" })
	public void testCheck(String word, String hiddenWord, String f, String l) {
		gameService.Check(word, hiddenWord, f, l);
	}

	@Test
	public void testStartNewGame() {
		UUID id = UUID.fromString("668c1752-ae7a-4675-b18f-e6204d359078");
		String difficulty = "Easy";
		gameService.startNewGame(id, difficulty, Game);

		verify(gameService).startNewGame(id, difficulty, Game);

		verify(gameService).setDifficulty(Game, difficulty);

		verify(gameService).chooseWord(Game);
	}

	@Test
	public void testmakeTry() {
		HashMap<UUID, Game> myRepomap = new HashMap<UUID, Game>();
		UUID id = UUID.fromString("668c1752-ae7a-4675-b18f-e6204d359078");
		String difficulty = "Easy";
		GameRepository myRepo = new GameRepositoryImpl(myRepomap);
		GameService myGameService = new GameServiceImpl(myRepo);
		myGameService.startNewGame(id, difficulty, Game);

		Game.setHiddenWord("scary");
		myRepo.contains(id);
		System.out.println("This is the Game:" + gameService.getGame(id));
		myGameService.makeTry(id, "a");
	}

	@ParameterizedTest
	@CsvSource({ "hello,h,o,o", "welcome,w,e,d" })
	public void testUpdateUsedList(String word, String first, String last, String letter) {
		Game.setWord(word);
		gameService.updateUsedList(Game, first, last, letter);
	}

	@Test
	public void testUpdateUsedListEmpty() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("There are no letters currently tried.");
		Game.setUsedLetters(list);
		Game.setWord("coconutc");
		gameService.updateUsedList(Game, "c", "c", "o");
	}

}
