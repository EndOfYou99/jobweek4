package hangman.controller;

import java.time.LocalDate;
import java.util.List;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.NestedServletException;

import hangman.business.repo.achievements.AchievementsService;
import hangman.business.repo.game.Game;
import hangman.business.repo.game.GameService;
import hangman.business.repo.monthlyRanks.MonthlyRanks;
import hangman.business.repo.monthlyRanks.MonthlyRanksService;
import hangman.business.repo.ranks.RankService;
import hangman.business.repo.ranks.Ranks;
import hangman.business.repo.statistics.Statistics;
import hangman.business.repo.statistics.StatsService;
import hangman.business.repo.user.User;
import hangman.business.repo.user.UserService;
import hangman.business.repo.word.WordService;

@Controller
@RequestMapping("/HangmanGame")
@SessionAttributes({ "user" })
public class GameController {

	@Autowired
	GameService GameServiceImpl;

	@Autowired
	RankService rankService;

	@Autowired
	MonthlyRanksService monthlyRankService;

	@Autowired
	UserService userService;

	@Autowired
	WordService wordService;

	@Autowired
	StatsService statsService;

	@Autowired
	AchievementsService achievementsservice;

	@PostMapping("/registerform")
	public String registerForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registrationForm";
	}

	@GetMapping("/login")
	public String getLogin(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return "login";
	}

	@PostMapping("/register")
	public String register(User user, Model model) {

		if (userService.contains(user.getUsername())) {
			model.addAttribute("error", "Sorry, the your username is already taken, please choose another one");
			return "registrationForm";
		}
		userService.add(user);

		Ranks rank = new Ranks();
		MonthlyRanks monthlyRank = new MonthlyRanks();

		rank.setUser(user.getUsername());
		monthlyRank.setMuser(user.getUsername());

		rankService.save(rank);
		monthlyRankService.save(monthlyRank);

		return "login";

	}

	// Games starting point
	@PostMapping("/loginform")
	public String Startup(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		System.out.println(" result from check" + userService.check(user.getUsername(), user.getPassword()));
		if (result.hasErrors()) {
			return "login";
		}
		if (userService.check(user.getUsername(), user.getPassword())) {

			model.addAttribute("user", user);

			return "mainPage";
		} else {
			model.addAttribute("error", "Sorry you used invalid username or password");
			return "login";
		}

	}

	@GetMapping("/new game")
	public String startNewGame(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("user", user);
		return "index";
	}

	@GetMapping("/exit")
	public String exit() {
		return "login";
	}

	@GetMapping("/index1")
	public String index1(@ModelAttribute("user") User user) {
		return "index";
	}

	@GetMapping("/mainPage")
	public String main(@ModelAttribute("user") User user) {
		return "mainPage";
	}

	@GetMapping("/rankList")
	public String rankList(@ModelAttribute("user") User user, Model model) {

		List<Ranks> best = rankService.bestOfAllTime();
		monthlyRankService.refreshResults();

		List<MonthlyRanks> monthlyRanks = monthlyRankService.bestOfAllTime();
		monthlyRanks = monthlyRankService.bestOfAllTime();

		model.addAttribute("bestList", best);
		model.addAttribute("bestMonthlyList", monthlyRanks);
		return "rankList";
	}

	@GetMapping("/profile")
	public String profile(@ModelAttribute("user") User user, Model model) {

		Ranks rank = rankService.findByName(user.getUsername());
		MonthlyRanks monthlyRank = monthlyRankService.findByName(user.getUsername());

		model.addAttribute("rank", rank);
		model.addAttribute("monthlyRank", monthlyRank);
		return "profile";
	}

	@PostMapping("/index")
	public String index() {
		return "index";
	}

	@ModelAttribute("Game")
	public Game myGame() {
		Game myGame = new Game();
		return myGame;
	}

	// Creating a new Game
	@GetMapping("/Init")
	public String Init(@ModelAttribute("user") User user, @ModelAttribute("Game") Game Game,
			@RequestParam String difficulty, Model model) throws Exception {

		String userName = user.getUsername();
		user = userService.getUser(userName);

		LocalDate date = LocalDate.now();
		LocalDate date1 = date.minusMonths(1);

		UUID id = UUID.randomUUID();

		GameServiceImpl.startNewGame(id, difficulty, Game, user);

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

		if (!GameServiceImpl.contains(stringId)) {
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

		if (myGame.getResult() == "W" || myGame.getResult() == "L") {
			model.addAttribute("id", id);
			model.addAttribute("myGame", myGame);

			Ranks rank = rankService.saveRank(myGame);
			MonthlyRanks monthlyRank = monthlyRankService.saveMonthlyRank(myGame);
			Statistics s = myGame.getStatistics();
			s.setRanks(rank);
			s.setMonthlyRanks(monthlyRank);

			statsService.addStats(s);
			userService.addAchievements(myGame);

			return "redirect:/HangmanGame/resultPage/" + id;
		}

		return "myGame";
	}

	@GetMapping("/resultPage/{uuid}")
	public String showResultPage(@PathVariable String uuid, Model model) {
		Game game = GameServiceImpl.getGame(UUID.fromString(uuid));
		List<Ranks> best = rankService.bestOfAllTime();
		monthlyRankService.refreshResults();

		List<MonthlyRanks> bestMonthly = monthlyRankService.bestOfAllTime();
		userService.addAchievements(game);
		model.addAttribute("game", game);
		model.addAttribute("id", uuid);
		model.addAttribute("bestList", best);
		model.addAttribute("bestMonthlyList", bestMonthly);

		return "resultPage";
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
