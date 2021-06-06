package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;

import java.util.Set;
import java.util.List;
import java.util.HashSet;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Petr Šopf (506511)
 */
@Controller
@RequestMapping("/player")
public class HockeyPlayerController {

    @Autowired
    private HockeyPlayerFacade hockeyPlayerFacade;

    @Autowired
    private TeamFacade teamFacade;

    private final static Logger logger = LoggerFactory.getLogger(HockeyPlayerController.class);

    @GetMapping("/list")
    public String list(Model model, HttpSession httpSession) {
        logger.info("Hockey player list - GET");

        UserDto authUser = (UserDto) httpSession.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("authenticatedUser", authUser);

        List<HockeyPlayerDto> players = hockeyPlayerFacade.findAll();
        model.addAttribute("players", players);
        return "player/list";
    }

    @GetMapping("/recruit/{id}")
    public String recruit(HttpSession session, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        logger.info("Hockey player recruit id:{} - GET", id);

        UserDto authUser = (UserDto) session.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        HockeyPlayerDto player = hockeyPlayerFacade.findById(id);

        if (player == null) {
            redirectAttributes.addFlashAttribute("generic_error", "Player with this ID does not exists");
            logger.info("Hockey player recruit id:{} - GET - Player with this ID does not exists", id);
            return "redirect:/";
        }

        if (player.getTeam() == null && authUser.getTeam() != null) {
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

            logger.info("Hockey player recruit id:{} - GET - Player assigned to you team", id);
        }

        return "redirect:/team/my_team";
    }

    @GetMapping("/remove/{id}")
    public String removePlayer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        logger.info("Hockey player remove id:{} - GET", id);

        HockeyPlayerDto player = hockeyPlayerFacade.findById(id);

        if (player == null) {
            redirectAttributes.addFlashAttribute("generic_error", "Player with this ID does not exists");
            logger.info("Hockey player remove id:{} - GET - Player with this ID does not exists", id);
            return "redirect:/";
        }

        hockeyPlayerFacade.remove(player);

        return "redirect:/player/list";
    }

    @GetMapping("/create")
    public String createPlayer(Model model, HttpSession httpSession) {
        logger.info("Hockey player create - GET");

        UserDto authUser = (UserDto) httpSession.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        HockeyPlayerCreateDto hockeyPlayerCreateDto = new HockeyPlayerCreateDto();

        model.addAttribute("hockeyPlayerCreateDto", hockeyPlayerCreateDto);
        model.addAttribute("authenticatedUser", authUser);
        return "player/new";
    }

    @PostMapping("/create")
    public String addPlayer(@Valid @ModelAttribute("hockeyPlayerCreateDto") HockeyPlayerCreateDto hockeyPlayerDto,
                            Model model,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            HttpSession httpSession) {
        logger.info("Hockey player create - POST");

        UserDto authUser = (UserDto) httpSession.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.err.println("ObjectError: " + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                logger.info("Hockey player create - POST - {}", fe.getField());
            }
            model.addAttribute("authenticatedUser", authUser);
            return "player/new";
        }
        hockeyPlayerFacade.create(hockeyPlayerDto);

        redirectAttributes.addFlashAttribute("alert_success", "Player was created");
        logger.info("Hockey player create - POST - New player created successfully");
        return "redirect:/player/list";
    }

    @GetMapping("/edit/{id}")
    public String editPlayerForm(@PathVariable("id") Long id, Model model, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        logger.info("Hockey player edit - GET");

        UserDto authUser = (UserDto) httpSession.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("authenticatedUser", authUser);

        HockeyPlayerDto player = hockeyPlayerFacade.findById(id);

        if (player == null) {
            redirectAttributes.addFlashAttribute("generic_error", "Player with this ID does not exists");
            logger.info("Hockey player edit id:{} - GET - Player with this ID does not exists", id);
            return "redirect:/";
        }

        model.addAttribute("hockeyPlayerDto", player);
        return "player/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEditedPlayer(@PathVariable("id") Long id,
                                     @Valid @ModelAttribute("hockeyPlayerDto") HockeyPlayerDto hockeyPlayerDto,
                                     Model model,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     HttpSession httpSession) {
        logger.info("Hockey player edit - POST");

        UserDto authUser = (UserDto) httpSession.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.err.println("ObjectError: " + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                logger.info("Hockey player edit - POST - {}", fe.getField());
            }
            model.addAttribute("authenticatedUser", authUser);
            return "player/edit";
        }
        hockeyPlayerDto.setId(id);
        hockeyPlayerFacade.update(hockeyPlayerDto);

        redirectAttributes.addFlashAttribute("alert_success", "Player was updated");
        logger.info("Hockey player edit - GET - Player edited successfully");
        return "redirect:/player/list";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
    }
}