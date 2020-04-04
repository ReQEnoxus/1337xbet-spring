package com.enoxus.xbetspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetViewDto {
    private MatchDto match;
    private double amount;
    private double coefficient;
    private boolean active;
}
