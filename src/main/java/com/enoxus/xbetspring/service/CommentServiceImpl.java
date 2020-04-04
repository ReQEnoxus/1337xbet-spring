package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.CommentCreationDto;
import com.enoxus.xbetspring.dto.CommentDto;
import com.enoxus.xbetspring.dto.ReplyDto;
import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.entity.Comment;
import com.enoxus.xbetspring.entity.Reply;
import com.enoxus.xbetspring.repositories.CommentRepository;
import com.enoxus.xbetspring.repositories.MatchRepository;
import com.enoxus.xbetspring.repositories.ReplyRepository;
import com.enoxus.xbetspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CommentDto> getCommentsForMatch(Long matchId) {
        List<Comment> comments = commentRepository.getAllByMatchId(matchId);

        return comments.stream()
                .map(comment -> CommentDto.builder()
                        .id(comment.getId())
                        .owner(userService.getUserById(comment.getOwner().getId()).orElseThrow(IllegalArgumentException::new))
                        .text(comment.getText())
                        .replies(replyRepository.getAllByParentId(comment.getId())
                                .stream()
                                .map(reply -> ReplyDto.builder()
                                        .owner(userService.getUserById(reply.getOwner().getId()).orElseThrow(IllegalArgumentException::new))
                                        .text(reply.getText())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void createComment(CommentCreationDto dto, UserDto user) {
        Comment comment = Comment.builder()
                .match(matchRepository.findById(dto.getMatchId()).orElseThrow(IllegalArgumentException::new))
                .owner(userRepository.findById(user.getId()).orElseThrow(IllegalArgumentException::new))
                .text(dto.getText())
                .build();
        commentRepository.save(comment);
    }
}
