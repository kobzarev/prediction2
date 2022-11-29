package com.prediction2.controller;

import com.prediction2.entity.Match;
import com.prediction2.entity.Stage;
import com.prediction2.entity.Team;
import com.prediction2.service.MatchService;
import com.prediction2.service.StageService;
import com.prediction2.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MatchController {
    private final MatchService matchService;
    private final TeamService teamService;
    private final StageService stageService;

    public MatchController(MatchService matchService, TeamService teamService, StageService stageService) {
        this.matchService = matchService;
        this.teamService = teamService;
        this.stageService = stageService;
    }

    @GetMapping("/admin/match/add")
    public String create(Model model) {
        List<Team> teams = teamService.findAll();
        List<Stage> stages = stageService.findAll();
        model.addAttribute("teams", teams);
        model.addAttribute("stages", stages);
        model.addAttribute("title", "Title");
        return "admin/match/add";
    }

    @PostMapping("/admin/match/add")
    public String create(@RequestParam(value = "team1") Long team1Id,
                         @RequestParam(value = "team2") Long team2Id,
                         @RequestParam(value = "time") String time,
                         @RequestParam(value = "stage") Long stageId) {
        matchService.create(new Match(
                teamService.findById(team1Id),
                teamService.findById(team2Id),
                stageService.findById(stageId),
                time
        ));
        return "redirect:/admin/match/add";
    }

    @GetMapping("/admin/match/update")
    public String update(Model model) {
        model.addAttribute("result", matchService.findStarted());
        return "admin/match/update";
    }

    @PostMapping("/admin/match/update")
    public String update(@RequestParam Long id,
                         @RequestParam Short score1,
                         @RequestParam Short score2) {
        matchService.saveResult(id, score1, score2);
        return "redirect:/admin/match/update";
    }
}
