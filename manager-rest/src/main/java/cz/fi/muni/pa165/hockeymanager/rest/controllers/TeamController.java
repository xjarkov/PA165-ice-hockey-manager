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

import java.util.List;

@RestController
public class TeamController {
    private final static Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamFacade teamFacade;

    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDto> getAll() {
        logger.info("REST Team findAllTeams()");
        return teamFacade.findAllTeams();
    }

    @GetMapping(value = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDto getMatch(@PathVariable("id") Long id) {
        logger.info("REST Match findTeamById({})", id);
        return teamFacade.findTeamById(id);
    }
}
