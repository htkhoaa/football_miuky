package com.miuky.footballhtkfailed.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "standings")
public class Standing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private Integer position;
    @Column(name = "played_games")
    private Integer playedGames;
    private Integer points;
    @Column(name = "goal_diffrence")
    private Integer goalDifference;
    @Column(name = "goals_for")
    private Integer goalsFor;
    @Column(name = "goals_against")
    private Integer goalsAgainst;

    @Column(name = "last_updated", nullable = false)
    @UpdateTimestamp
    private Instant lastUpdated;
}
