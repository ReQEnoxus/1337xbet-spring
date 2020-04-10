package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUserById(Long id);

    Optional<UserDto> getCurrentUser();

    List<UserDto> getAllUsers();

    Optional<UserDto> getAdmin();
}
