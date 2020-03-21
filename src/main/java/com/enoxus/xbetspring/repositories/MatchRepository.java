package com.enoxus.xbetspring.repositories;

import com.enoxus.xbetspring.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findAllByDateBetween(Date dateFrom, Date dateTo);
}
