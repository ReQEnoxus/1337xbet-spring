package com.enoxus.xbetspring.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Match {

    @Id
    private Long id;
    private int leagueId;
    private Date date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
    private int goalsHomeTeam;
    private int goalsAwayTeam;
    private boolean active;
}
