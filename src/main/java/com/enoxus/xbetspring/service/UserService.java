package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUserById(Long id);

    Optional<UserDto> getCurrentUser();
}
