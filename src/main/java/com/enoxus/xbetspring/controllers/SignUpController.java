package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.SignUpDto;
import com.enoxus.xbetspring.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/register")
    public String getSignUpPage() {
        return "register";
    }

    @PostMapping("/register")
    public String signUp(SignUpDto dto) {
        signUpService.signUp(dto);

        return "redirect:/login";
    }
}
