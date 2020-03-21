package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.BetDto;
import com.enoxus.xbetspring.dto.ServerErrorDto;
import com.enoxus.xbetspring.dto.ServerSuccessDto;
import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.exceptions.BetCreatingException;
import com.enoxus.xbetspring.service.BetService;
import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

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
