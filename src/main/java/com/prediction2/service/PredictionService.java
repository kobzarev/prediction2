package com.prediction2.service;

import com.prediction2.entity.Match;
import com.prediction2.entity.Prediction;
import com.prediction2.entity.User;
import com.prediction2.dto.UserPrediction;
import com.prediction2.repository.MatchRepository;
import com.prediction2.repository.PredictionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PredictionService {
    private final PredictionRepository predictionRepository;
    private final MatchRepository matchRepository;

    public PredictionService(PredictionRepository predictionRepository, MatchRepository matchRepository) {
        this.predictionRepository = predictionRepository;
        this.matchRepository = matchRepository;
    }

    public  List<UserPrediction> myPrediction(Long userId) {
        List<Match> matches = matchRepository.findByPlayAtAfterOrderByPlayAt(
                LocalDateTime.now().minusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        List<Prediction> predictions = predictionRepository.findByUserId(userId);
        List<UserPrediction> result = new ArrayList<>();
        for (Match item : matches) {
            result.add(new UserPrediction(
                    item,
                    getPredictionByMatchId(predictions, item.getId())
            ));
        }
        return result;
    }

    private Prediction getPredictionByMatchId(List<Prediction> predictions, Long matchId) {
        for (Prediction item : predictions) {
            if (Objects.equals(item.getMatch().getId(), matchId)) {
                return item;
            }
        }
        return null;
    }

    public List<Prediction> findByUserId(Long id) {
        return predictionRepository.findByUserId(id);
    }

    public void save(Long[] id, String[] score1, String[] score2, User user) {
        for (int i = 0; i < id.length; i++) {
            Optional<Match> matchOptional = matchRepository.findById(id[i]);
            if (matchOptional.isEmpty()) {
                continue;
            }
            Match match = matchOptional.get();
            Optional<Prediction> predictionOptional = predictionRepository.findByUserIdAndMatchId(user.getId(), match.getId());
            try {
                int team1Score = Integer.parseInt(score1[i]);
                int team2Score = Integer.parseInt(score2[i]);
                if (predictionOptional.isEmpty()) {
                    predictionRepository.save(new Prediction(match, user, team1Score, team2Score, 0));
                } else {
                    Prediction prediction = predictionOptional.get();
                    prediction.setScore1(team1Score);
                    prediction.setScore2(team2Score);
                    predictionRepository.save(prediction);
                }
            } catch (NumberFormatException ignored) {
                int a = 1;
            }
        }
    }
}
