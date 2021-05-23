package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lukas Machalek (485196)
 */
@Controller
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchFacade matchFacade;

    @Autowired
    private TeamFacade teamFacade;

    @GetMapping("/list")
    public String list(Model model) {
        List<MatchDto> matches = matchFacade.findAllMatches();
        Collections.sort(matches, new Comparator<MatchDto>() {
            public int compare(MatchDto o1, MatchDto o2) {
                return o2.getDateTimeDto().compareTo(o1.getDateTimeDto());
            }
        });
        model.addAttribute("matches", matches);
        return "match/list";
    }

    @GetMapping(value = "/new")
    public String newMatch(Model model) {
        List<TeamDto> teams = teamFacade.findAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("matchCreate", new MatchDto());
        return "match/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("matchCreate")MatchDto formBean, Model model) {
        //create product
        Long id = matchFacade.create(formBean);
        return "match/list";
    }
}
