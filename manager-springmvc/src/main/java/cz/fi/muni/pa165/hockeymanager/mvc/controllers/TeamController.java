package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;

import java.util.List;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
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

    @GetMapping("/list")
    public String list(Model model) {
        List<TeamDto> teams = teamFacade.findAllTeams();
        model.addAttribute("teams", teams);
        return "team/list";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        TeamDto team = teamFacade.findTeamById(id);
        model.addAttribute("team", team);

        return "team/details";
    }

    @GetMapping("/{id}/select")
    public String selectTeam(@PathVariable Long id,
                             HttpSession httpSession,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        TeamDto team = teamFacade.findTeamById(id);
        UserDto authUser = (UserDto)httpSession.getAttribute("authenticatedUser");

        if (team.getManager() != null) {
            redirectAttributes.addFlashAttribute("team_has_manager", "This team already has a manager");

            //TODO change redirect to error page
            return "redirect:/user/list";
        }

        if (authUser.getTeam() != null) {
            redirectAttributes.addFlashAttribute("user_has_team", "This user already has a team");

            //TODO change redirect to error page
            return "redirect:/user/list";
        }

        authUser.setId(team.getId());
        team.setManager(authUser);

        teamFacade.update(team);
        userFacade.update(authUser);

        model.addAttribute("team", team);

        return "team/details";
    }
}
