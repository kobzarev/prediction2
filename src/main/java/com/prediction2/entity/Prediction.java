package com.prediction2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "predictions")
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int score1;

    private int score2;

    private int points;

    public Prediction(Match match, User user, int score1, int score2, int points) {
        this.match = match;
        this.user = user;
        this.score1 = score1;
        this.score2 = score2;
        this.points = points;
    }
}
