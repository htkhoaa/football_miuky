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
@Table(name = "competitions")
public class Competition {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private String code;
    private String type;
    private String emblem;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "competition")
    private List<Season> seasons;

    @OneToMany(mappedBy = "competition")
    private List<Match> matches;
}
