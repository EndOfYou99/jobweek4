package hangman.business.repo.achievements;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hangman.business.repo.user.User;

public interface AchievementsRepository extends JpaRepository<Achievements, Integer> {

	public List<Achievements> findAllByUsers(User user);

	public Achievements getAchievementsById(int id);

}
