package hangman.business.repo.monthlyRanks;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hangman.business.repo.game.Game;
import hangman.business.repo.game.GameService;
import hangman.business.repo.user.User;
import hangman.business.repo.user.UserRepository;

@Service
public class MonthlyRanksServiceImpl implements MonthlyRanksService {
	@Autowired
	MonthlyRanksRepository rankRepo;

	@Autowired
	GameService gameService;

	@Autowired
	UserRepository userRepo;

	public void refreshResults() {
		List<MonthlyRanks> ranks = rankRepo.findAll();
		LocalDate date = LocalDate.now();
		LocalDate date1 = date.minusMonths(1);

		for (var rank : ranks) {
			User user = userRepo.getUser(rank.getMuser());

			rank.setMplayedGames(gameService.countMonthlyGames(user, date1));
			rank.setMwins(gameService.countMonthlyWins(user, date1));
			rank.setMloses(gameService.countMonthlyLoses(user, date1));
			rank.setMeasyGames((gameService.getMonthlyEasyGames(user, date1)));
			rank.setMmediumGames((gameService.getMonthlyMediumGames(user, date1)));
			rank.setMhardGames((gameService.getMonthlyHardGames(user, date1)));
			rankRepo.save(rank);

		}
	}

	public MonthlyRanks saveMonthlyRank(Game game) {
		LocalDate date = LocalDate.now();
		LocalDate date1 = date.minusMonths(1);

		MonthlyRanks rank = rankRepo.findByName(game.getUser().getUsername());
		rank.setMuser(game.getUser().getUsername());
		rank.setMplayedGames(gameService.countMonthlyGames(game.getUser(), date1));
		rank.setMwins(gameService.countMonthlyWins(game.getUser(), date1));
		rank.setMloses(gameService.countMonthlyLoses(game.getUser(), date1));
		rank.setMeasyGames((gameService.getMonthlyEasyGames(game.getUser(), date1)));
		rank.setMmediumGames((gameService.getMonthlyMediumGames(game.getUser(), date1)));
		rank.setMhardGames((gameService.getMonthlyHardGames(game.getUser(), date1)));

		rankRepo.save(rank);

		return rank;
	}

	public void save(MonthlyRanks rank) {
		rankRepo.save(rank);
	}

	@Override
	public List<MonthlyRanks> bestOfAllTime() {
		return rankRepo.findTop10ByOrderByMwinsDesc();
	}

	public MonthlyRanks findByName(String name) {
		return rankRepo.findByName(name);
	}

}
