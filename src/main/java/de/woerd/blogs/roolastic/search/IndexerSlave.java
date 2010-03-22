package de.woerd.blogs.roolastic.search;

import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.util.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class IndexerSlave {
	
	final static Logger logger = LoggerFactory.getLogger(IndexerSlave.class);
	
	@Autowired
	private ElasticSearchTemplate esTemplate;
	
    public void onMessage(Map message) {
        logger.debug(String.format("Message ad to index: id=%d, type=%s, content=\n%s", message.get("id"), message.get("type"), message.get("content")));
        
        esTemplate.index("main", (String) message.get("type"), ((Long)message.get("id")).toString(), (String)message.get("content"));
    }
}
