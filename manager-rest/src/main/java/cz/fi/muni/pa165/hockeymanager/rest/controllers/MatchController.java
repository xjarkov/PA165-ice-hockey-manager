package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MatchController {
    private final static Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchFacade matchFacade;

    @GetMapping(value = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MatchDto> getAll() {
        logger.info("REST Match findAllMatches()");
        return matchFacade.findAllMatches();
    }

    @GetMapping(value = "/match/nearest", produces = MediaType.APPLICATION_JSON_VALUE)
    public final MatchDto getNearest() {
        logger.info("REST Match findNearestMatch()");
        return matchFacade.findNearestMatch();
    }

    @GetMapping(value = "/match/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final MatchDto getMatch(@PathVariable("id") Long id) {
        logger.info("REST Match findMatchById({})", id);
        return matchFacade.findMatchById(id);
    }

    @GetMapping(value = "/match/{id}/score", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ScoreTuple getMatchScore(@PathVariable("id") Long id) {
        logger.info("REST Match findNearestMatch({})", id);
        return matchFacade.getScoreOfMatch(id);
    }

    @GetMapping(value = "/match/{id}/winner", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, Object> getMatchWinner(@PathVariable("id") Long id) throws JsonProcessingException {
        logger.info("REST Match getMatchWinner({})", id);

        TeamDto teamWinner = matchFacade.getWinningTeam(id);
        Map<String, Object> winningTeam = new LinkedHashMap<>();

        winningTeam.put("id", teamWinner.getId());
        winningTeam.put("name", teamWinner.getName());

        return winningTeam;
    }
}
