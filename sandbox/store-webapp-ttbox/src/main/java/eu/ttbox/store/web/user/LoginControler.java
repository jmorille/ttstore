package eu.ttbox.store.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControler {

	@RequestMapping("login")
	public String execute(ModelMap model) {
		model.addAttribute("user", "LoginControler"); 
		return "layout:login";
	}
	
}
