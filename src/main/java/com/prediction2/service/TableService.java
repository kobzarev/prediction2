package com.prediction2.service;

import com.prediction2.dto.*;
import com.prediction2.entity.Match;
import com.prediction2.entity.Prediction;
import com.prediction2.entity.Stage;
import com.prediction2.entity.User;
import com.prediction2.repository.MatchRepository;
import com.prediction2.repository.PredictionRepository;
import com.prediction2.repository.StageRepository;
import com.prediction2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {
    private final UserRepository userRepository;
    private final StageRepository stageRepository;

    public TableService(UserRepository userRepository, StageRepository stageRepository) {
        this.userRepository = userRepository;
        this.stageRepository = stageRepository;
    }

    public Table buildTable() {
        List<UserDTO> userDTOList = userRepository.
                findAllByOrderById().
                stream().
                map(UserDTO::fromEntity).
                toList();

        List<Stage> stages = stageRepository.findAll();
        List<StageDTO> stageDTOList = new ArrayList<>();
        for (Stage stage: stages) {
            List<MatchDTO> matchDTOList = new ArrayList<>();
            for (Match match : stage.getMatches().stream().filter(x -> x.getPlayAt().isBefore(LocalDateTime.now())).toList()) {
                List<PredictionDTO> predictionDTOList = new ArrayList<>();
                for (UserDTO userDTO : userDTOList) {
                    predictionDTOList.add(getPredictionByUser(match.getPredictions(), userDTO.getId()));
                }
                matchDTOList.add(MatchDTO.fromEntity(match, predictionDTOList));
            }
            stageDTOList.add(new StageDTO(stage.getStage(), matchDTOList));
        }
        return new Table(userDTOList, stageDTOList);
    }

    private PredictionDTO getPredictionByUser(List<Prediction> predictions, Long userId) {
        Optional<Prediction> prediction =  predictions.
                stream().
                filter(x -> x.getUser().getId().equals(userId)).
                findFirst();

        return prediction.isEmpty() ? PredictionDTO.empty() : PredictionDTO.fromEntity(prediction.get());
    }
}
