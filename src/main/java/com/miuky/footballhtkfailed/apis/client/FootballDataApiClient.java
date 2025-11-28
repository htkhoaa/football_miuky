package com.miuky.footballhtkfailed.apis.client;

import com.miuky.footballhtkfailed.apis.dto.MatchResponseApiDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FootballDataApiClient {

    private final WebClient webClient;

    public FootballDataApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<com.miuky.footballhtkfailed.apis.dto.MatchResponseApiDto> getMatches(String dateFrom, String dateTo) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/matches")
                        .queryParam("/dateFrom", dateFrom)
                        .queryParam("/dateTo", dateTo)
                        .build())
                .retrieve()
                .bodyToMono(MatchResponseApiDto.class);
    }
}
