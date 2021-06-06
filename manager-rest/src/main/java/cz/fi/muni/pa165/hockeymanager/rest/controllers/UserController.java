package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Map<String, Object>> getAll() {
        logger.info("REST User findAllUsers()");
        List<UserDto> users = userFacade.findAllUsers();
        List<Map<String, Object>> usersMapped = new ArrayList<>();

        for (UserDto user : users) {
            usersMapped.add(mapUserData(user));
        }

        return usersMapped;
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, Object> getUser(@PathVariable("id") Long id) {
        logger.info("REST User findUserById({})", id);
        return mapUserData(userFacade.findUserById(id));
    }

    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final boolean removeUser(@PathVariable("id") Long id) {
        logger.info("REST User removeUser({})", id);
        UserDto user = userFacade.findUserById(id);
        if (user != null) {
            userFacade.remove(user);
            return true;
        }

        return false;
    }

    private Map<String, Object> mapUserData(UserDto userDto) {
        Map<String, Object> userData = new LinkedHashMap<>();

        userData.put("id", userDto.getId());
        userData.put("name", userDto.getName());
        userData.put("email", userDto.getEmail());
        userData.put("role", userDto.getRole());

        if (userDto.getTeam() != null) {
            userData.put("team", userDto.getTeam().getId());
        } else {
            userData.put("team", null);
        }

        return userData;
    }
}
