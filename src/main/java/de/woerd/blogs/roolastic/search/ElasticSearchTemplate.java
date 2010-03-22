package de.woerd.blogs.roolastic.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.util.transport.InetSocketTransportAddress;

/**
 * Template to use for interfacing with an ElasticSearch cluster via ElasticSearch's Java API
 * Takes care of configuration and IO resource management.
 * 
 * @author joerg
 *
 */
public class ElasticSearchTemplate {
	
	private TransportClient client;
	
	private Map<String, Integer> nodeAddresses = new HashMap<String, Integer>();
	
	@PostConstruct
	public void init() {
		client = new TransportClient();
		for(Entry<String, Integer> nodeAddress : nodeAddresses.entrySet()) {
			client.addTransportAddress(new InetSocketTransportAddress(nodeAddress.getKey(), nodeAddress.getValue()));
		}
	}
	
	@PreDestroy
	public void close() {
		if(client != null) {
			try{
				client.close();
			} catch(Exception e){}
		}
	}
	
	
	public void index(String index, String type, String id, String content) {
		client.index(new IndexRequest(index)
						.id(id)
						.type(type)
						.source(content)).actionGet();
	}
	
	

	public void setNodeAddresses(Map<String, Integer> nodeAddresses) {
		this.nodeAddresses = nodeAddresses;
	}
	
	
}
