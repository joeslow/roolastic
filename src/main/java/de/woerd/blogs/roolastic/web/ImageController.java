package de.woerd.blogs.roolastic.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import de.woerd.blogs.roolastic.model.Image;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "image", automaticallyMaintainView = true, formBackingObject = Image.class)
@RequestMapping("/image/**")
@Controller
public class ImageController {
}
