package com.miuky.footballhtkfailed.apis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.miuky.footballhtkfailed.entities.Area;
import lombok.Builder;
import lombok.Data;

@Data
public class AreaApiDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("flag")
    private String flag;

    public Area toEntity() {
        return Area.builder()
                .id(this.getId())
                .name(this.getName())
                .code(this.getCode())
                .flag(this.getFlag())
                .build();
    }
}
