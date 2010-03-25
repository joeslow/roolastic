package de.woerd.blogs.roolastic.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.server.Server;
import org.elasticsearch.server.ServerBuilder;
import org.elasticsearch.util.settings.ImmutableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Template to use for interfacing with an ElasticSearch cluster via ElasticSearch's Java API
 * Takes care of configuration and IO resource management.
 * 
 * @author joerg
 *
 */
public class ElasticSearchTemplate {
	
	final static Logger logger = LoggerFactory.getLogger(ElasticSearchTemplate.class);
	
	private Server server;
	private Client client;
	
	private Map<String, Integer> nodeAddresses = new HashMap<String, Integer>();
	
	@Value("${elasticsearch.default.index}")
	private String defaultIndex;
	
	@Value("${node.data}")
	private boolean nodeData;
	
	@Value("${node.local}")
	private boolean nodeLocal;
	
	@PostConstruct
	public void init() {
		try{
			Server server = new ServerBuilder().settings(ImmutableSettings.settingsBuilder().put("node.data", nodeData).put("node.local", nodeLocal)).server();
			client = server.client();
		} catch(Exception e) {
			logger.warn("Problems starting embedded elasticsearch node");
		}
	}
	
	@PreDestroy
	public void close() {
		if(server != null) {
			try{
				server.close();
			} catch(Exception e){}
		}
	}
	
	
	public void index(String index, String type, String id, String content) {
		logger.debug("adding to elasticsearch");
		try{
			client.index(new IndexRequest(index)
						.id(id)
						.type(type)
						.source(content)).actionGet();
		}catch(ElasticSearchException e) {
			//noop
			logger.warn("Error adding to elasticsearch");
		}
	}
	
	public List<String> search(String query) {
		logger.debug(String.format("Searching for [%s]", query));
		
		List<String> result = new ArrayList<String>();
		try{
			SearchResponse response = client.search(new SearchRequest(defaultIndex)
													.source(query)).actionGet();
			SearchHits hits = response.hits();
			for(SearchHit hit : hits.hits()) {
				result.add(hit.sourceAsString());
			}
		} catch(ElasticSearchException e) {
			logger.warn("Error running search against elasticsearch cluster", e);
		}
		return result;
	}
	
	

	public void setNodeAddresses(Map<String, Integer> nodeAddresses) {
		this.nodeAddresses = nodeAddresses;
	}
	
	
}
