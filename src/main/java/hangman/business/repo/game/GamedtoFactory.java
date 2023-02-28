package hangman.business.repo.game;

import java.util.List;

public interface GamedtoFactory {

	public Gamedto fromEntity(Game game);

	public List<Gamedto> fromEntities(List<Game> games);

}
