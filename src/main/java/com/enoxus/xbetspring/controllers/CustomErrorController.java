package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @Autowired
    private UserService userService;

    @RequestMapping("/error")
    public String handleError(Model model) {
        userService.getCurrentUser().ifPresent(userDto -> model.addAttribute("user", userDto));
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
