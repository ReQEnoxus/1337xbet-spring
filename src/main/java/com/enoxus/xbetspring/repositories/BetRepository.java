package com.enoxus.xbetspring.repositories;

import com.enoxus.xbetspring.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> getAllByActiveTrue();

    List<Bet> getAllByOwnerId(Long id);
}
