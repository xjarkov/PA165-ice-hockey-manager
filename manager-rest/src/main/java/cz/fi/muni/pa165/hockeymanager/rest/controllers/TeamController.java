package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
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
public class TeamController {
    private final static Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamFacade teamFacade;

    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Map<String, Object>> getAll() {
        logger.info("REST Team findAllTeams()");
        List<TeamDto> teams = teamFacade.findAllTeams();
        List<Map<String, Object>> teamsMapped = new ArrayList<>();

        for (TeamDto team : teams) {
            teamsMapped.add(mapTeamData(team));
        }

        return teamsMapped;
    }

    @GetMapping(value = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, Object> getMatch(@PathVariable("id") Long id) {
        logger.info("REST Match findTeamById({})", id);
        return mapTeamData(teamFacade.findTeamById(id));
    }

    private Map<String, Object> mapTeamData(TeamDto teamDto) {
        Map<String, Object> teamData = new LinkedHashMap<>();

        teamData.put("id", teamDto.getId());
        teamData.put("name", teamDto.getName());
        teamData.put("points", teamDto.getPoints());

        return teamData;
    }
}
