package com.prediction2.service;

import com.prediction2.entity.Stage;
import com.prediction2.excpetion.EntityNotFoundException;
import com.prediction2.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {
    private final StageRepository stageRepository;

    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public List<Stage> findAll() {
        return stageRepository.findAll();
    }

    public Stage findById(Long id) {
        Optional<Stage> stage = stageRepository.findById(id);
        if (stage.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return stage.get();
    }
}
