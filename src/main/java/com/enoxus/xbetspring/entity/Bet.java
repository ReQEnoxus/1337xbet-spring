package com.enoxus.xbetspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    private double amount;
    private double coefficient;

    @Enumerated(value = EnumType.STRING)
    private Prediction prediction;
    private boolean active;
    private boolean won;
}