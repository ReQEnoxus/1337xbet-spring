package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.MessageDto;

import java.util.List;

public interface MessageService {
    void save(MessageDto dto);

    List<MessageDto> getDialog(String firstLogin, String secondLogin);
}
