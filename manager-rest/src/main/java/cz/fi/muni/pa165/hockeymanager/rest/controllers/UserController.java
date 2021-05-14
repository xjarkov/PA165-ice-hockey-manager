package cz.fi.muni.pa165.hockeymanager.rest.controllers;


import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;
import cz.fi.muni.pa165.hockeymanager.rest.ApiUris;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_USER)
public class UserController {
    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDto create(@RequestBody UserDto user){
        Long id = userFacade.create(user);
        return userFacade.findUserById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteUser(@PathVariable Long id){
        userFacade.remove(userFacade.findUserById(id));
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDto> findAll(){
        return userFacade.findAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDto findById(@PathVariable Long id){
        return userFacade.findUserById(id);
    }
}
