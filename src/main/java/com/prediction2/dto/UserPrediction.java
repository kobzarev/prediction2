package com.prediction2.dto;

import com.prediction2.entity.Match;
import com.prediction2.entity.Prediction;
import groovy.transform.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
@EqualsAndHashCode
public class UserPrediction {
    private Match match;
    private Prediction prediction;
    private boolean isMatchStarted;

    public UserPrediction(Match match, Prediction prediction) {
        this.match = match;
        this.prediction = prediction;

        this.isMatchStarted = match.getPlayAt().isBefore(LocalDateTime.now());
    }
}
