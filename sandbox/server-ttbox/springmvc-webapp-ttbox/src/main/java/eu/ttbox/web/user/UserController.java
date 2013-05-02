package eu.ttbox.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
 
	Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public String index() { 
		return "/WEB-INF/scalate/user/index.scaml";
	}

	 

}
