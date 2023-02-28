package hangman.business.repo.statistics;

import java.util.List;

public interface StatisticsFactory {

	public StatisticsDTO fromEntity(Statistics stats);

	public List<StatisticsDTO> fromEntities(List<Statistics> stats);
}
