package com.prediction2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED not null")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    @Column(name = "score1")
    private Short score1;

    @Column(name = "score2")
    private Short score2;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @Column(name = "play_at", nullable = false)
    private String playAt;

    public Match(Team team1, Team team2, Stage stage, String playAt) {
        this.team1 = team1;
        this.team2 = team2;
        this.stage = stage;
        this.playAt = playAt;
    }
}
