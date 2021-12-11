package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qlpk.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	@GetMapping(value = {"/", "/login"})
	public String showLoginForm() {
		return "Login";
	}
//	@GetMapping("/loginSuccess")
//	public String redirectAfterLogin(Model model, HttpServletRequest request){
//
//		if(request.isUserInRole(Role.ADMIN.getType())){
//			return "redirect:/qlns/bacsi/ds-bacsi";
//		}
//		else if (request.isUserInRole(Role.BACSY.getType())){
//			return "redirect:/bacsi/list-benhan";
//		}
//		else return "redirect:/yta/list-benhan";
//	}
}
