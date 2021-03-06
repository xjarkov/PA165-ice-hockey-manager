package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Matus Jarkovic 456441
 */
@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamFacade teamFacade;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private HockeyPlayerFacade hockeyPlayerFacade;

    @Autowired
    private MatchFacade matchFacade;
  
    private final static Logger logger = LoggerFactory.getLogger(TeamController.class);

    @GetMapping("/list")
    public String list(Model model, HttpSession httpSession) {
        logger.info("Team list - GET");
        UserDto authUser = (UserDto) httpSession.getAttribute("authenticatedUser");
        model.addAttribute("authenticatedUser", authUser);
        List<TeamDto> teams = teamFacade.findAllTeams();
        model.addAttribute("teams", teams);
        return "team/list";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        logger.info("Team detail id:{} - GET", id);

        TeamDto team = teamFacade.findTeamById(id);

        if (team == null) {
            redirectAttributes.addFlashAttribute("generic_error", "Team with this ID does not exists");
            logger.info("Team detail id:{} - GET - Team with this ID does not exists", id);
            return "redirect:/";
        }

        model.addAttribute("team", team);

        return "team/details";
    }

    @GetMapping("/{id}/select")
    public String selectTeam(@PathVariable Long id,
                             HttpSession httpSession,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        logger.info("Team select id:{} - GET", id);

        UserDto authUser = (UserDto)httpSession.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        TeamDto team = teamFacade.findTeamById(id);

        if (team == null) {
            redirectAttributes.addFlashAttribute("generic_error", "Team with this ID does not exists");
            logger.info("Team select id:{} - GET - Team with this ID does not exists", id);
            return "redirect:/";
        }

        if (team.getManager() != null) {
            redirectAttributes.addFlashAttribute("team_has_manager", "This team already has a manager");
            logger.info("Team select id:{} - GET - This team already has a manager", id);
            return "redirect:/user/select";
        }

        if (authUser.getTeam() != null) {
            redirectAttributes.addFlashAttribute("user_has_team", "This user already has a team");
            logger.info("Team select id:{} - GET - This user already has a team", id);
            return "redirect:/user/list";
        }

        authUser.setTeam(team);
        team.setManager(authUser);

        teamFacade.update(team);
        userFacade.update(authUser);

        logger.info("Team select id:{} - GET - Successfully selected team", id);
        return "redirect:/team/" + team.getId();
    }

    @GetMapping("/my_team")
    public String myTeam(Model model, HttpSession httpSession) {
        UserDto authUser = (UserDto)httpSession.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        if (authUser.getTeam() == null) {
            return "redirect:/user/select";
        }

        model.addAttribute("players", authUser.getTeam().getHockeyPlayers());
        model.addAttribute("freeAgents", hockeyPlayerFacade.findPlayersWithoutTeam());
        return "team/teamManagement";
    }

    @GetMapping("/remove/{id}")
    public String removePlayer(HttpSession session, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        logger.info("Team remove player id:{} - GET", id);

        UserDto authUser = (UserDto) session.getAttribute("authenticatedUser");

        if (authUser == null) {
            return "redirect:/auth/login";
        }

        HockeyPlayerDto player = hockeyPlayerFacade.findById(id);

        if (player == null) {
            redirectAttributes.addFlashAttribute("generic_error", "Player with this ID does not exists");
            logger.info("Team remove player id:{} - GET - Player with this ID does not exists", id);
            return "redirect:/";
        }

        if (player.getTeam() != null && authUser.getTeam() != null) {
            TeamDto team = authUser.getTeam();
            team.getHockeyPlayers().remove(player);
            player.setTeam(null);

            teamFacade.update(team);
            hockeyPlayerFacade.update(player);
            logger.info("Team remove player id:{} - GET - Successfully removed player", id);
        }

        return "redirect:/team/my_team";
    }

    @GetMapping("/{id}/delete")
    public String deleteTeam(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        TeamDto team = teamFacade.findTeamById(id);

        if (team != null) {
            for(var match : matchFacade.findAllMatches()){
                if(match.getHomeTeam().getId().equals(team.getId()) || match.getVisitingTeam().getId().equals(team.getId())){
                    redirectAttributes.addFlashAttribute("failure", "Cant delete a team, that is present in a match.");
                    return "redirect:/team/list";
                }
            }

            if(team.getManager() != null){
                redirectAttributes.addFlashAttribute("failure", "Cant delete a team, that has a manager.");
                return "redirect:/team/list";
            }

            //FREE PLAYERS
            if(team.getHockeyPlayers().size() > 0){
                for(var player : team.getHockeyPlayers()){
                    player.setTeam(null);
                    hockeyPlayerFacade.update(player);
                }
            }

            teamFacade.remove(teamFacade.findTeamById(id));
        }

        return "redirect:/team/list";
    }

    @GetMapping(value = "/new")
    public String newTeam(Model model) {
        model.addAttribute("teamCreate", new TeamDto());
        model.addAttribute("championships", Championship.values());
        return "team/new";
    }



    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("teamCreate") TeamDto teamDto,
                         Model model,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        for(TeamDto team : teamFacade.findAllTeams()){
            if(team.getName().equals(teamDto.getName())){
                redirectAttributes.addFlashAttribute("failure", "Can't create team with already existing name.");
                return "redirect:/team/new";
            }
        }
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.err.println("ObjectError: " + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                System.err.println(fe.getField() + "_error");
            }
            return "/team/new";
        }

        teamFacade.create(teamDto);
        return "redirect:/team/list";
    }

    @PostMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @Valid @ModelAttribute("teamDto") TeamDto teamDto,
                         Model model,
                         BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        for(TeamDto team : teamFacade.findAllTeams()){
            if(team.getName().equals(teamDto.getName()) && !teamDto.getName().equals(teamFacade.findTeamById(id).getName())){
                redirectAttributes.addFlashAttribute("failure", "Can't change name to already existing one.");
                return "redirect:/team/edit/" + id.toString();
            }
        }
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                System.err.println("ObjectError: " + ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                System.err.println(fe.getField() + "_error");
            }
            return "/team/list";
        }
        teamDto.setManager(teamFacade.findTeamById(id).getManager());
        teamDto.setId(id);
        teamFacade.update(teamDto);
        return "redirect:/team/list";
    }

    @GetMapping("/edit/{id}")
    public String editPlayerForm(@PathVariable("id") Long id, Model model, HttpSession httpSession) {
        TeamDto teamDto = teamFacade.findTeamById(id);
        model.addAttribute("teamDto", teamDto);
        UserDto userDto = (UserDto) httpSession.getAttribute("authenticatedUser");
        model.addAttribute("authenticatedUser", userDto);
        model.addAttribute("championships", Championship.values());
        return "team/edit";
    }

    @PostMapping("/save/{id}")
    public String updateEditedPlayer(@PathVariable("id") Long id,
                                     @Valid @ModelAttribute("teamDto") TeamDto teamDto,
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
            return "team/edit";
        }
        teamDto.setId(id);
        teamFacade.update(teamDto);
        redirectAttributes.addFlashAttribute("alert_success", "review was updated");
        return "redirect:/team/list";
    }
}
