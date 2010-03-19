package de.woerd.blogs.roolastic.search;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

@Service
public class IndexService {

    static final Logger logger = LoggerFactory.getLogger(IndexService.class);

    private static IndexService instance = null;
    
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private transient JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public void addToIndex(Object o) throws Exception {
    	Method method = o.getClass().getMethod("getId", new Class[]{}); // we assume that we deal with an entity if it has a method named 'getId'
        if (method != null) {
        	Map<String, Object> msg = new HashMap<String, Object>();
        	
            msg.put("id", (Long) method.invoke(o, new Object[] {}));
            msg.put("type", ClassUtils.getShortName(o.getClass()));
            msg.put("content", mapper.writeValueAsString(o));
            
            jmsTemplate.convertAndSend(msg);
        }
    }

    /**
	 * Index the give object. Just siltently swallow any exceptions that might happen to not interfere with the callers business.
	 * @param o
	 */
    public static void index(Object o) {
        try {
            instance.addToIndex(o);
        } catch (Exception e) {
            logger.warn("Couldn't index object", e);
        }
    }

}
