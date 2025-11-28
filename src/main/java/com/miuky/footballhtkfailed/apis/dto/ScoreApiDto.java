package com.miuky.footballhtkfailed.apis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.miuky.footballhtkfailed.entities.MatchScore;
import lombok.Data;

@Data
public class ScoreApiDto {
    @JsonProperty("winner")
    private String winner;
    @JsonProperty("duration")
    private String duration;

    @JsonProperty("fullTime")
    private TimeScoreApiDto fullTime;
    @JsonProperty("halfTime")
    private TimeScoreApiDto halfTime;

    @Data
    public static class TimeScoreApiDto {
        @JsonProperty("home")
        private Integer home;
        @JsonProperty("away")
        private Integer away;
    }

    public MatchScore toEntity() {
        return MatchScore.builder()
                .winner(this.winner)
                .duration(this.duration)
                .fullTimeHome(this.fullTime != null ? this.fullTime.getHome() : null)
                .fullTimeAway(this.fullTime != null ? this.fullTime.getAway() : null)
                .halfTimeHome(this.halfTime != null ? this.halfTime.getHome() : null)
                .halfTimeAway(this.halfTime != null ? this.halfTime.getAway() : null)
                .build();
    }

}
