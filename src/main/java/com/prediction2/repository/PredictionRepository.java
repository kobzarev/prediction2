package com.prediction2.repository;

import com.prediction2.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction> findByUserId(Long id);
    List<Prediction> findByMatchId(Long id);
    Optional<Prediction> findByUserIdAndMatchId(Long userId, Long matchId);
}
