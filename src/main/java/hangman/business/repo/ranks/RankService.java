package hangman.business.repo.ranks;

import java.util.List;

import hangman.business.repo.game.Game;

public interface RankService {

	public void save(Ranks rank);

	public Ranks saveRank(Game game);

	public Ranks findByName(String name);

	List<Ranks> bestOfAllTime();

}
