package com.prediction2.controller;

import com.prediction2.entity.Match;
import com.prediction2.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class TestController {
    private final MatchService matchService;

    public TestController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/test2")
    public String test() {
        List<Match> list = matchService.findAll();
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "index";
    }
}
