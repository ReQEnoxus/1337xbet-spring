package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.*;
import com.enoxus.xbetspring.exceptions.BetCreatingException;
import com.enoxus.xbetspring.service.BetService;
import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

    @GetMapping("/bets")
    public String getBetsPage(Model model) {
        UserDto user = userService.getCurrentUser().get();
        List<BetViewDto> bets = betService.getAllBetsOfUser(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("bets", bets);
        return "bets";
    }

    @PostMapping("/bets")
    public String makeBet(BetDto betDto, HttpSession session) {
        UserDto user = userService.getCurrentUser().get();
        try {
            betService.createBetForUser(betDto, user);
            session.setAttribute("success", new ServerSuccessDto("Ставка успешно создана"));
        } catch (BetCreatingException e) {
            session.setAttribute("error", new ServerErrorDto(e.getMessage()));
        }

        return "redirect:/match?id=" + betDto.getMatchId();
    }
}
