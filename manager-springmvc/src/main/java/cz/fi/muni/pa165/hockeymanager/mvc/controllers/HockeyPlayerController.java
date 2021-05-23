package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
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
}