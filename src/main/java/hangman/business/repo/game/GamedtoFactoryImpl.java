package hangman.business.repo.game;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GamedtoFactoryImpl implements GamedtoFactory {

	@Override
	public Gamedto fromEntity(Game game) {
		Gamedto dto = new Gamedto();

		dto.setId(game.getId());
		dto.setWord(game.getWord());
		dto.setUser(game.getUser().getUsername());
		dto.setMaxTries(game.getMaxTries());
		dto.setTries(game.getTries());
		dto.setDiff(game.getDiff());
		dto.setHiddenWord(game.getHiddenWord());
		dto.setResult(game.getResult());
		dto.setUsedLetters(game.getUsedLetters());
		return dto;
	}

	@Override
	public List<Gamedto> fromEntities(List<Game> games) {
		List<Gamedto> list = new LinkedList<>();
		for (Game item : games) {
			list.add(fromEntity(item));
		}
		return list;
	}

}
