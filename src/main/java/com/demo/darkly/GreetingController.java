package com.demo.darkly;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@Autowired
	protected LDBean ldb;

	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		model.addAttribute("homePromotion", ldb.homePagePromotion());
		model.addAttribute("homeMenuVariant", ldb.homeMenuVariant());
		model.addAttribute("accountFlag", ldb.accountFlag());
		return "home";
	}
	@GetMapping("/greetings")
	public String greetings(@RequestParam(name="name", required=false, defaultValue="Human") String name, Model model) {
		model.addAttribute("name", name);

		String template;
		if(ldb.greetingVariant()) {
			template = "greeting-a";
		} else {
			template = "greeting-b";
		}
		return template;
	}

	@GetMapping("/partings")
	public String partings(@RequestParam(name="name", required=false, defaultValue="Human") String name, Model model) {
		model.addAttribute("name", name);

		String template;
		if(ldb.partingVariant()) {
			template = "parting-a";
		} else {
			template = "parting-b";
		}
		return template;
	}

}
