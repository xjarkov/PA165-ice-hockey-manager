package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.MatchCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private final static Logger logger = LoggerFactory.getLogger(MatchController.class);

    @GetMapping("/list")
    public String getList(Model model) {
        logger.info("Match list - GET");

        List<MatchDto> matches = matchFacade.findAllMatches();
        Collections.sort(matches, new Comparator<MatchDto>() {
            public int compare(MatchDto o1, MatchDto o2) {
                return o2.getDateTime().compareTo(o1.getDateTime());
            }
        });
        model.addAttribute("matches", matches);
        return "match/list";
    }

    @GetMapping(value = "/create")
    public String getNew(Model model, RedirectAttributes redirectAttributes) {
        logger.info("Match new - GET");

        List<TeamDto> teams = teamFacade.findAllTeams();

        if (teams == null || teams.isEmpty()) {
            redirectAttributes.addFlashAttribute("generic_error", "Cannot create match, there are no teams present");
            logger.info("Match new - GET - Cannot create match, there are no teams present");
            return "redirect:/";
        }

        model.addAttribute("teams", teams);
        model.addAttribute("matchCreate", new MatchCreateDto());

        return "match/new";
    }

    @PostMapping(value = "/create")
    public String postNew(@Valid @ModelAttribute("matchCreate") MatchCreateDto matchDto,
                          Model model,
                          BindingResult bindingResult) {
        logger.info("Match new - POST");

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.err.println("ObjectError: " + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                System.err.println(fe.getField() + "_error");
                logger.info("Match create - POST - {}", fe.getField());
            }
            return "match/list";

        }

        logger.info("{}", matchDto);

        matchFacade.create(matchDto);
        logger.info("Match create - POST - New match created successfully");
        return "match/list";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
    }
}
