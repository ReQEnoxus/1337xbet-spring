package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.service.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/confirm/{code}")
    public String confirm(@PathVariable("code") String code, Model model) {
        if (confirmService.confirm(code)) {
            model.addAttribute("authorized", new Object());
        }
        return "confirm";
    }
}