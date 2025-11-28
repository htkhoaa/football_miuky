package com.miuky.footballhtkfailed.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "teams")
public class Team {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "shortName")
    private String shortName;
    private String tla;
    private String crest;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "winnerTeam")
    private List<Season> wonSeasons;

    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;

    @OneToMany(mappedBy = "team")
    private List<Standing> standings;
}
