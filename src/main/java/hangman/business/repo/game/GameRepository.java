package hangman.business.repo.game;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import hangman.business.repo.user.User;

public interface GameRepository {

	public void update(Game Game);

	public void add(UUID id, Game Game);

	public Game getGameById(UUID id);

	public List<Game> ongoing();

	public boolean isEmpty();

	public boolean contains(UUID id);

	public Long PlayedGames(Game game);

	public int checkForWrongLetterCount(Game game);

	public Long winnedGames(Game game);

	public Long winnedHardGames(Game game);

	public Long winnedMediumGames(Game game);

	public Long winnedGames(User user);

	public Long finishedGames(User user);

	public Long lostGames(User user);

	public Long getEasyGames(User user);

	public Long getMediumGames(User user);

	public Long getHardGames(User user);

	public Long monthlyWinnedGames(User user, LocalDate date);

	public Long monthlyFinishedGames(User user, LocalDate date);

	public Long monthlyLostGames(User user, LocalDate date);

	public Long getMonthlyEasyGames(User user, LocalDate date);

	public Long getMonthlyMediumGames(User user, LocalDate date);

	public Long getMonthlyHardGames(User user, LocalDate date);

}
