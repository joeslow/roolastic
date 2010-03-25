package de.woerd.blogs.roolastic.web;

import de.woerd.blogs.roolastic.model.Authority;
import java.lang.Long;
import java.lang.String;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect AuthorityController_Roo_Controller {
    
    @RequestMapping(value = "/authority", method = RequestMethod.POST)
    public String AuthorityController.create(@Valid Authority authority, BindingResult result, ModelMap modelMap) {
        if (authority == null) throw new IllegalArgumentException("A authority is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("authority", authority);
            return "authority/create";
        }
        authority.persist();
        return "redirect:/authority/" + authority.getId();
    }
    
    @RequestMapping(value = "/authority/form", method = RequestMethod.GET)
    public String AuthorityController.createForm(ModelMap modelMap) {
        modelMap.addAttribute("authority", new Authority());
        return "authority/create";
    }
    
    @RequestMapping(value = "/authority/{id}", method = RequestMethod.GET)
    public String AuthorityController.show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("authority", Authority.findAuthority(id));
        return "authority/show";
    }
    
    @RequestMapping(value = "/authority", method = RequestMethod.GET)
    public String AuthorityController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            modelMap.addAttribute("authoritys", Authority.findAuthorityEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Authority.countAuthoritys() / sizeNo;
            modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            modelMap.addAttribute("authoritys", Authority.findAllAuthoritys());
        }
        return "authority/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String AuthorityController.update(@Valid Authority authority, BindingResult result, ModelMap modelMap) {
        if (authority == null) throw new IllegalArgumentException("A authority is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("authority", authority);
            return "authority/update";
        }
        authority.merge();
        return "redirect:/authority/" + authority.getId();
    }
    
    @RequestMapping(value = "/authority/{id}/form", method = RequestMethod.GET)
    public String AuthorityController.updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("authority", Authority.findAuthority(id));
        return "authority/update";
    }
    
    @RequestMapping(value = "/authority/{id}", method = RequestMethod.DELETE)
    public String AuthorityController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        Authority.findAuthority(id).remove();
        return "redirect:/authority?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
}
