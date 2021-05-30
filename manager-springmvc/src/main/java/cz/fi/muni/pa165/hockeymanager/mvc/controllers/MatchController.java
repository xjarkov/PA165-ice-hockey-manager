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

import cz.fi.muni.pa165.hockeymanager.mvc.LoggedInFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    private final static Logger logger = LoggerFactory.getLogger(LoggedInFilter.class);

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

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("matchDto") MatchDto matchDto,
                         Model model,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.err.println("ObjectError: " + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                System.err.println(fe.getField() + "_error");
            }
            return "match/list";

        }

        matchFacade.create(matchDto);
        return "match/list";
    }
}
