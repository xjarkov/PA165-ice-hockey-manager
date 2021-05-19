package cz.fi.muni.pa165.hockeymanager.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/hello" )
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "Hello there!";
    }
}
