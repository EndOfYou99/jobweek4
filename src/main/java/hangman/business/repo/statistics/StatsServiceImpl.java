package hangman.business.repo.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private StatsRepository statsRepo;

	public void addStats(Statistics statistics) {
		statsRepo.save(statistics);
	}

	public List<Statistics> getAllStatistics() {
		return statsRepo.findAll();
	}

}
