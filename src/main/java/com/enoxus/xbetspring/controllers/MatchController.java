package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.MatchDto;
import com.enoxus.xbetspring.service.MatchService;
import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @GetMapping("/match")
    public String getMatchPage(Long id, Model model, HttpSession session) {
        userService.getCurrentUser().ifPresent(userDto -> model.addAttribute("user", userDto));
        Optional<MatchDto> match = matchService.getMatchById(id);

        if (session.getAttribute("error") != null) {
            model.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        } else if (session.getAttribute("success") != null) {
            model.addAttribute("success", session.getAttribute("success"));
            session.removeAttribute("success");
        }

        if (match.isPresent()) {
            model.addAttribute("match", match.get());

            return "match";
        } else {
            return "errorPage";
        }
    }
}
