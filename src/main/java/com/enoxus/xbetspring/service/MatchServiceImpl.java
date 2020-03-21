package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.MatchDto;
import com.enoxus.xbetspring.dto.QueryDto;
import com.enoxus.xbetspring.entity.Match;
import com.enoxus.xbetspring.repositories.MatchRepository;
import com.enoxus.xbetspring.util.BetHelper;
import com.enoxus.xbetspring.util.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<MatchDto> getMatchesByQuery(QueryDto query) {
        Date initial = new Date();
        Date queryDateStart = DateHelper.parse(DateHelper.getThisWeek().get(query.getDate()));
        Date queryDateEnd = new Date(queryDateStart.getTime() + 86400000);

        List<Match> allMatches = matchRepository.findAllByDateBetween(queryDateStart, queryDateEnd);
        return allMatches.stream()
                .filter(match -> match.getDate().after(initial)
                        && "Not Started".equals(match.getStatus())
                        && (match.getHomeTeam().getName() + " " + match.getAwayTeam().getName()).contains(query.getQuery()))
                .sorted(Comparator.comparingInt(m -> Math.min(m.getHomeTeam().getId(), m.getAwayTeam().getId())))
                .skip(query.getOffset())
                .limit(query.getLimit() - query.getOffset())
                .map(match -> MatchDto.builder()
                        .awayTeam(match.getAwayTeam())
                        .homeTeam(match.getHomeTeam())
                        .coefficients(BetHelper.coefficientSet(match.getHomeTeam().getId(), match.getAwayTeam().getId()))
                        .id(match.getId())
                        .localizedDate(DateHelper.russianLocalized(match.getDate()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchDto> getInitialMatchesForCurrentDate() {
        QueryDto query = QueryDto.builder()
                .date(0)
                .query("")
                .offset(0)
                .limit(10)
                .build();
        return getMatchesByQuery(query);
    }

    @Override
    public Long getMatchCount(Integer dateIndex) {
        Date initial = new Date();
        Date queryDateStart = DateHelper.parse(DateHelper.getThisWeek().get(dateIndex));
        Date queryDateEnd = new Date(queryDateStart.getTime() + 86400000);

        List<Match> matches = matchRepository.findAllByDateBetween(queryDateStart, queryDateEnd);

        return matches.stream().filter(match -> match.getDate().after(initial)
                && "Not Started".equals(match.getStatus()))
                .count();
    }

    @Override
    public Optional<MatchDto> getMatchById(Long id) {
        return matchRepository.findById(id).map(match -> MatchDto.builder()
                .localizedDate(DateHelper.russianLocalized(match.getDate()))
                .id(match.getId())
                .coefficients(BetHelper.coefficientSet(match.getHomeTeam().getId(), match.getAwayTeam().getId()))
                .homeTeam(match.getHomeTeam())
                .awayTeam(match.getAwayTeam())
                .build());
    }
}
