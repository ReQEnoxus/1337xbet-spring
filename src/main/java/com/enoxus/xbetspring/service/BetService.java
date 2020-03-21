package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.BetDto;
import com.enoxus.xbetspring.dto.UserDto;

public interface BetService {
    void createBetForUser(BetDto betDto, UserDto userDto);

    void verifyAndCloseBetsOnFinishedMatches();
}
