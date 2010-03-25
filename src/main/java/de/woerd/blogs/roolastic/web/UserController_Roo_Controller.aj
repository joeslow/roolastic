package de.woerd.blogs.roolastic.web;

import de.woerd.blogs.roolastic.model.RoolasticUser;
import java.lang.Long;
import java.lang.String;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect UserController_Roo_Controller {
    
    @RequestMapping(value = "/roolasticuser", method = RequestMethod.POST)
    public String UserController.create(@Valid RoolasticUser roolasticUser, BindingResult result, ModelMap modelMap) {
        if (roolasticUser == null) throw new IllegalArgumentException("A roolasticUser is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("roolasticUser", roolasticUser);
            return "roolasticuser/create";
        }
        roolasticUser.persist();
        return "redirect:/roolasticuser/" + roolasticUser.getId();
    }
    
    @RequestMapping(value = "/roolasticuser/form", method = RequestMethod.GET)
    public String UserController.createForm(ModelMap modelMap) {
        modelMap.addAttribute("roolasticUser", new RoolasticUser());
        return "roolasticuser/create";
    }
    
    @RequestMapping(value = "/roolasticuser/{id}", method = RequestMethod.GET)
    public String UserController.show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("roolasticUser", RoolasticUser.findRoolasticUser(id));
        return "roolasticuser/show";
    }
    
    @RequestMapping(value = "/roolasticuser", method = RequestMethod.GET)
    public String UserController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            modelMap.addAttribute("roolasticusers", RoolasticUser.findRoolasticUserEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) RoolasticUser.countRoolasticUsers() / sizeNo;
            modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            modelMap.addAttribute("roolasticusers", RoolasticUser.findAllRoolasticUsers());
        }
        return "roolasticuser/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String UserController.update(@Valid RoolasticUser roolasticUser, BindingResult result, ModelMap modelMap) {
        if (roolasticUser == null) throw new IllegalArgumentException("A roolasticUser is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("roolasticUser", roolasticUser);
            return "roolasticuser/update";
        }
        roolasticUser.merge();
        return "redirect:/roolasticuser/" + roolasticUser.getId();
    }
    
    @RequestMapping(value = "/roolasticuser/{id}/form", method = RequestMethod.GET)
    public String UserController.updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("roolasticUser", RoolasticUser.findRoolasticUser(id));
        return "roolasticuser/update";
    }
    
    @RequestMapping(value = "/roolasticuser/{id}", method = RequestMethod.DELETE)
    public String UserController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        RoolasticUser.findRoolasticUser(id).remove();
        return "redirect:/roolasticuser?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @RequestMapping(value = "find/ByUsername/form", method = RequestMethod.GET)
    public String UserController.findRoolasticUsersByUsernameForm(ModelMap modelMap) {
        return "roolasticuser/findRoolasticUsersByUsername";
    }
    
    @RequestMapping(value = "find/ByUsername", method = RequestMethod.GET)
    public String UserController.findRoolasticUsersByUsername(@RequestParam("username") String username, ModelMap modelMap) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("A Username is required.");
        modelMap.addAttribute("roolasticusers", RoolasticUser.findRoolasticUsersByUsername(username).getResultList());
        return "roolasticuser/list";
    }
    
}
