package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.MatchDto;
import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.service.MatchService;
import com.enoxus.xbetspring.service.UserService;
import com.enoxus.xbetspring.util.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RootController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @Autowired
    private DateHelper dateHelper;

    @GetMapping("/")
    public String getRootPage(Model model) {
        List<MatchDto> matches = matchService.getInitialMatchesForCurrentDate();
        UserDto user = userService.getCurrentUser().orElse(null);
        List<String> dates = dateHelper.thisWeekLocalized();

        model.addAttribute("user", user);
        model.addAttribute("matches", matches);
        model.addAttribute("dates", dates);

        return "main";
    }
}
