package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.ServerErrorDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/login")
    public String getSignInPage() {
        return "login";
    }

    @GetMapping(value = "/login", params = {"error"})
    public String getSignInPage(Model model) {
        ServerErrorDto errorDto = ServerErrorDto.builder()
                .reason("Неверный логин или пароль")
                .build();

        model.addAttribute("error", errorDto);
        return "login";
    }
}
