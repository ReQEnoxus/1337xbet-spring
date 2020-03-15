package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUserPage(Model model, @RequestParam(value = "id", required = false) Long id) {

        model.addAttribute("user", userService.getCurrentUser().get());

        if (id == null && userService.getCurrentUser().isPresent()) {
            model.addAttribute("requestedUser", userService.getCurrentUser().get());
            model.addAttribute("owner", true);
        } else if (id != null && userService.getUserById(id).isPresent()) {
            UserDto requestedUser = userService.getUserById(id).get();
            model.addAttribute("requestedUser", requestedUser);
            Optional<UserDto> currentUser = userService.getCurrentUser();
            if (currentUser.isPresent() && currentUser.get().getId().equals(requestedUser.getId())) {
                model.addAttribute("owner", true);
            } else {
                model.addAttribute("owner", false);
            }
        } else {
            return "redirect:/errorPage";
        }
        return "user";
    }
}
