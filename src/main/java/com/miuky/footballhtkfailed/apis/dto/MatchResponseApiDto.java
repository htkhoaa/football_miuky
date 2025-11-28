package com.miuky.footballhtkfailed.apis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MatchResponseApiDto {
    @JsonProperty("filters")
    private FiltersDto filtersDto;
    @JsonProperty("resultSet")
    private ResultSet resultSet;
    @JsonProperty("matches")
    private List<MatchApiDto> matches;

    @Data
    public static class FiltersDto {
        private String dateFrom;
        private String dateTo;
    }
    @Data
    public static class ResultSet {
        private int count;
        private String competitions;
        private String first;
        private String last;
        private int played;
    }
}
