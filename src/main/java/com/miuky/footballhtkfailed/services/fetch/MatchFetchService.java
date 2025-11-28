package com.miuky.footballhtkfailed.services.fetch;

import com.miuky.footballhtkfailed.apis.client.FootballDataApiClient;
import com.miuky.footballhtkfailed.apis.dto.*;
import com.miuky.footballhtkfailed.entities.*;
import com.miuky.footballhtkfailed.repositories.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.*;
import java.util.Optional;

@Service
public class MatchFetchService {

    private static LocalDate today = LocalDate.now();

    private final FootballDataApiClient client;
    private final MatchRepository matchRepo;
    private final CompetitionRepository competitionRepo;
    private final TeamRepository teamRepo;
    private final SeasonRepository seasonRepo;
    private final AreaRepository areaRepo;
    private final MatchScoreRepository matchScoreRepo;

    public MatchFetchService(FootballDataApiClient client, MatchRepository matchRepository,
                             CompetitionRepository competitionRepository, TeamRepository teamRepository,
                             SeasonRepository seasonRepository, AreaRepository areaRepository,
                             MatchScoreRepository matchScoreRepo) {
        this.client = client;
        this.matchRepo = matchRepository;
        this.competitionRepo = competitionRepository;
        this.teamRepo = teamRepository;
        this.seasonRepo = seasonRepository;
        this.areaRepo = areaRepository;
        this.matchScoreRepo = matchScoreRepo;
    }

    public void fetchAndSaveStandings() {

    }

    public void fetchAndSaveMatches() throws ParseException {
        MatchResponseApiDto response = client
                .getMatches(today.toString(), today.plusDays(1).toString())
                .block();

        if (response == null || response.getMatches() == null) return;

        for (MatchApiDto matchDto : response.getMatches()) {
            Optional<Match> oldMatch = matchRepo.findById(matchDto.getId());
            if (oldMatch.isPresent()) {
                Instant apiUpdated = Instant.parse(matchDto.getLastUpdated());
                Instant dbUpdated = oldMatch.get().getApiLastUpdated();
                if (!apiUpdated.equals(dbUpdated)) {
                    Season newSeason = updateSeason(matchDto.getSeason().toEntity());
                    MatchScore newMatchScore = updateMatchScore(matchDto.getScore().toEntity());
                    Match matchUpdated = oldMatch.get();
                    matchUpdated.setSeason(newSeason);
                    matchUpdated.setMatchScore(newMatchScore);
                    matchRepo.save(matchUpdated);
                }
                continue;
            }
            Match newMatch = Match.builder().id(matchDto.getId()).build();
            Area newArea = null;
            if (matchDto.getArea() != null) {
                Optional<Area> oldArea = areaRepo.findById(matchDto.getArea().getId());
                if (oldArea.isPresent()) newArea = oldArea.get();
                else newArea = matchDto.getArea().toEntity();
                newArea = areaRepo.save(newArea);
            }
            Competition newComp = null;
            if (matchDto.getCompetition() != null) {
                Optional<Competition> oldComp = competitionRepo.findById(matchDto.getCompetition().getId());
                if (oldComp.isPresent()) newComp = oldComp.get();
                else {
                    newComp = matchDto.getCompetition().toEntity();
                    newComp.setArea(newArea);
                }
                newComp = competitionRepo.save(newComp);
            }
            Season newSeason = null;
            if (matchDto.getSeason() != null) {
                Optional<Season> oldSeason = seasonRepo.findById(matchDto.getSeason().getId());
                if (oldSeason.isPresent()) newSeason = oldSeason.get();
                else {
                    newSeason = matchDto.getSeason().toEntity();
                    newSeason.setCompetition(newComp);
                }
                newSeason = seasonRepo.save(newSeason);
            }
            Team newHomeTeam = null;
            if (matchDto.getHomeTeam() != null) {
                Optional<Team> oldHomeTeam = teamRepo.findById(matchDto.getHomeTeam().getId());
                if (oldHomeTeam.isPresent()) newHomeTeam = oldHomeTeam.get();
                else {
                    newHomeTeam = matchDto.getHomeTeam().toEntity();
                    newHomeTeam.setArea(newArea);
                }
                newHomeTeam = teamRepo.save(newHomeTeam);
            }
            Team newAwayTeam = null;
            if (matchDto.getAwayTeam() != null) {
                Optional<Team> oldAwayTeam = teamRepo.findById(matchDto.getAwayTeam().getId());
                if (oldAwayTeam.isPresent()) newAwayTeam = oldAwayTeam.get();
                else {
                    newAwayTeam = matchDto.getAwayTeam().toEntity();
                    newAwayTeam.setArea(newArea);
                }
                newAwayTeam = teamRepo.save(newAwayTeam);
            }
            MatchScore newMatchScore = null;
            if (matchDto.getScore() != null){
                newMatchScore = matchDto.getScore().toEntity();
                newMatchScore.setId(newMatch.getId());
            }
            newMatch.setCompetition(newComp);
            newMatch.setSeason(newSeason);
            newMatch.setDate(Instant.parse(matchDto.getDate()));
            newMatch.setStatus(matchDto.getStatus());
            newMatch.setMatchday(matchDto.getMatchday());
            newMatch.setStage(matchDto.getStage());
            newMatch.setApiLastUpdated(Instant.parse(matchDto.getLastUpdated()));
            newMatch.setHomeTeam(newHomeTeam);
            newMatch.setAwayTeam(newAwayTeam);
            newMatch.setMatchScore(newMatchScore);
            matchRepo.save(newMatch);
        }
    }

    private Season updateSeason(Season newSeason) {
        Season seasonUpdated = seasonRepo.findById(newSeason.getId()).get();
        if (newSeason.getWinnerTeam() != null) {
            seasonUpdated.setWinnerTeam(newSeason.getWinnerTeam());
        }
        return seasonUpdated;
    }
    private MatchScore updateMatchScore(MatchScore newMatchScore) {
        MatchScore matchScoreUpdated = matchScoreRepo.findById(newMatchScore.getId()).get();
        if (newMatchScore.getWinner() != null) {
            matchScoreUpdated.setWinner(newMatchScore.getWinner());
        }
        if (newMatchScore.getFullTimeHome() != null) {
            matchScoreUpdated.setFullTimeHome(newMatchScore.getFullTimeHome());
        }
        if (newMatchScore.getFullTimeAway() != null) {
            matchScoreUpdated.setFullTimeAway(newMatchScore.getFullTimeAway());
        }
        if (newMatchScore.getHalfTimeHome() != null) {
            matchScoreUpdated.setHalfTimeHome(newMatchScore.getHalfTimeHome());
        }
        if (newMatchScore.getHalfTimeAway() != null) {
            matchScoreUpdated.setHalfTimeAway(newMatchScore.getHalfTimeAway());
        }
        return matchScoreRepo.save(matchScoreUpdated);
    }
}
