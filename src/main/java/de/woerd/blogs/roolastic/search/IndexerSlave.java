package de.woerd.blogs.roolastic.search;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IndexerSlave {
	
	final static Logger logger = LoggerFactory.getLogger(IndexerSlave.class);
	
    public void onMessage(Map message) {
        logger.debug(String.format("Message to index: id=%d, type=%s, content=\n%s", message.get("id"), message.get("type"), message.get("content")));
    }
}
