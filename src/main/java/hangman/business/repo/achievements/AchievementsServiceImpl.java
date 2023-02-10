package hangman.business.repo.achievements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hangman.business.repo.user.User;

@Service
public class AchievementsServiceImpl implements AchievementsService {

	@Autowired
	AchievementsRepository achievementRepo;

	public List<Achievements> findAllByUser(User user) {
		return achievementRepo.findAllByUsers(user);
	}
}
