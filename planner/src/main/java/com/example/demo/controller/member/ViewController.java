package com.example.demo.controller.member;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.member.AdminAuthorize;
import com.example.demo.config.member.UserAuthorize;



@Controller
@RequestMapping("/view")

public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "view/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("loginId", user.getUsername());
        model.addAttribute("loginRoles", user.getAuthorities());
        return "view/dashboard";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "view/join";
    }

    @GetMapping("/setting/admin")
    @AdminAuthorize
    public String adminSettingPage() {
        return "view/admin_setting";
    }

    @GetMapping("/setting/user")
    @UserAuthorize
    public String userSettingPage() {
        return "view/user_setting";
    }
   
}