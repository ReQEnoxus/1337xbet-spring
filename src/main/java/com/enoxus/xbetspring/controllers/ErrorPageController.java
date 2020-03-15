package com.enoxus.xbetspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {

    @GetMapping("/errorPage")
    public String getErrorPage() {
        return "error";
    }
}
