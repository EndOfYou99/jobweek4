package hangman.business.repo.statistics;

import java.util.List;

public interface StatsService {

	public void addStats(Statistics statistics);

	public List<Statistics> getAllStatistics();

}
