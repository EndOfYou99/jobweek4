package hangman.business.repo.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private StatsRepository statsRepo;

	public void addStats(Statistics statistics) {
		statsRepo.save(statistics);
	}

}
