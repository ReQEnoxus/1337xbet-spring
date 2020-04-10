package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {

    @Autowired
    private UserService userService;

    @GetMapping("/errorPage")
    public String getErrorPage(Model model) {
        userService.getCurrentUser().ifPresent(userDto -> model.addAttribute("user", userDto));
        return "error";
    }
}
