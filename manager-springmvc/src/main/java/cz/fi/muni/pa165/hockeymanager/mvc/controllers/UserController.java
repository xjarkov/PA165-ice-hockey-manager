package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author Kristian Kosorin (456620)
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TeamFacade teamFacade;

    @GetMapping(value = "/list")
    public String list(Model model) {
        List<UserDto> allUsers = userFacade.findAllUsers();
        model.addAttribute("users", allUsers);

        return "user/list";
    }

    @GetMapping("/select")
    public String getSelect(HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
        UserDto authUser = (UserDto)httpSession.getAttribute("authenticatedUser");
        if (authUser.getTeam() != null) {
            redirectAttributes.addFlashAttribute("user_has_team", "You already have a team");
            return "redirect:/user/list";
        }

        List<TeamDto> teams = teamFacade.findAllTeams();
        teams = teams.stream().filter(t -> t.getManager() == null).collect(Collectors.toList());

        model.addAttribute("teams", teams);
        model.addAttribute("user", authUser);

        return "user/select";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        UserDto user = userFacade.findUserById(id);
        model.addAttribute("user", user);

        return "user/detail";
    }
}

