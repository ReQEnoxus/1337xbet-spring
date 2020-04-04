package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.BetDto;
import com.enoxus.xbetspring.dto.BetViewDto;
import com.enoxus.xbetspring.dto.UserDto;

import java.util.List;

public interface BetService {
    void createBetForUser(BetDto betDto, UserDto userDto);

    void verifyAndCloseBetsOnFinishedMatches();

    List<BetViewDto> getAllBetsOfUser(Long userId);
}
