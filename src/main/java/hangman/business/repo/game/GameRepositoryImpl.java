package hangman.business.repo.game;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import hangman.business.repo.statistics.Statistics;
import hangman.business.repo.user.User;

@Component("GameRepositoryImpl")
@Primary
public class GameRepositoryImpl implements GameRepository {
	@Autowired
	private EntityManager e;

	public Game getGameById(UUID id) {
		String stringId = id.toString();

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Game> criteriaQuery = criteriaBuilder.createQuery(Game.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), stringId));

		TypedQuery<Game> query = e.createQuery(criteriaQuery);

		Game game = query.getSingleResult();
		return game;

	}

	public boolean isEmpty() {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Game> criteriaQuery = criteriaBuilder.createQuery(Game.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		criteriaQuery.select(root);

		TypedQuery<Game> query = e.createQuery(criteriaQuery);
		List<Game> list = query.getResultList();

		if (list.size() > 0) {
			return false;
		} else {
			return true;
		}

	}

	@Transactional
	public void add(UUID id, Game Game) {
		String stringId = id.toString();
		Game.setId(stringId);
		e.persist(Game);

	}

	public boolean contains(UUID id) {
		String stringId = id.toString();
		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Game> criteriaQuery = criteriaBuilder.createQuery(Game.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), stringId));

		TypedQuery<Game> query = e.createQuery(criteriaQuery);
		List<Game> list = query.getResultList();
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Transactional
	public void update(Game Game) {
		e.merge(Game);

	}

	public int checkForWrongLetterCount(Game game) {
		String stringId = game.getId();

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(statsJoin.get("tries"));

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), stringId));

		TypedQuery<Integer> query = e.createQuery(criteriaQuery);
		Integer result = query.getSingleResult();

		return result;

	}

	public Long PlayedGames(Game game) {
		User username = game.getUser();

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), username));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long winnedGames(User user) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("result"), "W")));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long winnedGames(Game game) {
		User username = game.getUser();

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), username),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("result"), "W")));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long winnedHardGames(Game game) {
		User username = game.getUser();

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), username),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("result"), "W")),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 27)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long getHardGames(User user) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 27)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long winnedMediumGames(Game game) {
		User username = game.getUser();

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), username),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("result"), "W")),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 14)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long getMediumGames(User user) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 14)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long winnedEasyGames(Game game) {
		User username = game.getUser();

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), username),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("result"), "W")),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 1)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long getEasyGames(User user) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 1)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long lostGames(User user) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("result"), "L")));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long finishedGames(User user) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.notEqual(statsJoin.get("result"), "N")));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long monthlyFinishedGames(User user, LocalDate date) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.notEqual(statsJoin.get("result"), "N")),
				criteriaBuilder.and(criteriaBuilder.greaterThan(statsJoin.get("date"), date)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long monthlyLostGames(User user, LocalDate date) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("result"), "L")),
				criteriaBuilder.and(criteriaBuilder.greaterThan(statsJoin.get("date"), date)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long getMonthlyEasyGames(User user, LocalDate date) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 1)),
				criteriaBuilder.and(criteriaBuilder.greaterThan(statsJoin.get("date"), date)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long getMonthlyMediumGames(User user, LocalDate date) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 14)),
				criteriaBuilder.and(criteriaBuilder.greaterThan(statsJoin.get("date"), date)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long getMonthlyHardGames(User user, LocalDate date) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("diff"), 27)),
				criteriaBuilder.and(criteriaBuilder.greaterThan(statsJoin.get("date"), date)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

	public Long monthlyWinnedGames(User user, LocalDate date) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Game> root = criteriaQuery.from(Game.class);
		Join<Game, Statistics> statsJoin = root.join("statistics");
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user),
				criteriaBuilder.and(criteriaBuilder.equal(statsJoin.get("result"), "W")),
				criteriaBuilder.and(criteriaBuilder.greaterThan(statsJoin.get("date"), date)));

		TypedQuery<Long> query = e.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}

}
