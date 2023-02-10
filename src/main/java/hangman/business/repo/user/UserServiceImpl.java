package hangman.business.repo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hangman.business.repo.achievements.Achievements;
import hangman.business.repo.achievements.AchievementsRepository;
import hangman.business.repo.game.Game;
import hangman.business.repo.game.GameService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	AchievementsRepository achievementRepo;

	@Autowired
	GameService gameService;

	@Override
	public boolean check(String username, String password) {
		return userRepo.check(username, password);
	}

	@Override
	public void add(User user) {
		userRepo.add(user);

	}

	@Override
	public boolean contains(String username) {
		return userRepo.contains(username);
	}

	public User getUser(String name) {
		return userRepo.getUser(name);
	}

	public void addAchievements(Game game) {
		User user = game.getUser();
		List<Achievements> achievements = achievementRepo.findAllByUsers(user);
		user.setAchievements(achievements);

		if (!user.getAchievements().contains("Win 5 medium games") && gameService.winned5MediumGames(game)) {
			user.addAchievement(achievementRepo.getAchievementsById(1));

		}

		if (!user.getAchievements().contains("Win 5 games") && gameService.winned5Games(game)) {
			user.addAchievement(achievementRepo.getAchievementsById(2));

		}
		if (!user.getAchievements().contains("Win 5  hard games") && gameService.winned5HardGames(game)) {
			user.addAchievement(achievementRepo.getAchievementsById(3));

		}
		if (!user.getAchievements().contains("Play 10 games") && gameService.Played10Games(game)) {
			user.addAchievement(achievementRepo.getAchievementsById(4));

		}
		userRepo.update(user);

	}

	public void update(User user) {
		userRepo.update(user);
	}

}
