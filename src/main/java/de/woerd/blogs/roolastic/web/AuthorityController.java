package de.woerd.blogs.roolastic.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import de.woerd.blogs.roolastic.model.Authority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "authority", automaticallyMaintainView = true, formBackingObject = Authority.class)
@RequestMapping("/authority/**")
@Controller
public class AuthorityController {
}
