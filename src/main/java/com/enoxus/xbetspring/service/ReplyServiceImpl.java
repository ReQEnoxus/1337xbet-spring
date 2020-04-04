package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.CommentCreationDto;
import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.entity.Reply;
import com.enoxus.xbetspring.repositories.CommentRepository;
import com.enoxus.xbetspring.repositories.MatchRepository;
import com.enoxus.xbetspring.repositories.ReplyRepository;
import com.enoxus.xbetspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void createReply(CommentCreationDto dto, UserDto user) {
        Reply reply = Reply.builder()
                .owner(userRepository.findById(user.getId()).orElseThrow(IllegalArgumentException::new))
                .parent(commentRepository.findById(dto.getParentId()).orElseThrow(IllegalArgumentException::new))
                .text(dto.getText())
                .build();
        replyRepository.save(reply);
    }
}
