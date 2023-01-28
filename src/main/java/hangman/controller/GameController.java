package hangman.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.NestedServletException;

import hangman.business.repo.Game;
import hangman.business.repo.GameRepositoryImpl;
import hangman.business.repo.User;
import hangman.business.repo.WordList;
import hangman.business.service.GameServiceImpl;

@Controller
@RequestMapping("/HangmanGame")
public class GameController {

	@Autowired
	GameRepositoryImpl gameRepo;

	@Autowired
	GameServiceImpl GameServiceImpl;

	@GetMapping("/login")
	public String getLogin(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	// Games starting point
	@PostMapping("/loginform")
	public String Startup(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "login";
		}
		if (!user.getUsername().equals("Kaan") || !user.getPassword().equals("123456")) {
			model.addAttribute("error", "Sorry you used invalid username or password");
			return "login";
		} else {
			return "index";
		}

	}

	@PostMapping("/index")
	public String index() {
		return "index";
	}

	@ModelAttribute("Game")
	public Game myGame() {
		WordList WordList = new WordList();
		String[] list = WordList.getWordList();
		Game myGame = new Game(0, 0, 6, 0, 1, "", "", list, "N");
		return myGame;
	}

	// Creating a new Game
	@GetMapping("/Init")
	public String Init(@ModelAttribute("Game") Game Game, @RequestParam String difficulty, Model model)
			throws Exception {

		UUID id = UUID.randomUUID();

		GameServiceImpl.startNewGame(id, difficulty, Game);

		model.addAttribute("myGame", GameServiceImpl.getGame(id));
		model.addAttribute("id", id);
		model.addAttribute("GameServiceImpl", GameServiceImpl);

		return "redirect:game/" + id;
	}

	// passing the new Game to the jsp page
	@GetMapping("/game/{id}")
	public String getGameById(@PathVariable String id, Model model) {

		Game Game = GameServiceImpl.getGame(UUID.fromString(id));

		model.addAttribute("myGame", Game);
		model.addAttribute("id", id);
		model.addAttribute("GameServiceImpl", GameServiceImpl);

		return "myGame";
	}

	// Guessing letters
	@GetMapping("/Game/{id}")
	public String Guess(@PathVariable String id, @RequestParam String letter, Model model)
			throws NestedServletException {
		UUID stringId = UUID.fromString(id);

		if (!gameRepo.contains(stringId)) {
			throw new IllegalArgumentException("Sorry, but the Game you are looking for is not found.");
		}

		Game myGame = GameServiceImpl.getGame(stringId);
		String temp = myGame.getHiddenWord();
		String firstLetter = String.valueOf(myGame.getWord().charAt(0));
		String lastLetter = String.valueOf(myGame.getWord().charAt(myGame.getWord().length() - 1));

		GameServiceImpl.updateUsedList(myGame, firstLetter, lastLetter, letter);
		GameServiceImpl.makeTry(stringId, letter);
		GameServiceImpl.checkAttempt(myGame, temp);

		model.addAttribute("id", id);

		model.addAttribute("myGame", myGame);

		return "myGame";
	}

	// Passing the Game to the jsp page after guess is done
	@GetMapping("gameCheck/{id}")
	public String gameCheck(@RequestParam String id, Model model) {

		UUID stringID = UUID.fromString(id);
		Game myGame = GameServiceImpl.getGame(stringID);

		model.addAttribute("myGame", myGame);

		return "myGame";
	}
}
