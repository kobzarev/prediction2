package com.prediction2.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invites")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "guid")
    private String guid;

    @Column(name = "visited_at")
    private LocalDateTime visitedAt;
}
