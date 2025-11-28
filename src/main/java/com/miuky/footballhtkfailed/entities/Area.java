package com.miuky.footballhtkfailed.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
@Entity
@Table(name = "areas")
public class Area {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private String code;
    private String flag;

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
    private List<Competition> competitions;

    @OneToMany(mappedBy = "area")
    private List<Team> teams;

    public void addCompetition(Competition competition) {
        competitions.add(competition);
        competition.setArea(this);
    }

    public void addTeam(Team team) {
        teams.add(team);
        team.setArea(this);
    }
}
