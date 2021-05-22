package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.UserCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Kristian Kosorin (456620)
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserFacade userFacade;

    @GetMapping(value = "/list")
    public String list(Model model) {
        List<UserDto> allUsers = userFacade.findAllUsers();
        model.addAttribute("users", allUsers);

        return "user/list";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        UserDto user = userFacade.findUserById(id);
        model.addAttribute("user", user);

        return "user/detail";
    }
}

