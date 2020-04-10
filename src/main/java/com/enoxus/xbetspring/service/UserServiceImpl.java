package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.entity.State;
import com.enoxus.xbetspring.entity.User;
import com.enoxus.xbetspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .state(user.getState().name())
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
                    .state(user.getState().name())
                    .build());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll().stream().map(user -> UserDto.builder()
                .avatarPath(user.getAvatar().getStorageFileName())
                .balance(user.getBalance())
                .email(user.getEmail())
                .id(user.getId())
                .lastName(user.getLastName())
                .name(user.getName())
                .login(user.getLogin())
                .state(user.getState().name())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getAdmin() {
        return userRepository.findByState(State.ADMIN).map(user -> UserDto.builder()
                .avatarPath(user.getAvatar().getStorageFileName())
                .balance(user.getBalance())
                .email(user.getEmail())
                .id(user.getId())
                .lastName(user.getLastName())
                .name(user.getName())
                .login(user.getLogin())
                .state(user.getState().name())
                .build());
    }
}
