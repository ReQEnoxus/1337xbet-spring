package com.enoxus.xbetspring.security.details;

import com.enoxus.xbetspring.entity.User;
import com.enoxus.xbetspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userCandidate = userRepository.findByLogin(s);

        if (userCandidate.isPresent()) {
            return new UserDetailsImpl(userCandidate.get());
        } else {
            throw new UsernameNotFoundException("No users were found by login: " + s);
        }
    }
}
