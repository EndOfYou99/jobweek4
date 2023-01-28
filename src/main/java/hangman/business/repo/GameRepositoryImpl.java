package hangman.business.repo;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("GameRepositoryImpl")
@Primary
public class GameRepositoryImpl implements GameRepository {
	public HashMap<UUID, Game> gameRepository;

	public GameRepositoryImpl(HashMap<UUID, Game> gameRepository) {
		this.gameRepository = gameRepository;
	}

	public boolean contains(UUID id) {
		return gameRepository.containsKey(id);
	}

	public void update(UUID id, Game Game) {
		gameRepository.replace(id, Game);
	}

	public void add(UUID id, Game Game) {
		gameRepository.put(id, Game);
	}

	public Game getGameById(UUID id) {
		Game Game = gameRepository.get(id);
		return Game;
	}

	public boolean isEmpty() {
		if (gameRepository.isEmpty()) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		return "GameRepositoryImpl [GameRepositoryImpl=" + gameRepository + "]";

	}

}
