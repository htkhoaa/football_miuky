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
@Table(name = "matches")
public class Match {
    @Id
    private Long id;

    private Instant date;
    private String status;
    private Integer matchday;
    private String stage;

    @Column(name = "local_last_updated", nullable = false)
    @UpdateTimestamp
    private Instant localLastUpdated;

    @Column(name = "api_last_updated", nullable = false)
    private Instant apiLastUpdated;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @OneToOne(mappedBy = "match")
    private MatchScore matchScore;
}
