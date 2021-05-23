package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDto> getAll() {
        logger.info("REST User findAllUsers()");
        return userFacade.findAllUsers();
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDto getUser(@PathVariable("id") Long id) {
        logger.info("REST User findUserById({})", id);
        return userFacade.findUserById(id);
    }
}
