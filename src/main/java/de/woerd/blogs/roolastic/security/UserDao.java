package de.woerd.blogs.roolastic.security;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import de.woerd.blogs.roolastic.model.RoolasticUser;

@Repository
public class UserDao implements UserDetailsService {
	
	final static Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		try{
			return (RoolasticUser) RoolasticUser.findRoolasticUsersByUsername(username).getSingleResult();
		} catch(EntityNotFoundException  e) {
			throw new UsernameNotFoundException(String.format("Not user with username %s", username));
		} catch(NonUniqueResultException e) {
			logger.warn("More than one records matched username which should never happen");
			throw new RuntimeException("database contains more than one row with the same username, even though there is a unique constraint");
		}
	}
}
