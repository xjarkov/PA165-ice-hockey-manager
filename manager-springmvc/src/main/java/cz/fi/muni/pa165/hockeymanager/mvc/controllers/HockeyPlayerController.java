package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;

import java.util.Set;
import java.util.List;
import java.util.HashSet;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
    public String list(Model model, HttpSession httpSession) {
        UserDto userDto = (UserDto) httpSession.getAttribute("authenticatedUser");
        model.addAttribute("authenticatedUser", userDto);

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

    @GetMapping("/remove/{id}")
    public String removePlayer(@PathVariable Long id) {
        HockeyPlayerDto player = hockeyPlayerFacade.findById(id);
        hockeyPlayerFacade.remove(player);

        return "redirect:/players/list";
    }

    @GetMapping("/create")
    public String createPlayer(Model model, HttpSession httpSession) {
        HockeyPlayerCreateDto hockeyPlayerCreateDto = new HockeyPlayerCreateDto();
        UserDto userDto = (UserDto) httpSession.getAttribute("authenticatedUser");
        model.addAttribute("hockeyPlayerCreateDto", hockeyPlayerCreateDto);
        model.addAttribute("authenticatedUser", userDto);
        return "players/new";
    }

    @PostMapping("/add")
    public String addPlayer(@Valid @ModelAttribute("hockeyPlayerCreateDto") HockeyPlayerCreateDto hockeyPlayerDto,
                            Model model,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            HttpSession httpSession) {
        UserDto userDto = (UserDto) httpSession.getAttribute("authenticatedUser");
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.err.println("ObjectError: " + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            model.addAttribute("authenticatedUser", userDto);
            return "players/new";
        }
        hockeyPlayerFacade.create(hockeyPlayerDto);
        redirectAttributes.addFlashAttribute("alert_success", "Player was created");
        return "redirect:/players/list";
    }

    @GetMapping("/edit/{id}")
    public String editPlayerForm(@PathVariable("id") Long id, Model model, HttpSession httpSession) {
        HockeyPlayerDto hockeyPlayerDto = hockeyPlayerFacade.findById(id);
        model.addAttribute("hockeyPlayerDto", hockeyPlayerDto);
        UserDto userDto = (UserDto) httpSession.getAttribute("authenticatedUser");
        model.addAttribute("authenticatedUser", userDto);
        return "players/edit";
    }

    @PostMapping("/edit")
    public String updateEditedPlayer(@Valid @ModelAttribute("hockeyPlayerDto") HockeyPlayerDto hockeyPlayerDto,
                                     Model model,
                                     BindingResult bindingResult,
                                     UriComponentsBuilder uriBuilder,
                                     RedirectAttributes redirectAttributes,
                                     HttpSession httpSession) {
        UserDto userDto = (UserDto) httpSession.getAttribute("authenticatedUser");
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.err.println("ObjectError: " + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            model.addAttribute("authenticatedUser", userDto);
            return "players/edit";
        }
        hockeyPlayerFacade.update(hockeyPlayerDto);
        redirectAttributes.addFlashAttribute("alert_success", "review was updated");
        Long id = hockeyPlayerDto.getId();
        return "redirect:" + uriBuilder.path("/players/list").buildAndExpand(id).encode().toUriString();
    }
}