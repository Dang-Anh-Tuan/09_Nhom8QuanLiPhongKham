package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	@GetMapping("/404")
	public String showErrorView() {
		return "404";
	}
	@GetMapping("/403")
	public String showAccessDenied(){
		return "403";
	}
}
