package eu.ep.domibus.embedded_broker;

import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EmbeddedBrokerServlet implements ServletContextListener {
	static Logger logger = Logger.getLogger(EmbeddedBrokerServlet.class.getName());
	
	EmbeddedBrokerHandler handler;
    
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Get the ServletContext
            ServletContext context = sce.getServletContext();
            
            // Retrieve the context parameter
            String configPath = context.getInitParameter("activemq.config.path");
        	handler = new EmbeddedBrokerHandler(Optional.ofNullable(configPath).orElse(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            handler.stopAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}