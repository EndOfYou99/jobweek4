package hangman.restcontroller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hangman.business.repo.game.Game;
import hangman.business.repo.game.GameService;
import hangman.business.repo.game.Gamedto;
import hangman.business.repo.game.GamedtoFactory;
import hangman.business.repo.monthlyRanks.MonthlyRanks;
import hangman.business.repo.monthlyRanks.MonthlyRanksService;
import hangman.business.repo.ranks.RankService;
import hangman.business.repo.ranks.Ranks;
import hangman.business.repo.statistics.Statistics;
import hangman.business.repo.statistics.StatisticsFactory;
import hangman.business.repo.statistics.StatsService;
import hangman.business.repo.user.User;
import hangman.business.repo.user.UserService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/games")
public class GameRestController {

	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;

	@Autowired
	RankService rankService;

	@Autowired
	MonthlyRanksService monthlyRankService;

	@Autowired
	StatsService statsService;
	@Autowired
	StatisticsFactory statFactory;

	@Autowired
	GamedtoFactory gameFactory;

	@GetMapping("/ongoing")
	@Operation(summary = "List of all ongoing games")
	public ResponseEntity<CollectionModel<Gamedto>> ongoing() {
		List<Game> list = gameService.ongoing();

		List<Gamedto> dtos = gameFactory.fromEntities(list);

		for (Gamedto dto : dtos) {
			dto.add(linkTo(methodOn(GameRestController.class).getGameById(dto.getId())).withSelfRel());
			dto.add(linkTo(methodOn(GameRestController.class).makeGuess(dto.getId(), "")).withRel("makeTry"));
		}

		Link link = linkTo(methodOn(GameRestController.class).ongoing()).withSelfRel();

		CollectionModel<Gamedto> result = CollectionModel.of(dtos, link);

		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get game by id")
	public ResponseEntity<Gamedto> getGameById(@PathVariable String id) {
		UUID ID = UUID.fromString(id);
		Game myGame = gameService.getGame(ID);
		Gamedto dto = gameFactory.fromEntity(myGame);

		dto.add(linkTo(methodOn(GameRestController.class).getGameById(id)).withSelfRel());

		if (dto.getResult() == "N") {
			dto.add(linkTo(methodOn(GameRestController.class).makeGuess(dto.getId(), "")).withRel("makeTry"));
		}

		return ResponseEntity.ok().body(dto);
	}

	@PostMapping("/{difficulty}")
	@Operation(summary = "Create game")
	public ResponseEntity<Gamedto> createGame(@PathVariable String difficulty) {
		Game Game = new Game();

		User user = userService.getUser("Kaan");

		UUID id = UUID.randomUUID();
		String stringId = id.toString();
		gameService.startNewGame(id, difficulty, Game, user);

		Gamedto dto = gameFactory.fromEntity(Game);

		dto.add(linkTo(methodOn(GameRestController.class).createGame(difficulty)).withSelfRel());
		dto.add(linkTo(methodOn(GameRestController.class).makeGuess(stringId, "")).withRel("makeTry"));
		dto.add(linkTo(methodOn(GameRestController.class).getGameById(stringId)).withRel("getGame"));

		return new ResponseEntity<Gamedto>(dto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Make guess")
	public ResponseEntity<Gamedto> makeGuess(@PathVariable String id, @RequestParam String letter) {

		UUID stringId = UUID.fromString(id);

		Game myGame = gameService.getGame(stringId);

		Gamedto dto = gameFactory.fromEntity(myGame);

		if (myGame.getResult() == "N") {

			String temp = myGame.getHiddenWord();
			String firstLetter = String.valueOf(myGame.getWord().charAt(0));
			String lastLetter = String.valueOf(myGame.getWord().charAt(myGame.getWord().length() - 1));

			gameService.updateUsedList(myGame, firstLetter, lastLetter, letter);
			gameService.makeTry(stringId, letter);
			gameService.checkAttempt(myGame, temp);

			dto = gameFactory.fromEntity(myGame);

			dto.add(linkTo(methodOn(GameRestController.class).makeGuess(id, letter)).withSelfRel());
			dto.add(linkTo(methodOn(GameRestController.class).getGameById(id)).withRel("getGame"));

			return ResponseEntity.ok().body(dto);

		}

		else if (myGame.getResult() == "W" || myGame.getResult() == "L") {

			Ranks rank = rankService.saveRank(myGame);
			MonthlyRanks monthlyRank = monthlyRankService.saveMonthlyRank(myGame);
			Statistics s = myGame.getStatistics();
			s.setRanks(rank);
			s.setMonthlyRanks(monthlyRank);

			statsService.addStats(s);
			userService.addAchievements(myGame);

			dto = gameFactory.fromEntity(myGame);

			dto.add(linkTo(methodOn(GameRestController.class).makeGuess(id, letter)).withSelfRel());
			dto.add(linkTo(methodOn(GameRestController.class).getGameById(id)).withRel("getGame"));

			return new ResponseEntity<Gamedto>(dto, HttpStatus.OK);

		}
		return new ResponseEntity<Gamedto>(dto, HttpStatus.OK);

	}

}
