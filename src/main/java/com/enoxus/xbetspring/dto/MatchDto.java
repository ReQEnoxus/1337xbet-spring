package com.enoxus.xbetspring.dto;

import com.enoxus.xbetspring.entity.CoefficientSet;
import com.enoxus.xbetspring.entity.Team;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MatchDto {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private CoefficientSet coefficients;
    private String localizedDate;
}
