package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.entity.State;
import com.enoxus.xbetspring.service.MessageService;
import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SupportController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/support")
    public String getSupportPage(Model model) {

        UserDto user = userService.getCurrentUser().get();
        model.addAttribute("user", user);

        model.addAttribute("adminLogin", userService.getAdmin().get().getLogin());

        if (user.getState().equals(State.ADMIN.name())) {
            model.addAttribute("users", userService.getAllUsers());
        }

        model.addAttribute("messages", messageService.getDialog(user.getLogin(), userService.getAdmin().get().getLogin()));
        return "support";
    }

    @GetMapping("/manage")
    public String getAdminPage(@RequestParam(value = "receiver") String receiver, Model model) {
        UserDto user = userService.getCurrentUser().get();
        model.addAttribute("adminLogin", userService.getAdmin().get().getLogin());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("messages", messageService.getDialog(user.getLogin(), receiver));
        return "support";
    }
}
