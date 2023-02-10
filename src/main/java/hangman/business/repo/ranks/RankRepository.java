package hangman.business.repo.ranks;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hangman.business.repo.user.User;

@Repository
public interface RankRepository extends JpaRepository<Ranks, Integer> {

	@Query("Select avg(s.tries)from Game g join g.statistics s where g.user=:user ")
	public double averageTries(User user);

	@Query("Select r from Ranks r where user=:name")
	public Ranks findByName(String name);

	List<Ranks> findTop10ByOrderByWinsDesc();

	@Query("Select r from Ranks r join r.statistics s  WHERE   s.date >:date order by r.wins desc")
	public List<Ranks> bestOfTheMonth(LocalDate date);

}
