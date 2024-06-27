package eu.ep.domibus.embedded_broker;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.xbean.BrokerFactoryBean;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

public class EmbeddedBrokerHandler {
	
	Logger logger = Logger.getLogger(EmbeddedBrokerHandler.class.getName());
	
	BrokerFactoryBean factoryBean;
	
	public EmbeddedBrokerHandler() throws Exception {
		Resource config = new PathResource(System.getProperty("catalina.home") + "/conf/activemq.xml");;
		factoryBean = new BrokerFactoryBean(config);
		factoryBean.afterPropertiesSet();
	}
	
	List<BrokerService> brokers = new ArrayList<>();

	public BrokerService createNewBroker(String name, String connector, String networkConnector, Boolean isPersistent) throws Exception {
		String logMsg = String.format("Creating new broker service with name %s, connector %s, netoworkConnector %s, isPersistent %s",
				name, connector, networkConnector, isPersistent);
		logger.info(logMsg);
		BrokerService broker = new BrokerService();
		broker.setBrokerName(name);
		broker.addConnector(connector);
		if (networkConnector != null) {
			broker.addNetworkConnector(networkConnector);
		}
		if (isPersistent != null) {
			broker.setPersistent(isPersistent);
		}
		logger.info("Created broker with name " + name);
		logger.info("Sarting broker with name " + name);
		broker.start();
		logger.info("Sarted broker with name " + name);
		logger.info("Adding broker with name " + name + " to the broker list");
		brokers.add(broker);
		return broker;
	}
	
	public void stopAll() throws Exception {
		logger.info("Stopping all brokers");
		for (BrokerService broker : brokers) {
			logger.info("Stopping broker with name " + broker.getBrokerName());
			broker.stop();
			logger.info("Stopped broker with name " + broker.getBrokerName());
		}
	}
}
