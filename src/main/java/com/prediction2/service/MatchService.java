package com.prediction2.service;

import com.prediction2.entity.Match;
import com.prediction2.entity.Prediction;
import com.prediction2.repository.MatchRepository;
import com.prediction2.repository.PredictionRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final PredictionRepository predictionRepository;

    public MatchService(MatchRepository matchRepository, PredictionRepository predictionRepository) {
        this.matchRepository = matchRepository;
        this.predictionRepository = predictionRepository;
    }
    public List<Match> findAll() {
        return matchRepository.findAllByOrderByPlayAt();
    }
    public Match create(Match match) {
        return matchRepository.save(match);
    }
    public List<Match> findStarted() {
        return matchRepository.findByPlayAtBeforeOrderByPlayAt(
                LocalDateTime.now().minusHours(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
    public void saveResult(Long id, Short score1, Short score2) {
        Optional<Match> optionalMatch = matchRepository.findById(id);
        if (optionalMatch.isEmpty()) {
            return;
        }
        Match match = optionalMatch.get();
        match.setScore1(score1);
        match.setScore2(score2);
        matchRepository.save(match);

        List<Prediction> predictions = predictionRepository.findByMatchId(id);
        for (Prediction item : predictions) {
            int points = calculatePoints(match, item);
            item.setPoints(points);
            predictionRepository.save(item);
        }
    }

    private int calculatePoints(Match match, Prediction prediction) {
        if (match.getScore1() == prediction.getScore1() && match.getScore2() == prediction.getScore2())  {
            return 3;
        }
        int diffGoalsInMatch = match.getScore1() - match.getScore2();
        int diffGoalsInPrediction = prediction.getScore1() - prediction.getScore2();
        if (diffGoalsInMatch == diffGoalsInPrediction) {
            return 2;
        }
        if ((diffGoalsInMatch > 0 && diffGoalsInPrediction > 0) || (diffGoalsInMatch < 0 && diffGoalsInPrediction < 0)) {
            return 1;
        }
        return 0;
    }
}
