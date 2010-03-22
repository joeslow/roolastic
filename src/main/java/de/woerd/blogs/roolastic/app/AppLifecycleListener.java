package de.woerd.blogs.roolastic.app;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * LifecycleListener to help initialising and cleaning up various aspects of the app
 * 
 * @author joerg
 *
 */
@Component
public class AppLifecycleListener implements ApplicationListener<ApplicationContextEvent> {

	final static Logger logger = LoggerFactory.getLogger(AppLifecycleListener.class);
	
	private Server server;
	
	/**
	 * We use @PostConstruct as the  {@link ContextStartedEvent} never seems to get sent
	 */
	@PostConstruct
	public void init() {
		logger.debug("startup");
		
//		try {
//			server = Server.createWebServer(null).start();
//		} catch (SQLException e) {
//			logger.warn("Couldn't start h2 console", e);
//		}
	}
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		logger.debug(String.format("ApplicationContextEvent of Subtype %s registered", ClassUtils.getShortName(event.getClass())));
		
		if(event instanceof ContextStoppedEvent) {
			// context closing down
			if(server != null) {
				server.stop();
			}
		}
		
	}

}
