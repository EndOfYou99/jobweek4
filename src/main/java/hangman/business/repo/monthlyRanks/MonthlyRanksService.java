package hangman.business.repo.monthlyRanks;

import java.util.List;

import hangman.business.repo.game.Game;

public interface MonthlyRanksService {

	public MonthlyRanks saveMonthlyRank(Game game);

	public void save(MonthlyRanks rank);

	List<MonthlyRanks> bestOfAllTime();

	public void refreshResults();

	public MonthlyRanks findByName(String name);

}
