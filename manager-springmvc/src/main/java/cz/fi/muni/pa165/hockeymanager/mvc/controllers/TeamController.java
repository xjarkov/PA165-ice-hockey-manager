package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final static Logger logger = LoggerFactory.getLogger(TeamController.class);

    @GetMapping("/list")
    public String list(Model model) {
        logger.info("Team list - GET");

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
        logger.info("Team id:{} remove player - GET", id);

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
            logger.info("Team id:{} remove player - GET - Successfully removed player", id);
        }

        return "redirect:/team/my_team";
    }
}
