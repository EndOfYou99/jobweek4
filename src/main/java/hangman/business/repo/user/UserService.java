package hangman.business.repo.user;

import hangman.business.repo.game.Game;

public interface UserService {

	public boolean check(String username, String password);

	public void add(User user);

	public boolean contains(String username);

	public User getUser(String name);

	public void addAchievements(Game game);

	public void update(User user);

}
