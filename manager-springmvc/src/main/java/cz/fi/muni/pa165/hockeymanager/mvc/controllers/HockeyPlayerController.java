package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
@Controller
@RequestMapping("/players")
public class HockeyPlayerController {
    @Autowired
    private HockeyPlayerFacade hockeyPlayerFacade;

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
            player.setTeam(authUser.getTeam());
            hockeyPlayerFacade.update(player);
        }

        return "redirect:players/list";
    }
}