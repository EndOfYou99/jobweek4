package hangman.business.repo.achievements;

import java.util.List;

import hangman.business.repo.user.User;

public interface AchievementsService {

	public List<Achievements> findAllByUser(User user);

}
