package com.prediction2.service;

import com.prediction2.entity.Match;
import com.prediction2.repository.MatchRepository;
import com.prediction2.repository.PredictionRepository;
import com.prediction2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TableService {
    private final PredictionRepository predictionRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public TableService(PredictionRepository predictionRepository, MatchRepository matchRepository, UserRepository userRepository) {
        this.predictionRepository = predictionRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    public void buildTable() {
        List<Match> matches = matchRepository.findByPlayAtBeforeOrderByPlayAt(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        
    }
}
