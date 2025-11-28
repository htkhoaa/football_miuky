package com.miuky.footballhtkfailed.apis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.miuky.footballhtkfailed.entities.Competition;
import lombok.Data;

@Data
public class CompetitionApiDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("type")
    private String type;
    @JsonProperty("emblem")
    private String emblem;

    public Competition toEntity() {
        return Competition.builder()
                .id(this.id)
                .name(this.name)
                .code(this.code)
                .type(this.type)
                .emblem(this.emblem)
                .build();
    }
}
