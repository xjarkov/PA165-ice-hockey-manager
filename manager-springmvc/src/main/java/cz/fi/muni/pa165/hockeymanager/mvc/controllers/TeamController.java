package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Matus Jarkovic 456441
 */
@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamFacade teamFacade;

    @GetMapping("/list")
    public String list(Model model) {
        List<TeamDto> teams = teamFacade.findAllTeams();
        model.addAttribute("teams", teams);
        return "team/list";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        TeamDto team = teamFacade.findTeamById(id);
        model.addAttribute("team", team);

        return "team/details";
    }
}
