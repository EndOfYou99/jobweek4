package hangman.business.repo.user;

public interface UserRepository {

	public boolean check(String username, String password);

	public void add(User user);

	public boolean contains(String username);

	public User getUser(String name);

	public void update(User user);

}
