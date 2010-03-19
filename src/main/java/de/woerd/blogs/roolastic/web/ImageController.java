package de.woerd.blogs.roolastic.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import de.woerd.blogs.roolastic.model.Image;

@RooWebScaffold(path = "image", automaticallyMaintainView = false, formBackingObject = Image.class)
@RequestMapping("/image/**")
@Controller
public class ImageController {
	
	final static Logger logger = LoggerFactory.getLogger(ImageController.class);
	final static String diskStore = "/srv/data/roolastic";
	
	
	@RequestMapping(value = "/image", method = RequestMethod.POST)
    public String create(HttpServletRequest request, @Valid Image image, BindingResult result, ModelMap modelMap) throws IOException {
		
        if (image == null) throw new IllegalArgumentException("A image is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("image", image);
            return "image/create";
        }
        
        storeImage(image);
        
        image.persist();
        return "redirect:/image/" + image.getId();
    }
    
    private void storeImage(Image image) throws IOException {
		// store file
    	File newFile = new File(diskStore, image.getUpload().getOriginalFilename());
    	image.getUpload().transferTo(newFile);
		image.setFile(newFile.getName());
		
		try {
			Metadata metdata = JpegMetadataReader.readMetadata(newFile);
			StringBuilder builder = new StringBuilder();
			for(Iterator<Directory> it = metdata.getDirectoryIterator(); it.hasNext();) {
				Directory dir = it.next();
				for(Iterator<Tag> iit = dir.getTagIterator(); iit.hasNext();) {
					Tag tag = iit.next();
					builder.append(tag.toString());
					builder.append("\n");
				}
			}
			image.setMetadata(builder.toString());
		} catch (JpegProcessingException e) {
			throw new IOException("Couldn't extract metdata", e);
		}
    	
	}

	@RequestMapping(value = "/image/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        modelMap.addAttribute("image", new Image());
        return "image/create";
    }
    
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("image", Image.findImage(id));
        return "image/show";
    }
    
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
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
    public String update(@Valid Image image, BindingResult result, ModelMap modelMap) {
        if (image == null) throw new IllegalArgumentException("A image is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("image", image);
            return "image/update";
        }
        image.merge();
        return "redirect:/image/" + image.getId();
    }
    
    @RequestMapping(value = "/image/{id}/form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("image", Image.findImage(id));
        return "image/update";
    }
    
    @RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        Image.findImage(id).remove();
        return "redirect:/image?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
}
