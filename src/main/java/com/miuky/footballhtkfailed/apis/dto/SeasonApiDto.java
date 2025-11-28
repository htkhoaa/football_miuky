package com.miuky.footballhtkfailed.apis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.miuky.footballhtkfailed.entities.Season;
import com.miuky.footballhtkfailed.entities.Team;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SeasonApiDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("currentMatchday")
    private Integer currentMatchday;
    @JsonProperty("winner")
    private TeamApiDto winner;

    public Season toEntity() {
        return Season.builder()
                .id(this.id)
                .startDate(LocalDate.parse(this.startDate))
                .endDate(LocalDate.parse(this.endDate))
                .currentMatchday(this.currentMatchday)
                .winnerTeam(winner != null ? this.winner.toEntity() : null)
                .build();
    }
}
