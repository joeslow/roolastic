package de.woerd.blogs.roolastic.web;

import de.woerd.blogs.roolastic.model.Image;
import java.lang.Long;
import java.lang.String;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect ImageController_Roo_Controller {
    
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String ImageController.create(@Valid Image image, BindingResult result, ModelMap modelMap) {
        if (image == null) throw new IllegalArgumentException("A image is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("image", image);
            return "image/create";
        }
        image.persist();
        return "redirect:/image/" + image.getId();
    }
    
    @RequestMapping(value = "/image/form", method = RequestMethod.GET)
    public String ImageController.createForm(ModelMap modelMap) {
        modelMap.addAttribute("image", new Image());
        return "image/create";
    }
    
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public String ImageController.show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("image", Image.findImage(id));
        return "image/show";
    }
    
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String ImageController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            modelMap.addAttribute("images", Image.findImageEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Image.countImages() / sizeNo;
            modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            modelMap.addAttribute("images", Image.findAllImages());
        }
        return "image/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ImageController.update(@Valid Image image, BindingResult result, ModelMap modelMap) {
        if (image == null) throw new IllegalArgumentException("A image is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("image", image);
            return "image/update";
        }
        image.merge();
        return "redirect:/image/" + image.getId();
    }
    
    @RequestMapping(value = "/image/{id}/form", method = RequestMethod.GET)
    public String ImageController.updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("image", Image.findImage(id));
        return "image/update";
    }
    
    @RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
    public String ImageController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        Image.findImage(id).remove();
        return "redirect:/image?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
}
