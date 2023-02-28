package hangman.business.repo.statistics;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StatisticsFactoryImpl implements StatisticsFactory {

	@Override
	public StatisticsDTO fromEntity(Statistics stats) {
		StatisticsDTO dto = new StatisticsDTO();
		dto.setStatId(stats.getStatId());
		dto.setMaxTries(stats.getMaxTries());
		dto.setTries(stats.getTries());
		dto.setDiff(stats.getDiff());
		dto.setHiddenWord(stats.getHiddenWord());
		dto.setResult(stats.getResult());
		return dto;

	}

	@Override
	public List<StatisticsDTO> fromEntities(List<Statistics> stats) {
		List<StatisticsDTO> list = new LinkedList<>();
		for (Statistics item : stats) {
			list.add(fromEntity(item));
		}
		return list;
	}

}
