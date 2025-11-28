package com.miuky.footballhtkfailed.apis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.miuky.footballhtkfailed.entities.Match;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
public class MatchApiDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("utcDate")
    private String date;

    @JsonProperty("status")
    private String status;

    @JsonProperty("matchday")
    private Integer matchday;

    @JsonProperty("stage")
    private String stage;

    @JsonProperty("lastUpdated")
    private String lastUpdated;

    @JsonProperty("area")
    private AreaApiDto area;

    @JsonProperty("competition")
    private CompetitionApiDto competition;

    @JsonProperty("season")
    private SeasonApiDto season;

    @JsonProperty("homeTeam")
    private TeamApiDto homeTeam;

    @JsonProperty("awayTeam")
    private TeamApiDto awayTeam;

    @JsonProperty("score")
    private ScoreApiDto score;

}

