package com.enoxus.xbetspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Double balance;
    private String avatarPath;
    private String login;
}
