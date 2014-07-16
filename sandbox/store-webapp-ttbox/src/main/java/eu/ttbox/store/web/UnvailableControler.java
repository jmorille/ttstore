package eu.ttbox.store.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnvailableControler {

	@RequestMapping("/unvailable")
	public String execute(ModelMap model) {

		return "unvailable";
	}


}
