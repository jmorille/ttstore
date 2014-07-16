package eu.ttbox.web.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/login")
	public String login() {
		return "/WEB-INF/scalate/login.scaml";
	}

}
