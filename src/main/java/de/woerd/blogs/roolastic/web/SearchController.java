package de.woerd.blogs.roolastic.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.woerd.blogs.roolastic.model.Image;
import de.woerd.blogs.roolastic.search.ElasticSearchTemplate;

@RequestMapping("/search/**")
@Controller
public class SearchController {
	
	@Autowired
	private ElasticSearchTemplate esTemplate;
	
    @RequestMapping(method = RequestMethod.GET, params = {"q"})
    public String get(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "q") String q) {
    	modelMap.addAttribute("results", esTemplate.search(q));
    	return "search/result";
    }
    
    @RequestMapping(method = RequestMethod.GET, params = {"qe"})
    public String getEntities(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "qe") String q) throws JsonParseException, JsonMappingException, IOException {
    	
    	List<Image> results = new ArrayList<Image>();
    	ObjectMapper mapper = new ObjectMapper();
    	for(String source : esTemplate.search(q)) {
    		results.add(mapper.readValue(source, Image.class));
    	}
    	
    	modelMap.addAttribute("results", results);
    	return "search/entityresult";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "search/index";
    }
}
