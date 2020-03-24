package com.enoxus.xbetspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    private String login;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
