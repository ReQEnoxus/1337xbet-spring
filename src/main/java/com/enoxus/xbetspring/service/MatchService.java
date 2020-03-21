package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.MatchDto;
import com.enoxus.xbetspring.dto.QueryDto;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    List<MatchDto> getMatchesByQuery(QueryDto query);

    List<MatchDto> getInitialMatchesForCurrentDate();

    Long getMatchCount(Integer dateIndex);

    Optional<MatchDto> getMatchById(Long id);
}
