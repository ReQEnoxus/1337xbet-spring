package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.CommentCreationDto;
import com.enoxus.xbetspring.dto.CommentDto;
import com.enoxus.xbetspring.dto.UserDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsForMatch(Long matchId);

    void createComment(CommentCreationDto dto, UserDto user);
}
