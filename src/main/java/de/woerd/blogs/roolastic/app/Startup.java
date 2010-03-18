package de.woerd.blogs.roolastic.app;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class Startup implements ApplicationListener<ApplicationContextEvent> {

	final static Logger logger = LoggerFactory.getLogger(Startup.class);
	
	@PostConstruct
	public void init() {
		logger.debug("startup");
		
		try {
			org.h2.tools.Server.createWebServer(null).start();
		} catch (SQLException e) {
			logger.warn("Couldn't start h2 console", e);
		}
	}
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		logger.debug(String.format("ApplicationContextEvent of Subtype %s registered", ClassUtils.getShortName(event.getClass())));
		
		
	}

}
