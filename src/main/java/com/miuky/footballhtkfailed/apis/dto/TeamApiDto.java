package com.miuky.footballhtkfailed.apis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.miuky.footballhtkfailed.entities.Team;
import lombok.Data;

@Data
public class TeamApiDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("tla")
    private String tla;
    @JsonProperty("crest")
    private String crest;

    public Team toEntity() {
        return Team.builder()
                .id(this.id)
                .name(this.name)
                .shortName(this.shortName)
                .tla(this.tla)
                .crest(this.crest)
                .build();
    }
}
