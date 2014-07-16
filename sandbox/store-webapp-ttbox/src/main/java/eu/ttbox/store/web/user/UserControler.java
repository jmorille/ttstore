package eu.ttbox.store.web.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserControler {

	@RequestMapping("/user")
	public String execute(ModelMap model) {
		model.addAttribute("user", "Arnold");
		model.addAttribute("userList", getUsers());
		return "layout:userList";
	}

	@RequestMapping("/group")
	public String executeGroup(ModelMap model) {
		model.addAttribute("user", "Arnold");
		model.addAttribute("userList", getUsers());
		return "layout:group";
	}

	@RequestMapping("/secure/user")
	public String executeUserAccount(ModelMap model) {
		model.addAttribute("user", "Arnold");
		model.addAttribute("userList", getUsers());
		return "layout:user/account";
	}
	
	private List<String> getUsers() {
		List<String> users = new ArrayList<String>();
		users.add("toto");
		users.add("tata");
		users.add("titi");

		return users;
	}

}
