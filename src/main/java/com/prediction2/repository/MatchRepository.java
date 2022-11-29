package com.prediction2.repository;

import com.prediction2.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByOrderByPlayAt();
    List<Match> findByPlayAtAfterOrderByPlayAt(String dateTime);
    List<Match> findByPlayAtBeforeOrderByPlayAt(String dateTime);
}
