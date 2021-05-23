package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HockeyPlayerController {
    private final static Logger logger = LoggerFactory.getLogger(HockeyPlayerController.class);

    @Autowired
    private HockeyPlayerFacade hockeyPlayerFacade;

    @GetMapping(value = "/hockey_players", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HockeyPlayerDto> getAll() {
        logger.info("REST HockeyPlayers findAll()");
        return hockeyPlayerFacade.findAll();
    }
}
