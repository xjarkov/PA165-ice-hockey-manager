package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.MatchCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cz.fi.muni.pa165.hockeymanager.mvc.LoggedInFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    public String getList(Model model) {
        logger.info("get list called");

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
    public String getNew(Model model) {
        logger.info("get new called");

        List<TeamDto> teams = teamFacade.findAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("matchCreate", new MatchCreateDto());

        return "match/new";
    }

    @PostMapping(value = "/new")
    public String postNew(@Valid @ModelAttribute("matchCreate") MatchCreateDto matchDto,
                          @RequestParam("dateTimeDto") LocalDateTime dateAndTime,
                          @RequestParam("homeTeam") TeamDto homeTeam,
                          @RequestParam("visitingTeam") TeamDto visitingTeam,
                          Model model,
                          BindingResult bindingResult) {
        logger.info("post new called");
        logger.info("create(formBean={})", matchDto);

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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
    }
}
