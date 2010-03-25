package de.woerd.blogs.roolastic.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import de.woerd.blogs.roolastic.model.RoolasticUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "roolasticuser", automaticallyMaintainView = true, formBackingObject = RoolasticUser.class)
@RequestMapping("/roolasticuser/**")
@Controller
public class UserController {
}
