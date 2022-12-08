package com.prediction2.dto;

import com.prediction2.entity.Match;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class MatchDTO {
    private Long id;
    private String startedAt;
    private String match;
    private String result;
    private List<PredictionDTO> predictions;

    private MatchDTO(Long id, String startedAt, String match, String result, List<PredictionDTO> predictions) {
        this.id = id;
        this.startedAt = startedAt;
        this.match = match;
        this.result = result;
        this.predictions = predictions;
    }

    public static MatchDTO fromEntity(Match match, List<PredictionDTO> list) {
        return new MatchDTO(
                match.getId(),
                match.getPlayAt().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")),
                String.format("%s - %s", match.getTeam1().getTeam(), match.getTeam2().getTeam()),
                String.format("%s:%s", match.getScore1(), match.getScore2()),
                list
        );
    }
}
