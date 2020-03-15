package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(user -> UserDto.builder()
                .avatarPath(user.getAvatar().getStorageFileName())
                .balance(user.getBalance())
                .email(user.getEmail())
                .id(user.getId())
                .lastName(user.getLastName())
                .name(user.getName())
                .login(user.getLogin())
                .build());
    }

    @Override
    public Optional<UserDto> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return userRepository.findByLogin(((UserDetails) principal).getUsername()).map(user -> UserDto.builder()
                    .avatarPath(user.getAvatar().getStorageFileName())
                    .balance(user.getBalance())
                    .email(user.getEmail())
                    .id(user.getId())
                    .lastName(user.getLastName())
                    .name(user.getName())
                    .login(user.getLogin())
                    .build());
        } else {
            return Optional.empty();
        }
    }
}
