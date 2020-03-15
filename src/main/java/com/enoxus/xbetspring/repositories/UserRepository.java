package com.enoxus.xbetspring.repositories;

import com.enoxus.xbetspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Optional<User> findByConfirmCode(String code);
}
