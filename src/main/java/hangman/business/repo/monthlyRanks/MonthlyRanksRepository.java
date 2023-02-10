package hangman.business.repo.monthlyRanks;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hangman.business.repo.user.User;

public interface MonthlyRanksRepository extends JpaRepository<MonthlyRanks, Integer> {

	@Query("Select avg(s.tries)from Game g join g.statistics s where g.user=:user and s.date>:date ")
	public double averageTries(User user, LocalDate date);

	@Query("Select r from MonthlyRanks r where muser=:name")
	public MonthlyRanks findByName(String name);

	List<MonthlyRanks> findTop10ByOrderByMwinsDesc();

	List<MonthlyRanks> findAll();

//
//	@Query("Select r from MonthlyRanks r join r.statistics s  WHERE   s.date >:date order by r.wins desc")
//	public List<Ranks> bestOfTheMonth(LocalDate date);

}
