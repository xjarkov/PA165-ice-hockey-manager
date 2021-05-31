package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;

import java.util.Set;
import java.util.List;
import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Petr Šopf (506511)
 */
@Controller
@RequestMapping("/players")
public class HockeyPlayerController {
    @Autowired
    private HockeyPlayerFacade hockeyPlayerFacade;

    @Autowired
    private TeamFacade teamFacade;

    @GetMapping("/list")
    public String list(Model model) {
        List<HockeyPlayerDto> players = hockeyPlayerFacade.findAll();
        model.addAttribute("players", players);
        return "players/list";
    }

    @GetMapping("/recruit/{id}")
    public String recruit(HttpSession session, @PathVariable Long id) {
        HockeyPlayerDto player = hockeyPlayerFacade.findById(id);
        UserDto authUser = (UserDto) session.getAttribute("authenticatedUser");

        if (player != null && player.getTeam() == null && authUser != null && authUser.getTeam() != null) {
            TeamDto team = authUser.getTeam();
            player.setTeam(team);

            Set<HockeyPlayerDto> players;
            if (team.getHockeyPlayers() != null) {
                players = team.getHockeyPlayers();
            } else {
                players = new HashSet<>();
            }
            players.add(player);
            team.setHockeyPlayers(players);

            teamFacade.update(team);
            hockeyPlayerFacade.update(player);
        }

        return "redirect:/team/my_team";
    }
}