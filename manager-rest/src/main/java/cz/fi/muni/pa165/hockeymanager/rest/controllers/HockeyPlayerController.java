package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_HOCKEY_PLAYER)
public class HockeyPlayerController {
    private final static Logger logger = LoggerFactory.getLogger(HockeyPlayerController.class);

    @Inject
    private HockeyPlayerFacade hockeyPlayerFacade;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HockeyPlayerDto> getAll() {
        logger.debug("REST HockeyPlayer findAll()");
        return hockeyPlayerFacade.findAll();
    }
}
