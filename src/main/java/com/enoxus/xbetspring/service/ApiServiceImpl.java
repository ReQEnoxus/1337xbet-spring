package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.entity.Match;
import com.enoxus.xbetspring.repositories.MatchRepository;
import com.enoxus.xbetspring.repositories.TeamRepository;
import com.enoxus.xbetspring.util.JsonUtils;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiServiceImpl implements ApiService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private JsonUtils jsonUtils;

    @SneakyThrows
    @Override
    public void getMatchesForDate(String date) {
        JSONObject response = Unirest.get("https://api-football-v1.p.rapidapi.com/v2/fixtures/date/" + date + "?timezone=Europe%2FMoscow")
                .header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "fc058cc0aamsh60eb543baea04e6p13e0e0jsn36ec0adc778e")
                .asJson().getBody().getObject();


        List<Match> matches = jsonUtils.getMatchesFromJSON(response);

        matches.forEach(match -> {
            teamRepository.save(match.getHomeTeam());
            teamRepository.save(match.getAwayTeam());

            matchRepository.save(match);
        });
    }

    @SneakyThrows
    @Override
    public void updateMatchesForDate(String date) {
        JSONObject response = Unirest.get("https://api-football-v1.p.rapidapi.com/v2/fixtures/date/" + date + "?timezone=Europe%2FMoscow")
                .header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "fc058cc0aamsh60eb543baea04e6p13e0e0jsn36ec0adc778e")
                .asJson().getBody().getObject();

        List<Match> matches = jsonUtils.getMatchesFromJSON(response);

        matches.forEach(match -> matchRepository.save(match));
    }
}

