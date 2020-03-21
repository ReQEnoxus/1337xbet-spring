package com.enoxus.xbetspring.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Team {

    @Id
    private int id;
    private String name;
    private String logoUrl;
}
