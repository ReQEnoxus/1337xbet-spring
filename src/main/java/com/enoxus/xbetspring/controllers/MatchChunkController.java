package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.MatchCountDto;
import com.enoxus.xbetspring.dto.MatchDto;
import com.enoxus.xbetspring.dto.QueryDto;
import com.enoxus.xbetspring.dto.ServerErrorDto;
import com.enoxus.xbetspring.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MatchChunkController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/matches")
    public String getMatchPortion(QueryDto query, Model model) {
        List<MatchDto> matches = matchService.getMatchesByQuery(query);
        if (matches.isEmpty() && query.getOffset() == 0) {
            model.addAttribute("error", new ServerErrorDto("Матчей по заданным критериям не найдено"));
        }
        model.addAttribute("matches", matches);
        return "matchListGenerator";
    }

    @GetMapping("/matches/count")
    public ResponseEntity<Long> getTotalMatchCount(MatchCountDto dto) {
        return ResponseEntity.ok(matchService.getMatchCount(dto.getMatchNumber()));
    }
}
