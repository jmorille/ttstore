package eu.ttbox.store.web.offer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchControler {

	@RequestMapping("search")
	public String execute(ModelMap model) {
		model.addAttribute("user", "SearchControler"); 
		return "layout:search";
	}
	
}
