package com.prediction2.dto;

import com.prediction2.entity.Prediction;
import lombok.Data;

@Data
public class PredictionDTO {
    private String result;
    private int points;

    private PredictionDTO(String result, int points) {
        this.result = result;
        this.points = points;
    }

    public static PredictionDTO fromEntity(Prediction prediction) {
        return new PredictionDTO(
                String.format("%s:%s", prediction.getScore1(), prediction.getScore2()),
                prediction.getPoints()
        );
    }

    public static PredictionDTO empty() {
        return new PredictionDTO(
                String.format("%s:%s", "-", "-"),
                0
        );
    }
}
