package de.woerd.blogs.roolastic.persistence;

import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.woerd.blogs.roolastic.search.IndexService;

public class HibernateInterceptor extends EmptyInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(HibernateInterceptor.class);
	
	@Override
	public void postFlush(Iterator entities) {
		for(Iterator it = entities; it.hasNext();) {
			IndexService.index(it.next());
		}
	}
	
}
