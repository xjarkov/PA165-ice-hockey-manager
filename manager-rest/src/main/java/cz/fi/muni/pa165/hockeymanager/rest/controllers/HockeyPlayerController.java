package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HockeyPlayerController {
    private final static Logger logger = LoggerFactory.getLogger(HockeyPlayerController.class);

    @Autowired
    private HockeyPlayerFacade hockeyPlayerFacade;

    @GetMapping(value = "/hockey_players", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Map<String, Object>> getAll() {
        logger.info("REST HockeyPlayers findAll()");
        List<HockeyPlayerDto> players = hockeyPlayerFacade.findAll();
        List<Map<String, Object>> playersMapped = new ArrayList<>();

        for (HockeyPlayerDto player : players) {
            playersMapped.add(mapPlayerData(player));
        }

        return playersMapped;
    }

    @GetMapping(value = "/hockey_player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, Object> getPlayer(@PathVariable("id") Long id) {
        logger.info("REST HockeyPlayer findById({})", id);
        return mapPlayerData(hockeyPlayerFacade.findById(id));
    }

    private Map<String, Object> mapPlayerData(HockeyPlayerDto hockeyPlayerDto) {
        Map<String, Object> playerData = new LinkedHashMap<>();

        playerData.put("id", hockeyPlayerDto.getId());
        playerData.put("firstName", hockeyPlayerDto.getFirstName());
        playerData.put("lastName", hockeyPlayerDto.getLastName());
        playerData.put("defensiveStrength", hockeyPlayerDto.getDefensiveStrength());
        playerData.put("offensiveStrength", hockeyPlayerDto.getOffensiveStrength());

        if (hockeyPlayerDto.getTeam() != null) {
            playerData.put("team", hockeyPlayerDto.getTeam().getId());
        } else {
            playerData.put("team", null);
        }

        return playerData;
    }
}
