package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.entity.State;
import com.enoxus.xbetspring.entity.User;
import com.enoxus.xbetspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean confirm(String code) {
        Optional<User> userCandidate = userRepository.findByConfirmCode(code);

        if (userCandidate.isPresent()) {
            userCandidate.get().setState(State.CONFIRMED);
            userRepository.save(userCandidate.get());

            return true;
        }

        return false;
    }
}