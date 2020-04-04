package com.enoxus.xbetspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "owner_id")
    @ManyToOne
    private User owner;
    @JoinColumn(name = "match_id")
    @ManyToOne
    private Match match;
    private String text;
}
