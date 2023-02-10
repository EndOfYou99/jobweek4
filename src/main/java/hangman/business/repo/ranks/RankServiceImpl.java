package hangman.business.repo.ranks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hangman.business.repo.game.Game;
import hangman.business.repo.game.GameService;

@Service
public class RankServiceImpl implements RankService {

	@Autowired
	GameService gameService;

	@Autowired
	RankRepository rankRepo;

	public Ranks saveRank(Game game) {

		Ranks rank = rankRepo.findByName(game.getUser().getUsername());
		rank.setUser(game.getUser().getUsername());
		rank.setPlayedGames(gameService.countGames(game.getUser()));
		rank.setWins(gameService.countWins(game.getUser()));
		rank.setLoses(gameService.countLoses(game.getUser()));
		rank.setEasyGames((gameService.getEasyGames(game.getUser())));
		rank.setMediumGames((gameService.getMediumGames(game.getUser())));
		rank.setHardGames((gameService.getHardGames(game.getUser())));

		rankRepo.save(rank);

		return rank;
	}

	public void save(Ranks rank) {
		rankRepo.save(rank);
	}

	public Ranks findByName(String name) {
		return rankRepo.findByName(name);
	}

	@Override
	public List<Ranks> bestOfAllTime() {
		return rankRepo.findTop10ByOrderByWinsDesc();
	}

}
