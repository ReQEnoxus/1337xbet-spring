package com.enoxus.xbetspring.util;

import com.enoxus.xbetspring.entity.Match;
import com.enoxus.xbetspring.entity.Team;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonUtils {

    @Autowired
    @Qualifier("sdfForApiDecoding")
    private SimpleDateFormat formatter;

    public List<Match> getMatchesFromJSON(JSONObject json) {

        List<Match> matches = new ArrayList<>();

        JSONArray fixtures = json.getJSONObject("api").getJSONArray("fixtures");

        for (Object object : fixtures) {
            JSONObject fixture = (JSONObject) object;
            JSONObject homeTeamJson = fixture.getJSONObject("homeTeam");
            JSONObject awayTeamJson = fixture.getJSONObject("awayTeam");
            Team homeTeam = new Team(homeTeamJson.getInt("team_id"), homeTeamJson.getString("team_name"), homeTeamJson.getString("logo"));
            Team awayTeam = new Team(awayTeamJson.getInt("team_id"), awayTeamJson.getString("team_name"), awayTeamJson.getString("logo"));
            Match match = null;
            try {
                match = Match.builder()
                        .id(fixture.getLong("fixture_id"))
                        .leagueId(fixture.getInt("fixture_id"))
                        .status(fixture.getString("status"))
                        .homeTeam(homeTeam)
                        .awayTeam(awayTeam)
                        .date(formatter.parse(fixture.getString("event_date")))
                        .goalsHomeTeam(!fixture.isNull("goalsHomeTeam") ? fixture.getInt("goalsHomeTeam") : 0)
                        .goalsAwayTeam(!fixture.isNull("goalsAwayTeam") ? fixture.getInt("goalsAwayTeam") : 0)
                        .active(!fixture.getString("status").equals("Match Finished"))
                        .build();
                matches.add(match);
            } catch (ParseException e) {
                throw new IllegalStateException("Failed to parse");
            }
        }

        return matches;
    }
}
