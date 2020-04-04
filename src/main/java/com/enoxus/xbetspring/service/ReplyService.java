package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.CommentCreationDto;
import com.enoxus.xbetspring.dto.UserDto;

public interface ReplyService {
    void createReply(CommentCreationDto dto, UserDto user);
}
