package com.prediction2.repository;

import com.prediction2.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long > {
}
