package com.prediction2.controller;

import com.prediction2.entity.Prediction;
import com.prediction2.entity.User;
import com.prediction2.service.PredictionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class PredictionController {
    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("predictions")
    public String getPrediction(Model model) {
        model.addAttribute("result", predictionService.myPrediction(1L));
        return "prediction";
    }

    @PostMapping("predictions/save")
    public String save(
            @RequestParam(value = "ids[]") Long[] ids,
            @RequestParam(value = "score1[]") String[] score1,
            @RequestParam(value = "score2[]") String[] score2) {
        predictionService.save(ids,score1, score2, new User(1L, "sdf", "sdf", "sdf", "sdf", "sdf", "sdf", new ArrayList<Prediction>()));
        return "redirect:/predictions";
    }
}
