package eu.ttbox.store.web.website;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CgvControler {

	@RequestMapping("/cgv")
	public String execute(ModelMap model) {
		model.addAttribute("user", "CGV"); 
		return "layout:website/cgv";
	}
	
}
