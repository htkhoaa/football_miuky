package com.miuky.footballhtkfailed.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "match_score")
public class MatchScore {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "match_id")
    private Match match;

    private String winner;
    private String duration;
    @Column(name = "full_time_home")
    private Integer fullTimeHome;
    @Column(name = "full_time_away")
    private Integer fullTimeAway;
    @Column(name = "half_time_home")
    private Integer halfTimeHome;
    @Column(name = "half_time_away")
    private Integer halfTimeAway;
}
