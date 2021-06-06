package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.utils.ScoreTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MatchController {
    private final static Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchFacade matchFacade;

    @GetMapping(value = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Map<String, Object>> getAll() {
        logger.info("REST Match findAllMatches()");
        List<MatchDto> matches = matchFacade.findAllMatches();
        List<Map<String, Object>> matchesMapped = new ArrayList<>();

        for (MatchDto match : matches) {
            matchesMapped.add(mapMatchData(match));
        }

        return matchesMapped;
    }

    @GetMapping(value = "/match/nearest", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, Object> getNearest() {
        logger.info("REST Match findNearestMatch()");
        MatchDto match = matchFacade.findNearestMatch();
        return mapMatchData(match);
    }

    @GetMapping(value = "/match/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, Object> getMatch(@PathVariable("id") Long id) {
        logger.info("REST Match findMatchById({})", id);
        MatchDto match = matchFacade.findMatchById(id);
        return mapMatchData(match);
    }

    @GetMapping(value = "/match/{id}/score", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ScoreTuple getMatchScore(@PathVariable("id") Long id) {
        logger.info("REST Match findNearestMatch({})", id);
        return matchFacade.getScoreOfMatch(id);
    }

    @GetMapping(value = "/match/{id}/winner", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, Object> getMatchWinner(@PathVariable("id") Long id) {
        logger.info("REST Match getMatchWinner({})", id);

        TeamDto teamWinner = matchFacade.getWinningTeam(id);
        Map<String, Object> winningTeam = new LinkedHashMap<>();

        winningTeam.put("id", teamWinner.getId());
        winningTeam.put("name", teamWinner.getName());

        return winningTeam;
    }

    private Map<String, Object> mapMatchData(MatchDto match) {
        Map<String, Object> matchData = new LinkedHashMap<>();

        matchData.put("id", match.getId());
        matchData.put("datetime", match.getDateTimeDto());
        matchData.put("visitingTeamScore", match.getVisitingTeamScore());
        matchData.put("visitingHomeScore", match.getHomeTeamScore());
        matchData.put("homeTeamId", match.getHomeTeam().getId());
        matchData.put("visitingTeamId", match.getVisitingTeam().getId());
        return matchData;
    }
}
