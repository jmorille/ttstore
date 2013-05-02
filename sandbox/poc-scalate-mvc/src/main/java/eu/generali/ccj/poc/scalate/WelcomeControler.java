package eu.generali.ccj.poc.scalate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeControler {

	@RequestMapping("/index")
	public String execute(ModelMap model) {
		return "index";
	}

}
