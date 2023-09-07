package com.sparta.springauth.controller;

import com.sparta.springauth.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) { //<-- 이부분빠졌었음.
        // 페이지 동적 처리 : 사용자 이름
        model.addAttribute("username", userDetails.getUser().getUsername());

        return "index";
    }
}