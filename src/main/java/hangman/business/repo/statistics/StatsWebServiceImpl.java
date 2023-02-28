package hangman.business.repo.statistics;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@WebService(name = "GREETING")
@Service
public class StatsWebServiceImpl {

	@Autowired
	StatsService statService;

	@Autowired
	StatisticsFactory statFactory;

	@WebMethod(operationName = "GET_STATS")
	public List<StatisticsDTO> getAllStatistics() {
		System.out.println("statisticsdto");
		return statFactory.fromEntities(statService.getAllStatistics());
	}

}
