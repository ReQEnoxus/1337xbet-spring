package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.CommentCreationDto;
import com.enoxus.xbetspring.dto.ServerSuccessDto;
import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.service.CommentService;
import com.enoxus.xbetspring.service.ReplyService;
import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

    @PostMapping("/comment")
    public String makeComment(CommentCreationDto dto, HttpSession session) {
        UserDto user = userService.getCurrentUser().get();

        if (dto.getParentId() == null) {
            commentService.createComment(dto, user);
        } else {
            replyService.createReply(dto, user);
        }

        session.setAttribute("commentSuccess", new ServerSuccessDto("Комментарий успешно добавлен"));

        return "redirect:/match?id=" + dto.getMatchId();
    }
}
