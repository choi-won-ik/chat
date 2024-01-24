package com.example.demo.controller.member;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//@ControllerAdvice
//public class loginInformation {
//	
//	@ModelAttribute
//	public void login(Model model) {
//		try {
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//			String me = authentication.getName();
//			model.addAttribute("me", me);
//		} catch (Exception e) {
//
//		} 
//	}
//}
