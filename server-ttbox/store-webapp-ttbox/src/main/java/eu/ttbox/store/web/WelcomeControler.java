package eu.ttbox.store.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeControler {


	@RequestMapping("/index")
	public String execute(ModelMap model) {

		return "layout:index";
	}
}
