package eu.ep.domibus.embedded_broker;

import java.util.logging.Logger;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class EmbeddedBrokerApp {
	
	private static final String TOMCAT_CONTEXT_PATH = "";
	private static Logger logger = Logger.getLogger(EmbeddedBrokerApp.class.getName());
	
	public static void main(String[] args) throws LifecycleException {
        // Create a Tomcat instance
		logger.info("Starting embedded Tomcat");
        Tomcat tomcat = new Tomcat();
        Context context = tomcat.addContext(TOMCAT_CONTEXT_PATH, null);
        context.addApplicationListener("eu.ep.domibus.embedded_broker.EmbeddedBrokerServlet");
        
        // Start the Tomcat instance
        try {
			tomcat.start();
		} catch (LifecycleException e) {
			logger.info("Tomcat failed on the start up");
			throw e;
		}
        logger.info("Tomcat was start up successfully");
        // Keep the Tomcat server running
        tomcat.getServer().await();
	}
}
