package de.woerd.blogs.roolastic.persistence;

import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateInterceptor extends EmptyInterceptor {
	
	final static Logger logger = LoggerFactory.getLogger(HibernateInterceptor.class);
	
	@Override
	public void postFlush(Iterator entities) {
		for(Iterator it = entities; it.hasNext();) {
			logger.debug(it.next().toString());
		}
	}
	
}
