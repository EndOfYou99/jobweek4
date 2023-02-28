package hangman;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hangman.business.repo.statistics.StatsWebServiceImpl;

@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;

	@Autowired
	private StatsWebServiceImpl statImpl;

	@Bean
	public Endpoint endpoint() {

		EndpointImpl endpoint = new EndpointImpl(bus, statImpl);
		endpoint.publish("/GREETING");
		return endpoint;

	}

}
