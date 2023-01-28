package hangman.business.repo;

import java.util.UUID;

public interface GameRepository {

	public void update(UUID id, Game Game);

	public void add(UUID id, Game Game);

	public Game getGameById(UUID id);

	public boolean isEmpty();

	public boolean contains(UUID id);

}
