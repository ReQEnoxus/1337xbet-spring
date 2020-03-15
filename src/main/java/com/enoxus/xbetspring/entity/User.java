package com.enoxus.xbetspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "xbet_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private Double balance;
    private String name;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "file_info_id")
    private FileInfo avatar;

    @Enumerated(value = EnumType.STRING)
    private State state;

    private String confirmCode;
}
