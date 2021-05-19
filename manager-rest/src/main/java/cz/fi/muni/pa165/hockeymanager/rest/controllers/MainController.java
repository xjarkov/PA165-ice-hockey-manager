package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import cz.fi.muni.pa165.hockeymanager.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String, String> resourcesMap = new HashMap<>();

        resourcesMap.put("user_uri", ApiUris.ROOT_URI_USER);
        resourcesMap.put("team_uri", ApiUris.ROOT_URI_TEAM);
        resourcesMap.put("match_uri", ApiUris.ROOT_URI_MATCH);
        resourcesMap.put("hockey_player_uri", ApiUris.ROOT_URI_HOCKEYPLAYER);

        return Collections.unmodifiableMap(resourcesMap);
    }
}
