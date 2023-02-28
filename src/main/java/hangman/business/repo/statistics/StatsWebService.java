package hangman.business.repo.statistics;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface StatsWebService {

	@WebMethod
	public List<Statistics> getAllStatistics();

}
